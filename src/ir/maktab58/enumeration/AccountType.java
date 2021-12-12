package ir.maktab58.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Taban Soleymani
 */
@NoArgsConstructor
@AllArgsConstructor
public enum AccountType {
    LONG_TERM_SAVING("long-term"),
    SHORT_TERM_SAVING("short-term"),
    TRANSACTION("transaction");

    private @Getter String type;
}
