package ir.maktab58.service;

import ir.maktab58.dao.UpdateInfoDao;
import ir.maktab58.models.UpdateInfo;

/**
 * @author Taban Soleymani
 */
public class UpdateInfoService {
    private final UpdateInfoDao updateInfoDao = new UpdateInfoDao();

    public int saveUpdateInfo(UpdateInfo updateInfo) {
        return updateInfoDao.save(updateInfo);
    }
}
