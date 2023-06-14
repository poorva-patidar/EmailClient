package com.poorva.view;

import com.poorva.EmailManager;
import com.poorva.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {
    private EmailManager emailManager;
    private ArrayList<Stage> activeStages;
    private boolean mainViewInitialized = false;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        activeStages = new ArrayList<>();
    }

    public boolean isMainViewInitialized(){
        return mainViewInitialized;
    }

    //---------------------------Options Handling----------------------------------
    private Theme theme = Theme.DARK;
    private FontSize fontSize = FontSize.MEDIUM;

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public FontSize getFontSize() {
        return fontSize;
    }
    //---------------------------------------------------------------------------------------------------------
    public void showLoginScreen(){
        BaseController controller = new LoginSceneController(emailManager, this, "LoginScreen.fxml");
        initializeStage(controller);
    }

    public void showMainScreen(){
        BaseController controller = new MainScreenController(emailManager, this, "MainScreen.fxml");
        initializeStage(controller);
        mainViewInitialized = true;
    }

    public void showOptionsScreen(){
        BaseController controller = new OptionsScreenController(emailManager, this, "OptionsScreen.fxml");
        initializeStage(controller);
    }

    public void showComposeMessageScreen(){
        BaseController controller = new ComposeMessageScreenController(emailManager, this, "ComposeMessageScreen.fxml");
        initializeStage(controller);
    }

    public void showEmailDetailsScreen(){
        BaseController controller = new EmailDetailsController(emailManager, this, "EmailDetailsScreen.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        loader.setController(controller);

        Parent parent;
        try{
            parent = loader.load();
        } catch(IOException e){
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        updateStyles(scene);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public void closeStage(Stage stageToClose){
        stageToClose.close();
        activeStages.remove(stageToClose);
    }

    public void closeAllStages(){
        for (Stage activeStage: activeStages) {
            activeStage.close();
        }
    }

    public void updateAllStyles() {
        for(Stage stage: activeStages){
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(Theme.getCssPath(theme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
            updateStyles(scene);
        }
    }

    public void updateStyles(Scene scene) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(Theme.getCssPath(theme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
    }
}
