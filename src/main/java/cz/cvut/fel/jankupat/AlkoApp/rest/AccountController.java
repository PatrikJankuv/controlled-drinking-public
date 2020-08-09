package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.AccountDao;
import cz.cvut.fel.jankupat.AlkoApp.exception.NotFoundException;
import cz.cvut.fel.jankupat.AlkoApp.model.Account;
import cz.cvut.fel.jankupat.AlkoApp.service.AccountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@RestController
@RequestMapping(path = "/account")
public class AccountController extends BaseController<AccountService, Account, AccountDao> {
    public AccountController(AccountService service){ super(service);}

    @GetMapping(path = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account findByEmail(@PathVariable String email){
        final Account account = service.findByEmail(email);

        if(account == null){
            throw NotFoundException.create("Account", email);
        }
        return account;
    }
}
