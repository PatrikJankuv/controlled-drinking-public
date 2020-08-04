package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.AccountDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Account;
import cz.cvut.fel.jankupat.AlkoApp.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrik Jankuv
 * @created 8/4/2020
 */
@RestController
@RequestMapping(path = "/account")
public class AccountController extends BaseController<AccountService, Account, AccountDao> {
    public AccountController(AccountService service){ super(service);}
}
