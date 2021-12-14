package ir.maktab58.service;

import ir.maktab58.exceptions.BankSysException;
import ir.maktab58.models.Owner;
import ir.maktab58.service.singletonvalidator.NationalCodeValidator;
import ir.maktab58.service.singletonvalidator.UserAndPassValidator;

/**
 * @author Taban Soleymani
 */
public class BankService {
    private final OwnerService ownerService = new OwnerService();

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
}
