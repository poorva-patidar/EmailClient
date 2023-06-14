package com.poorva;

import com.poorva.controller.persistence.PersistenceAccess;
import com.poorva.controller.persistence.ValidAccount;
import com.poorva.controller.services.LoginService;
import com.poorva.model.EmailAccount;
import com.poorva.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private PersistenceAccess persistenceAccess = new PersistenceAccess();
    private EmailManager emailManager = new EmailManager();

    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(emailManager);
        List<ValidAccount> validAccountsList = persistenceAccess.loadPersistence();
        if(validAccountsList.size() > 0){
            viewFactory.showMainScreen();
            for (ValidAccount validAccount: validAccountsList) {
                EmailAccount emailAccount = new EmailAccount(validAccount.getAddress(), validAccount.getPassword());
                LoginService loginService = new LoginService(emailAccount, emailManager);
                loginService.start();
            }
        } else {
            viewFactory.showLoginScreen();
        }
    }

    @Override
    public void stop() throws Exception {
        List<ValidAccount> validAccountList = new ArrayList<>();
        for(EmailAccount emailAccount: emailManager.getEmailAccounts()){
            validAccountList.add(new ValidAccount(emailAccount.getAddress(), emailAccount.getPassword()));
            persistenceAccess.saveToPersistence(validAccountList);
        }
    }
}
