package cloud.spring.my.study.domain;

/**
 * 火星充电桩
 */
public class MarsCharging extends Charging {

    /**
     * 充电
     */
    @Override
    public void joinPowerNet() {
        System.out.println("接入充电网络");
    }

}
