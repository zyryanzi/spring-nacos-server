package cloud.spring.my.study.gof23.behaviorPatten.state.statepattern;

import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;

public class SubmittedEvent extends AbstractEvent {

    @Override
    public AbstractEvent pass() {
        boolean hasNext = Math.random() < 0.5d;
        if (hasNext) {
            System.out.println("--->> 提交代审状态，nextState: " + State.SUBMITTED.getDesc());
            return getNextBehavior(State.SUBMITTED);
        } else {
            System.out.println("--->> 提交代审状态，nextState: " + State.APPROVAL.getDesc());
            return getNextBehavior(State.APPROVAL);
        }
    }

    @Override
    public AbstractEvent reject() {
        System.out.println("--->> 提交代审状态，nextState: " + State.REJECTED.getDesc());
        return getNextBehavior(State.REJECTED);
    }

    @Override
    public AbstractEvent transfer() {
        System.out.println("--->> 提交代审状态，nextState: " + State.SUBMITTED.getDesc());
        return getNextBehavior(State.SUBMITTED);
    }

    @Override
    public AbstractEvent rReturn() {
        boolean returnToDraft = Math.random() < 0.5d;
        if (returnToDraft) {
            System.out.println("--->> 提交代审状态，nextState: " + State.DRAFT.getDesc());
            return getNextBehavior(State.DRAFT);
        } else {
            System.out.println("--->> 提交代审状态，nextState: " + State.SUBMITTED.getDesc());
            return getNextBehavior(State.SUBMITTED);
        }
    }

}
