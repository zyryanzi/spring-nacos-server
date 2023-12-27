package cloud.spring.my.study.gof23.behaviorPatten.state.enums;

import cloud.spring.my.study.gof23.behaviorPatten.state.statepattern.*;

public enum State {

    DRAFT("未提交"){
        @Override
        public DraftEvent getBehavior() {
            return new DraftEvent();
        }
    },
    SUBMITTED("已提交") {
        @Override
        public SubmittedEvent getBehavior() {
            return new SubmittedEvent();
        }
    },
    APPROVAL("已通过") {
        @Override
        public DefaultEvent getBehavior() {
            return new DefaultEvent();
        }
    },
    RETURNED("已打回") {
        @Override
        public ReturnedEvent getBehavior() {
            return new ReturnedEvent();
        }
    },
    REJECTED("已拒绝") {
        @Override
        public DefaultEvent getBehavior() {
            return new DefaultEvent();
        }
    },
    ;

    /**
     * 取得行为实现
     * @return
     */
    public abstract AbstractEvent getBehavior();

    private String desc;

    State(String name) {
        this.desc = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 取得码
     * @param desc
     * @return
     */
    public static String getCodeByDesc(String desc) {
        for (State value : values()) {
            if (value.desc.equals(desc)) {
                return value.name();
            }
        }
        return desc;
    }

    public static void main(String[] args) {
    }

}
