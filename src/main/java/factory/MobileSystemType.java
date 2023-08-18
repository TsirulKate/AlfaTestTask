package factory;

import lombok.Getter;

@Getter
public enum MobileSystemType {
    ANDROID("ANDROID"),
    IOS("IOS");

    private final String type;

    MobileSystemType(String type) {
        this.type = type;
    }
}
