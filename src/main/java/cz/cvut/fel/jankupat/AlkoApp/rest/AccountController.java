package cz.cvut.fel.jankupat.AlkoApp.rest;

import cz.cvut.fel.jankupat.AlkoApp.dao.AccountDao;
import cz.cvut.fel.jankupat.AlkoApp.model.Account;
import cz.cvut.fel.jankupat.AlkoApp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

//    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> createEntity(@RequestBody Account entity) {
//        System.out.println(entity.getPassword() + entity.getEmail() + entity.getRole());
//        service.persist(entity);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}
