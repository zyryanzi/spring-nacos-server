package cloud.spring.my.study.gof23.behaviorPatten.state.statemachine;

import cloud.spring.my.study.gof23.behaviorPatten.state.ConcreteBean;
import cloud.spring.my.study.gof23.behaviorPatten.state.enums.Event;
import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;
import cloud.spring.my.study.gof23.behaviorPatten.state.statemachine.handler.DraftStateHandler;
import cloud.spring.my.study.gof23.behaviorPatten.state.statemachine.handler.IStateHandler;

import java.util.Arrays;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<Process> processList = Arrays.asList(
                ProcessBuilder.builder().from(State.DRAFT).event(Event.SAVE).to(State.DRAFT).build(),
                ProcessBuilder.builder().from(State.DRAFT).event(Event.SUBMIT).to(State.SUBMITTED).handler(new DraftStateHandler()).build()
        );

        StateMachine stateMachine = new StateMachine(processList);
        State nextState = stateMachine.getNextState(State.DRAFT, Event.SUBMIT);
        System.out.println("--->> nextState: " + nextState);
        IStateHandler<Object, Object> nextStateHandler = stateMachine.getNextStateHandler(State.DRAFT, Event.SUBMIT);

        ConcreteBean cb = new ConcreteBean();
        cb.setStatus(State.DRAFT.name());
        nextStateHandler.handle(cb);
        System.out.println("--->> after cb status: " + cb.getStatus());

    }

}
