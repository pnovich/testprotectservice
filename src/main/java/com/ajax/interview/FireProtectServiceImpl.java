package com.ajax.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Service
public class FireProtectServiceImpl implements FireProtectService{

    List<FireDepartment> departments = new ArrayList<>();

    private Map<String, Device> devices = new ConcurrentHashMap<>();

    public FireProtectServiceImpl() {
        this.devices = new ConcurrentHashMap<>();
//        Device device = new Device("1")
    }


    @Override
    public void changeState(String deviceId, State state) {
        devices.get(deviceId).setState(state);
    }

    @Override
    public synchronized void changeTemperature(String deviceId, short temperatureDiff) {

        if (!devices.containsKey(deviceId) ) {
            Device device = new Device(deviceId, State.DISARMED);
            devices.put(deviceId, device);
        }

        Device device = devices.get(deviceId);
        int currentValue = device.getTemperatureValue();


        device.setTemperatureValue(currentValue + temperatureDiff);

        if (device.getState() == State.ARMED && (temperatureDiff > 20 || currentValue >= 80)) {
            departments.forEach(d -> {
                d.alarm(deviceId);

            });
        }
        int lastValue = device.getTemperatureValue();
    }

    @Override
    public State getState(String deviceId) {
        return devices.get(deviceId).getState();
    }

    @Override
    public short getTemperature(String deviceId) {
        return (short)devices.get(deviceId).getTemperatureValue();
    }

    @Override
    public void registerFireDepartment(FireDepartment fireDepartment) {

        departments.add(fireDepartment);
    }
}
