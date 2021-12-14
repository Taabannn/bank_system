package ir.maktab58.models.factory;

import ir.maktab58.enumeration.AccountType;
import ir.maktab58.enumeration.LongTermType;
import ir.maktab58.exceptions.BankSysException;
import ir.maktab58.models.Owner;

import java.util.Date;

/**
 * @author Taban Soleymani
 */
public class AccountFactory {
    public static Account getAccount(String type, long initialBalance, long accountNumber, Owner owner) {
        Account account;
        if (AccountType.SHORT_TERM_SAVING.getType().equals(type)) {
            account = ShortTermAccount.builder()
                    .withAccountNumber(accountNumber)
                    .withBalance(initialBalance)
                    .withOwner(owner)
                    .withCreationDate(new Date()).build();
        } else if (AccountType.LONG_TERM_SAVING.getType().equals(type)) {
            account = LongTermSavingAccount.builder()
                    .withAccountNumber(accountNumber)
                    .withBalance(initialBalance)
                    .withOwner(owner)
                    .withLongTermType(LongTermType.SIX_MONTH)
                    .withCreationDate(new Date()).build();
        } else if (AccountType.TRANSACTION.getType().equals(type)) {
            account = TransactionAccount.builder()
                    .withAccountNumber(accountNumber)
                    .withBalance(initialBalance)
                    .withOwner(owner)
                    .withCreationDate(new Date()).build();
        } else {
            throw BankSysException.builder()
                    .message("Type of account must be declared.")
                    .errorCode(400).build();
        }
        return account;
    }
}
