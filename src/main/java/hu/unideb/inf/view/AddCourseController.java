package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.util.EduDAO;
import hu.unideb.inf.util.JpaEduDAO;
import hu.unideb.inf.util.JpaUserDAO;
import hu.unideb.inf.util.UserDAO;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCourseController extends LoginController implements Initializable {
    
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    @FXML
    private ListView<Course> courseList;

    @FXML
    private TextField newCourseName;

    @FXML
    private TextField newCourseCode;

    @FXML
    private Button uploadBackButton;

    @FXML
    void courseLoadPushed(ActionEvent event) {

    }

    @FXML
    void uploadBackButtonPushed(ActionEvent event) {

    }

    @FXML
    void uploadExitButtonPushed(ActionEvent event) {

    }

    @FXML
    void uploadUploadButton(ActionEvent event) {

    }
}
