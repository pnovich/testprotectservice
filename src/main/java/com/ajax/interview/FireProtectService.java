package com.ajax.interview;

/**
 * <p>Функциональные требования:</p>
 * Есть датчик температуры, который может быть в состоянии armed/disarmed.
 * Это состояние может меняться вызовом метода changeState.
 * В метод changeTemperature приходит изменение температуры для устройства с id.
 * При изменении температуры сервис должен вызывать пожарную службу если
 * температура повышается больше чем на 20 градусов или текущая температура в помещении больше 80
 * но только если датчик в состоянии armed.
 * <p>
 * Когда по устройству первый раз приходит какое-то уведомление,
 * то считается, что он в состоянии disarmed и температура в помещении 20 градусов.
 *
 * <p>Нефункциональные требования:</p>
 * Считаем, что реализация этого интерфейса будет singleton-бином (реализацию singleton делать не нужно) который
 * обрабатывает сигналы от всех датчиков в системе (десятки или сотни тысяч). В связи с чем реализация должна корректно
 * работать в многопоточном режиме, учитывая что разные методы могут быть вызваны одновременно с разных потоков.
 * <p>
 * Также, нужно написать юнит тесты, которые:
 * 1) Проверят функциональные требования
 * 2) Проверят нефункциональные требования. Конкретно - правильность работы в многопоточном режиме.
 */

/**
 * <p> Functional requirements: </p>
 * There is a temperature sensor that can be armed / disarmed.
 * This state can be changed by calling the changeState method.
 * The changeTemperature method receives the temperature change for the device with id.
 * When the temperature changes, the service must call the fire department if
 * the temperature rises by more than 20 degrees or the current room temperature is more than 80
 * but only if the sensor is armed.
 *
 * When a notification arrives for the first time on the device,
 * it is considered that sensors is disarmed and the room temperature is 20 degrees.
 *
 * <p> Non-functional requirements: </p>
 * We suppose that the implementation of this interface is a singleton-bean (you do not need to implement a singleton) which
 * processes signals from all sensors in the system (tens or hundreds of thousands). So the implementation must be correct
 * work in multi-threaded mode, considering that different methods can be called simultaneously from different threads.
 *
 * Also, you need to write unit tests that:
 * 1) Check functional requirements
 * 2) Check for non-functional requirements. Specifically - the correctness of work in multi-threaded mode.
 *
 */
public interface FireProtectService {

    void changeState(String deviceId, State state);

    void changeTemperature(String deviceId, short temperatureDiff);

    State getState(String deviceId);

    short getTemperature(String deviceId);

    void registerFireDepartment(FireDepartment fireDepartment);
}
