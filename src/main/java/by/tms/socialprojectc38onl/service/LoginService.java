package by.tms.socialprojectc38onl.service;

import by.tms.socialprojectc38onl.dao.DAOAccounts;
import by.tms.socialprojectc38onl.exception.AuthException;
import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.validator.EmailValidator;

public class LoginService {
    DAOAccounts accountsDAO = DAOAccounts.getInstance();
    public Account authenticate(String email, String password) {

        if(!EmailValidator.isValidEmail(email)) {
            throw new AuthException("Email is not valid");
        }

        Account account = accountsDAO.findByEmail(email)
                .orElseThrow(() -> new AuthException("Wrong email or password"));

        if (!account.getPassword().equals(password)) {
            throw new AuthException("Wrong email or password");
        }

        return account;

    }
}
