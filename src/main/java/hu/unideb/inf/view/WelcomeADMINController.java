package hu.unideb.inf.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeADMINController extends LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
    
    //WELCOME ADMIN START
    @FXML
    private Button welcomeAdminDownloadButton;
      
       @FXML
    void welcomeAdminLogoutButtonPushed() {
        windowLoader("/fxml/Login.fxml", "Login");
        Stage stage = (Stage) welcomeAdminDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeAdminDownloadButtonPushed() {
        windowLoader("/fxml/Download.fxml", "Download");
        Stage stage = (Stage) welcomeAdminDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeAdminExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void welcomeAdminUploadButtonPushed() {
        windowLoader("/fxml/Upload.fxml", "Upload");
        Stage stage = (Stage) welcomeAdminDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeAdminUsersButtonPushed() {
        windowLoader("/fxml/Admin.fxml", "Add user");
        Stage stage = (Stage) welcomeAdminDownloadButton.getScene().getWindow();
        stage.close();
    }
    
    //WELCOME ADMIN STOP
}
