package ir.maktab58.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Taban Soleymani
 */
@AllArgsConstructor
public enum TransactionType {
    DEPOSIT("deposit"),
    WITHDRAW("withdraw"),
    DEPOSIT_INTEREST("deposit_interest"),
    CARD_TO_CARD("card_to_card");

    private @Getter String type;
}
