package cloud.spring.my.study.gof23.behaviorPatten.state;

import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;

/**
 * 实际持有状态的类
 */
public class ConcreteBean {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
