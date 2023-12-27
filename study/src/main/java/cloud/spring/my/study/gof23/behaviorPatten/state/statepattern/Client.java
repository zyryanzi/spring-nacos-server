package cloud.spring.my.study.gof23.behaviorPatten.state.statepattern;

import cloud.spring.my.study.gof23.behaviorPatten.state.ConcreteBean;
import cloud.spring.my.study.gof23.behaviorPatten.state.enums.State;

public class Client {

    public static void main(String[] args) {
        ConcreteBean bean = new ConcreteBean();
        bean.setStatus(State.DRAFT.name());

        State state = State.valueOf(bean.getStatus());

        AbstractEvent behavior = state.getBehavior();

        behavior.setConcreteBean(bean);

        behavior = behavior.save();

        behavior = behavior.submit();

        behavior = behavior.pass();

        behavior = behavior.pass();
        behavior = behavior.pass();
        behavior = behavior.pass();
        behavior = behavior.pass();
        behavior = behavior.pass();
        behavior = behavior.pass();


        System.out.println("behavior instanceof DraftBehavior: " + (behavior instanceof DraftEvent));
        System.out.println("behavior instanceof SubmittedBehavior: " + (behavior instanceof SubmittedEvent));
        System.out.println("behavior instanceof ReturnedBehavior: " + (behavior instanceof ReturnedEvent));
        System.out.println("behavior instanceof DefaultBehavior: " + (behavior instanceof DefaultEvent));

    }

}
