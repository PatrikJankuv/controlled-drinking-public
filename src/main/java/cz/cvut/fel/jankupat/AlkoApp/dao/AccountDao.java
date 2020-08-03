package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.model.Account;
import org.springframework.stereotype.Repository;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Repository
public class AccountDao extends BaseDao<Account> {

    public AccountDao(){
        super(Account.class);
    }
}
