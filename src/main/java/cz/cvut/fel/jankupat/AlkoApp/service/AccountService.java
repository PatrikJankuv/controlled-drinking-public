package cz.cvut.fel.jankupat.AlkoApp.service;

import cz.cvut.fel.jankupat.AlkoApp.dao.AccountDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Account;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
public class AccountService extends BaseService<Account, AccountDao> {

    public AccountService(AccountDao dao){
        super(dao);
    }
}
