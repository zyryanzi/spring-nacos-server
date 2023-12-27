package cloud.spring.my.study.gof23.behaviorPatten.state.statemachine;

import cloud.spring.my.study.gof23.behaviorPatten.state.enums.Event;
import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;
import cloud.spring.my.study.gof23.behaviorPatten.state.statemachine.handler.IStateHandler;
import lombok.Data;

@Data
public class Process {

    private State from;

    private State to;

    private Event event;

    private IStateHandler<Object, Object> stateHandler;

}
