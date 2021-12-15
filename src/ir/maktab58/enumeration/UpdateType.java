package ir.maktab58.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Taban Soleymani
 */
@AllArgsConstructor
public enum UpdateType {
    UPDATE_USERNAME("update username"),
    UPDATE_PASSWORD("update password");

    private @Getter String type;
}
