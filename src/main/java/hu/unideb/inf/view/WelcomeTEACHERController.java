package hu.unideb.inf.view;

import hu.unideb.inf.modell.CurrentUser;
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
    void welcomeTeacherDownloadButtonPushed() {
        windowLoader("/fxml/Download.fxml", "Download");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeTeacherUploadButtonPushed() {
        windowLoader("/fxml/Upload.fxml", "Upload");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void welcomeTeacherAddCoursePushed() {
        windowLoader("/fxml/AddCourse.fxml", "Add Course");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeTeacherAddStudentPushed() {
        windowLoader("/fxml/AddStudent.fxml", "Add Student");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void welcomeTeacherLogoutButtonPushed() {
        CurrentUser.logOut();
        windowLoader("/fxml/Login.fxml", "Login");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void welcomeTeacherExitButtonPushed() {
        System.exit(0);
    }
    //WELCOME TEACHER STOP
}
