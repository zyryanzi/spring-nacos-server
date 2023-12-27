package cloud.spring.my.study.gof23.behaviorPatten.state.statemachine;

import cloud.spring.my.study.gof23.behaviorPatten.state.enums.Event;
import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;
import cloud.spring.my.study.gof23.behaviorPatten.state.statemachine.handler.IStateHandler;

public class ProcessBuilder {
    private Process process;

    protected void setProcess(Process process) {
        this.process = process;
    }

    public static ProcessBuilder builder() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.setProcess(new Process());
        return processBuilder;
    }

    public ProcessBuilder from(State state) {
        process.setFrom(state);
        return this;
    }

    public ProcessBuilder to(State state) {
        process.setTo(state);
        return this;
    }

    public ProcessBuilder event(Event event) {
        process.setEvent(event);
        return this;
    }

    public ProcessBuilder handler(IStateHandler stateHandler) {
        process.setStateHandler(stateHandler);
        return this;
    }

    public Process build() {
        return process;
    }
}
