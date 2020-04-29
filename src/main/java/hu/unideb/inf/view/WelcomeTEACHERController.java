package hu.unideb.inf.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeTEACHERController extends LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    

    //WELCOME TEACHER START
    @FXML
    private Button welcomeTeacherDownloadButton;
      
       @FXML
    void welcomeTeacherLogoutButtonPushed() {
        windowLoader("/fxml/Login.fxml", "Login");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeTeacherDownloadButtonPushed() {
        windowLoader("/fxml/Download.fxml", "Download");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeTeacherExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void welcomeTeacherUploadButtonPushed() {
        windowLoader("/fxml/Upload.fxml", "Upload");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeTeacherUsersButtonPushed() {
        windowLoader("/fxml/Teacher.fxml", "Add user");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }
    //WELCOME TEACHER STOP
}
