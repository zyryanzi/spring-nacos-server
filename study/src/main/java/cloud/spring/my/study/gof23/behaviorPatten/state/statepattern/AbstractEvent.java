package cloud.spring.my.study.gof23.behaviorPatten.state.statepattern;

import cloud.spring.my.study.gof23.behaviorPatten.state.ConcreteBean;
import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;

/**
 * 接口过度类，避免实现类要实现全部接口方法
 */
public class AbstractEvent implements IBehavior {

    /**
     * 实际被操作的对象
     */
    protected ConcreteBean concreteBean;

    /**
     * 下一个节点所对应的行为
     */
    protected AbstractEvent nextBehavior;

    public void setConcreteBean(ConcreteBean concreteBean) {
        this.concreteBean = concreteBean;
    }

    /**
     * 取得下一个节点对应行为
     * @param state 节点状态枚举
     * @return 下一个节点对应的行为
     */
    protected AbstractEvent getNextBehavior(State state) {
        concreteBean.setStatus(state.name());
        nextBehavior = state.getBehavior();
        nextBehavior.concreteBean = concreteBean;
        return nextBehavior;
    }

    @Override
    public AbstractEvent save() {
        return this;
    }

    @Override
    public AbstractEvent submit() {
        return this;
    }

    @Override
    public AbstractEvent pass() {
        return this;
    }

    @Override
    public AbstractEvent reject() {
        return this;
    }

    @Override
    public AbstractEvent transfer() {
        return this;
    }

    @Override
    public AbstractEvent rReturn() {
        return this;
    }

}
