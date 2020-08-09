package cz.cvut.fel.jankupat.AlkoApp.dao;

import cz.cvut.fel.jankupat.AlkoApp.exception.NotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
@Repository
public class AccountDao extends BaseDao<Account> {

    public AccountDao(){
        super(Account.class);
    }

    public Account findByEmail(String email){
        List<Account> accounts = em.createQuery("SELECT a FROM Account a WHERE a.email = :email").setParameter("email", email).getResultList();

        if(accounts.isEmpty()){
            throw new NotFoundException("Account with given email not found");
        }

        return accounts.get(0);
    }
}
