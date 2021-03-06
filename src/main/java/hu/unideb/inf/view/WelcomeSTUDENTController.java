package hu.unideb.inf.view;

import hu.unideb.inf.modell.CurrentUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomeSTUDENTController extends LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeStudentText.setText("Üdv újra, " + CurrentUser.getInstance().getCurrent().getFirstName().toString()+ "!");
    }
    
    public String userMode;
    
    //WELCOME STUDENT START
    @FXML
    private Button welcomeDownloadButton;
    
    @FXML
    private Text welcomeStudentText;
    
    @FXML
    void welcomeDownloadButtonPushed() {
        windowLoader("/fxml/Download.fxml", "Download");
        Stage stage = (Stage) welcomeDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeExitButtonPushed() {
        handleExit(true);
    }

    @FXML
    void welcomeUploadButtonPushed() {
        windowLoader("/fxml/Upload.fxml", "Upload");
        Stage stage = (Stage) welcomeDownloadButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void studentCourseLogIn() {
        windowLoader("/fxml/CourseLogIn.fxml", "Course Log In");
        Stage stage = (Stage) welcomeDownloadButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void welcomeLogoutButtonPushed() {
        CurrentUser.logOut();
        windowLoader("/fxml/Login.fxml", "Login");
        Stage stage = (Stage) welcomeDownloadButton.getScene().getWindow();
        stage.close();
    }
    //WELCOME STUDENT END
}
