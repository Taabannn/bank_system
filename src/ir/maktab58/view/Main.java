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
    }
}
