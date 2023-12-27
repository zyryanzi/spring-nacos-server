package cloud.spring.my.study.gof23.behaviorPatten.state.statemachine;

import cloud.spring.my.study.gof23.behaviorPatten.state.enums.Event;
import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;
import cloud.spring.my.study.gof23.behaviorPatten.state.statemachine.handler.IStateHandler;
import com.google.common.collect.HashBasedTable;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.List;

public class StateMachine {

    private List<Process> processList;

    /**
     * 执行器，封装了下一个状态和下一个逻辑处理器
     */
    @Data
    static class Exec {

        private State nextState;

        private IStateHandler<Object, Object> nextStateHandler;

    }

    /**
     * 根据原始的状态和事件，得到第三个参数，即下一个状态和附加的处理逻辑
     */
    private final HashBasedTable<State, Event, Exec> processTable = HashBasedTable.create();

    public StateMachine(List<Process> processList) {
        Assert.noNullElements(processList, "流程列表不能为空");
        this.processList = processList;
        processList.forEach(process -> {
            Exec exec = new Exec();
            exec.setNextState(process.getTo());
            exec.setNextStateHandler(process.getStateHandler());
            processTable.put(process.getFrom(), process.getEvent(), exec);
        });
    }

    public State getNextState(State state, Event event) {
        return getNextExec(state, event).getNextState();
    }

    public IStateHandler<Object, Object> getNextStateHandler(State statee, Event event) {
        return getNextExec(statee, event).getNextStateHandler();
    }

    private Exec getNextExec(State statee, Event event) {
        Exec exec = processTable.get(statee, event);
        Assert.notNull(exec, "执行器不存在");
        return exec;
    }

}


