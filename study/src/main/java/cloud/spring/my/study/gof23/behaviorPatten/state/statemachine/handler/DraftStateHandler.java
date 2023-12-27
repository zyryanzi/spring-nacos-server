package cloud.spring.my.study.gof23.behaviorPatten.state.statemachine.handler;

import cloud.spring.my.study.gof23.behaviorPatten.state.ConcreteBean;
import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DraftStateHandler implements IStateHandler<ConcreteBean, Boolean>{

    @Override
    public Boolean handle(ConcreteBean concreteBean) {
        log.info("--->> 执行草稿提交逻辑");
        concreteBean.setStatus(State.SUBMITTED.name());
        return true;
    }

}
