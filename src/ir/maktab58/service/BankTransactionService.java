package ir.maktab58.service;

import ir.maktab58.dao.BankTransactionDao;
import ir.maktab58.models.BankTransaction;

import java.util.List;

/**
 * @author Taban Soleymani
 */
public class BankTransactionService {
    private final BankTransactionDao bankTransactionDao = new BankTransactionDao();

    public List<BankTransaction> getAccountTransaction(int accountId) {
        return bankTransactionDao.findTransactionsByAccountID(accountId);
    }

    public void deleteTheOldTransaction(BankTransaction oldTransaction, int accountId) {
        bankTransactionDao.removeOldTransaction(oldTransaction, accountId);
    }

    public int saveNewTransaction(BankTransaction bankTransaction) {
        return bankTransactionDao.save(bankTransaction);
    }
}
