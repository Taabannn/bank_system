package ir.maktab58.view;

import ir.maktab58.dao.BaseDaoInterfaceImpl;
import ir.maktab58.models.Owner;
import ir.maktab58.models.factory.TransactionAccount;

import java.util.Date;

/**
 * @author Taban Soleymani
 */
public class Main {
    public static void main(String[] args) {
        BankSys bankSys = new BankSys();
        bankSys.showMenu();
        /*Owner owner = Owner.builder().withName("Taban")
                .withFamily("Soleymani")
                .withEmail("tabansoleymani@yahoo.com")
                .withPassword("61378").withNationalCode(8990).build();
        TransactionAccount account = TransactionAccount.builder().withAccountNumber(5567).withOwner(owner).withBalance(455).withCreationDate(new Date()).build();
        BaseDaoInterfaceImpl<TransactionAccount> accountDao = new BaseDaoInterfaceImpl<>();
        accountDao.save(account);*/
    }
}
