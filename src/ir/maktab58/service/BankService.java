package ir.maktab58.service;

import ir.maktab58.enumeration.UpdateType;
import ir.maktab58.exceptions.BankSysException;
import ir.maktab58.models.Owner;
import ir.maktab58.models.UpdateInfo;
import ir.maktab58.models.factory.Account;
import ir.maktab58.service.singletonvalidator.NationalCodeValidator;
import ir.maktab58.service.singletonvalidator.UserAndPassValidator;

import java.util.Date;
import java.util.List;

/**
 * @author Taban Soleymani
 */
public class BankService {
    private final OwnerService ownerService = new OwnerService();
    private final AccountService accountService = new AccountService();
    private final CardService cardService = new CardService();
    private final UpdateInfoService updateInfoService = new UpdateInfoService();

    public int registerOwnerInBank(String inputLine) {
        String[] tokens = inputLine.split(" ");
        String name = tokens[0];
        String family = tokens[1];
        String nationalCode = tokens[2];
        String username = tokens[3];
        String password = tokens[4];
        validateUserInfo(nationalCode, username, password);
        checkOwnerIsExisted(username, password, nationalCode);
        Owner owner = Owner.builder()
                .withName(name).withFamily(family)
                .withNationalCode(nationalCode)
                .withUsername(username).withPassword(password).build();
        return ownerService.saveOwner(owner);
    }

    private void validateUserInfo(String nationalCode, String username, String password) {
        boolean isUserNPassValid = UserAndPassValidator
                .getInstance()
                .isUserAndPassValid(username, password);
        boolean isNationalCodeValid = NationalCodeValidator
                .getInstance()
                .isNationalCodeValid(nationalCode);
        if (!isUserNPassValid)
            throw BankSysException.builder()
                    .message("Invalid user and pass!")
                    .errorCode(400).build();
        if (!isNationalCodeValid)
            throw BankSysException.builder()
                    .message("Invalid national-code!")
                    .errorCode(400).build();
    }

    public int checkOwnerIsExisted(String inputLine) {
        String[] tokens = inputLine.split(" ");
        String username = tokens[0];
        String password = tokens[1];
        Owner existedOwner = ownerService.getExistedOwner(username, password);
        if (existedOwner != null)
            return existedOwner.getId();
        return 0;
    }

    public void checkOwnerIsExisted(String username, String password, String nationalCode) {
        Owner existedOwner = ownerService.getExistedOwner(username, password);
        if (existedOwner != null)
            throw BankSysException.builder()
                    .message("username: " + username + ", password: " + password + " is already existed.")
                    .errorCode(400).build();
        existedOwner = ownerService.getExistedOwner(nationalCode);
        if (existedOwner != null)
            throw BankSysException.builder()
                    .message("national_code: " + nationalCode + " is already existed.")
                    .errorCode(400).build();
    }

    public List<Account> getOwnerAccounts(int id) {
        return accountService.getAccountsByOwnerId(id);
    }

    public int openNewAccountForThisOwner(int id, String inputLine) {
        String[] tokens = inputLine.split(" ");
        String type = tokens[0];
        long initialBalance = Long.parseLong(tokens[1]);
        Owner owner = ownerService.getOwnerById(id);
        Account account = accountService.openAnAccount(type, initialBalance, owner);
        cardService.generateACard(account);
        return accountService.saveAccount(account);
    }

    public int checkAccountExisted(long accountNumber, int ownerId) {
        return accountService.getAccountId(accountNumber, ownerId).getId();
    }

    public void updateUserUserName(String username, int ownerId) {
        Owner owner = ownerService.updateOwnerUsername(username, ownerId);
        UpdateInfo updateInfo = UpdateInfo.builder()
                .withDateOfUpdate(new Date())
                .withUpdateType(UpdateType.UPDATE_USERNAME)
                .withDetail("username has changed to: " + username)
                .withOwner(owner).build();
        updateInfoService.saveUpdateInfo(updateInfo);
    }

    public void updateUserPassword(String password, int ownerId) {
        Owner owner = ownerService.updateOwnerPassword(password, ownerId);
        UpdateInfo updateInfo = UpdateInfo.builder()
                .withDateOfUpdate(new Date())
                .withUpdateType(UpdateType.UPDATE_PASSWORD)
                .withDetail("password has changed to: " + password)
                .withOwner(owner).build();
        updateInfoService.saveUpdateInfo(updateInfo);
    }
}
