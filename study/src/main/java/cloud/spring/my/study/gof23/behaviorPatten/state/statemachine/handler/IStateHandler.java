package cloud.spring.my.study.gof23.behaviorPatten.state.statemachine.handler;

public interface IStateHandler<T, R> {

    R handle(T t);

}