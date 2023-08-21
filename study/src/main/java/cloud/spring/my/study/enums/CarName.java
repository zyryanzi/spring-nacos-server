package cloud.spring.my.study.enums;

public enum CarName {
    TESLA,
    NIO,
    ;

    public static CarName getByName(String name) {
        for (CarName carName : values()) {
            if (carName.name().equals(name)) {
                return carName;
            }
        }
        return null;
    }
}
