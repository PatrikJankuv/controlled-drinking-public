package cz.cvut.fel.jankupat.AlkoApp.model;

import cz.cvut.fel.jankupat.AlkoApp.model.enums.AccountRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.springframework.lang.NonNull;


/**
 * @author Patrik Jankuv
 * @created 8/2/2020
 */
@Entity
public class Account extends BaseEntity implements IEntity{


    @Column(unique = true, nullable = false)
    private String email;


    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountRole role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }
}
