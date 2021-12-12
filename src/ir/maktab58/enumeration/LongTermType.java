package ir.maktab58.enumeration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author Taban Soleymani
 */
@AllArgsConstructor
public enum LongTermType {
    SIX_MONTH(0.1),
    ONE_YEAR(0.15),
    TWO_YEAR(0.2);

    private @Getter double interest;
}
