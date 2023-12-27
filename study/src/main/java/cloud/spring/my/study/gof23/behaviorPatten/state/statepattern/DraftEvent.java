package cloud.spring.my.study.gof23.behaviorPatten.state.statepattern;

import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;

public class DraftEvent extends AbstractEvent {

    @Override
    public AbstractEvent save() {
        System.out.println("--->> 草稿状态，nextState: " + State.DRAFT.getDesc());
        return getNextBehavior(State.DRAFT);
    }

    @Override
    public AbstractEvent submit() {
        System.out.println("--->> 草稿状态，nextState: " + State.SUBMITTED.getDesc());
        return getNextBehavior(State.SUBMITTED);
    }

}
