package hu.unideb.inf.view;

import hu.unideb.inf.modell.CurrentUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomeADMINController extends LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeAdminText.setText("Üdv újra, " + CurrentUser.getInstance().getCurrent().getLastName().toString()+ "!");
    }
    
    
    //WELCOME ADMIN START
    @FXML
    private Button welcomeAdminDownloadButton;
    
    @FXML
    private Text welcomeAdminText;
    
    @FXML
    void welcomeAdminDownloadButtonPushed() {
        windowLoader("/fxml/Download.fxml", "Download");
        Stage stage = (Stage) welcomeAdminDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeAdminExitButtonPushed() {
        handleExit(true);
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
    
    @FXML
    void welcomeAdminLogoutButtonPushed() {
        CurrentUser.logOut();
        windowLoader("/fxml/Login.fxml", "Login");
        Stage stage = (Stage) welcomeAdminDownloadButton.getScene().getWindow();
        stage.close();
    }
    //WELCOME ADMIN STOP
}
