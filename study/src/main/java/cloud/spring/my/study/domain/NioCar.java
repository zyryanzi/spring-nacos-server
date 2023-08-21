package cloud.spring.my.study.domain;

import cloud.spring.my.study.gof23.structuralPatten.adapter.ChargingAdapter;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class NioCar implements ICar, Cloneable {

    /**
     * 火星充电桩适配器
     */
    private ChargingAdapter adapter;

    private String name;
    private Date offlineDate;

    @Override
    public void manufacture() {
        System.out.println("组装一台蔚来");
    }

    @Override
    public void paint() {
        System.out.println("涂装一台蔚来");
    }

    /**
     * 浅克隆
     * @return
     * @throws CloneNotSupportedException
     */
//    @Override
//    public NioCar clone() throws CloneNotSupportedException {
//        return (NioCar) super.clone();
//    }

    /**
     * 深克隆
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public NioCar clone() throws CloneNotSupportedException {
        NioCar clone = (NioCar) super.clone();
        clone.offlineDate = (Date) clone.offlineDate.clone();
        return clone;
    }

    /**
     * 充电
     */
    public void charge(ChargingAdapter adapter) {
        adapter.handleCharge();
    }
}
