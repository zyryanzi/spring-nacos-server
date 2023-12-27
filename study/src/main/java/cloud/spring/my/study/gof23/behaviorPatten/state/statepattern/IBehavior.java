package cloud.spring.my.study.gof23.behaviorPatten.state.statepattern;

/**
 * 状态所持有的行为
 * 意即当前状态下，可以有那些行为
 */
public interface IBehavior {

    IBehavior save();

    IBehavior submit();

    IBehavior pass();

    IBehavior reject();

    IBehavior transfer();

    IBehavior rReturn();

}
