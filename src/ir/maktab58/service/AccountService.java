package ir.maktab58.service;

import ir.maktab58.dao.AccountDao;
import ir.maktab58.models.Owner;
import ir.maktab58.models.factory.Account;
import ir.maktab58.models.factory.AccountFactory;

import java.util.List;
import java.util.Random;

/**
 * @author Taban Soleymani
 */
public class AccountService {
    private final AccountDao accountDao = new AccountDao();

    public Account openAnAccount(String type, long initialBalance, Owner owner) {
        Random random = new Random();
        long accountNumber = 9999999 + random.nextLong(10000000);
         return AccountFactory.getAccount(type, initialBalance, accountNumber, owner);
    }

    public List<Account> getAccountsByOwnerId(int ownerId) {
        return accountDao.findAccountsByOwnerId(ownerId);
    }

    public int saveAccount(Account account) {
        return accountDao.save(account);
    }

    public Account getAccountId(long accountNumber, int ownerId) {
        return accountDao.findAccountsByOwnerIdAndAccountNumber(accountNumber, ownerId);
    }

    public Account getAccountByAccountId(int accountId) {
        return accountDao.get(Account.class, accountId);
    }

    public void updateAccount(Account account) {
        accountDao.update(account);
    }
}
