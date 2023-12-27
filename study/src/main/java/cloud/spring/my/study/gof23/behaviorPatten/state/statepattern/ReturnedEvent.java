package cloud.spring.my.study.gof23.behaviorPatten.state.statepattern;

import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;

public class ReturnedEvent extends AbstractEvent {

    @Override
    public AbstractEvent submit() {
        System.out.println("--->> 打回状态，nextState: " + State.SUBMITTED.getDesc());
        return getNextBehavior(State.SUBMITTED);
    }

}
