package cloud.spring.my.study.gof23.structuralPatten.adapter;

import cloud.spring.my.study.domain.Charging;
import cloud.spring.my.study.domain.MarsCharging;
import lombok.Data;

/**
 * 真正工作的适配器
 */

@Data
public class MarsAdapter implements ChargingAdapter{

    /**
     * 火星充电桩
     */
    private MarsCharging marsCharging;

    public MarsAdapter(MarsCharging marsCharging) {
        this.marsCharging = marsCharging;
    }

    @Override
    public void handleCharge() {
        marsCharging.joinPowerNet();
    }

}
