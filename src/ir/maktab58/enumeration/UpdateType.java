package ir.maktab58.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Taban Soleymani
 */
@AllArgsConstructor
public enum UpdateType {
    UPDATE_NAME("update name"),
    UPDATE_FAMILY("update family"),
    UPDATE_USERNAME("update username"),
    UPDATE_PASSWORD("update password"),
    UPDATE_EMAIL("update email");

    private @Getter String type;
}
