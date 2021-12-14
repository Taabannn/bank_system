package ir.maktab58.service;

import ir.maktab58.dao.OwnerDao;
import ir.maktab58.models.Owner;

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
}
