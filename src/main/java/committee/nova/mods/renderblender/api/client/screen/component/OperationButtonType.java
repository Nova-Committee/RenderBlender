package committee.nova.mods.renderblender.api.client.screen.component;

import lombok.Getter;

import java.util.Arrays;

/**
 * 操作按钮类型
 */
@Getter
public enum OperationButtonType {
    TYPE(1),
    ITEM(2),
    COUNT(3),
    NBT(4),
    SLIDER(5),
    ;

    final int code;

    OperationButtonType(int code) {
        this.code = code;
    }

    static OperationButtonType valueOf(int code) {
        return Arrays.stream(values()).filter(v -> v.getCode() == code).findFirst().orElse(null);
    }
}
