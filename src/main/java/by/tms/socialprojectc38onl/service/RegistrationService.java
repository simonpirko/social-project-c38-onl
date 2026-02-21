package by.tms.socialprojectc38onl.service;

import by.tms.socialprojectc38onl.dao.DAOAccounts;
import by.tms.socialprojectc38onl.dto.RegistrationDTO;
import by.tms.socialprojectc38onl.dto.RegistrationResultDTO;
import by.tms.socialprojectc38onl.models.Account;
import by.tms.socialprojectc38onl.validator.EmailValidator;

import java.sql.SQLException;
import java.util.Objects;

public class RegistrationService {
    private final int PASSWORD_LENGTH = 6;
    DAOAccounts accountsDAO = DAOAccounts.getInstance();

    public RegistrationResultDTO register(RegistrationDTO registrationRequest) {
        RegistrationResultDTO registrationResult = new RegistrationResultDTO();

        if (registrationRequest.getNickname().isEmpty()
                || registrationRequest.getEmail().isEmpty()
                || registrationRequest.getPassword().isEmpty()
                || registrationRequest.getRepeatPassword().isEmpty()) {
            registrationResult.setSuccess(false);
            registrationResult.setMessage("Fields are required");
        }

        if (EmailValidator.isValidEmail(registrationRequest.getEmail())) {
            registrationResult.setSuccess(false);
            registrationResult.setMessage("Email is not valid");
        }

        if (registrationRequest.getPassword().length() < PASSWORD_LENGTH) {
            registrationResult.setSuccess(false);
            registrationResult.setMessage("Fields are required");
        }

        if (!Objects.equals(registrationRequest.getPassword(), registrationRequest.getRepeatPassword())) {
            registrationResult.setSuccess(false);
            registrationResult.setMessage("The passwords don't match");

            return registrationResult;
        }

        if (accountsDAO.findByEmail(registrationRequest.getEmail()).isPresent()) {
            registrationResult.setSuccess(false);
            registrationResult.setMessage("Email is already in use");

            return registrationResult;
        }


        Account account = new Account();
        account.setNickname(registrationRequest.getNickname());
        account.setPassword(registrationRequest.getPassword());
        account.setEmail(registrationRequest.getEmail());

        accountsDAO.save(account);

        registrationResult.setSuccess(true);
        registrationResult.setMessage("Registration successful");

        return registrationResult;
    }
}
