package ir.maktab58.service;

import ir.maktab58.dao.OwnerDao;
import ir.maktab58.exceptions.BankSysException;
import ir.maktab58.models.Owner;
import ir.maktab58.service.singletonvalidator.UserAndPassValidator;

/**
 * @author Taban Soleymani
 */
public class OwnerService {
    private final OwnerDao ownerDao = new OwnerDao();

    public Owner getExistedOwner(String username, String password) {
        return ownerDao.findOwnerByUserAndPass(username, password);
    }

    public Owner getExistedOwner(String nationalCode) {
        return ownerDao.findOwnerByNationalCode(nationalCode);
    }

    public int saveOwner(Owner owner) {
        return ownerDao.save(owner);
    }

    public Owner getOwnerById(int ownerId) {
        return ownerDao.get(Owner.class, ownerId);
    }

    public Owner updateOwnerUsername(String username, int ownerId) {
        Owner owner = ownerDao.get(Owner.class, ownerId);
        boolean isUserAndPassValid = UserAndPassValidator.getInstance().isUserAndPassValid(username, owner.getPassword());
        if(!isUserAndPassValid)
            throw BankSysException.builder().message("you've entered invalid username").errorCode(400).build();
        owner.setUsername(username);
        ownerDao.update(owner);
        return owner;
    }

    public Owner updateOwnerPassword(String password, int ownerId) {
        Owner owner = ownerDao.get(Owner.class, ownerId);
        boolean isUserAndPassValid = UserAndPassValidator.getInstance().isUserAndPassValid(owner.getUsername(), password);
        if(!isUserAndPassValid)
            throw BankSysException.builder().message("you've entered invalid password").errorCode(400).build();
        owner.setUsername(password);
        ownerDao.update(owner);
        return owner;
    }
}
