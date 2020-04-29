package hu.unideb.inf.view;

import hu.unideb.inf.entity.User;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.util.JpaUserDAO;
import hu.unideb.inf.util.UserDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeacherController extends LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
    // TEACHER START
    // TEACHER ADD STUDENT
    @FXML
    private ListView<User> foundStudents;

    @FXML
    private TextField searchUsername;

    @FXML
    private TextField searchFirstName;

    @FXML
    private TextField searchLastName;

    @FXML
    private Button teacherBackButton;

    @FXML
    private TextField searchEmail;

    @FXML
    void addStudentPushed() {
        //TODO: Select a course somehow. - if it will be needed...
//        //Add the course to the users
//        User selectedUser = foundStudents.getSelectionModel().getSelectedItem();
//        selectedUser.addCourse(subject);
//        try (EduDAO uDAO = new JpaEduDAO<User>()) {
//            uDAO.update(selectedUser);
//        }
    }

    @FXML
    void searchStudentPushed() {
        ObservableList<User> list = FXCollections.observableArrayList();
        //get the username
        String username = searchUsername.getText();
        //search the user and add it to the list
        try (UserDAO userDAO = new JpaUserDAO()) {
            User user = userDAO.getByUsername(username);
            list.add(user);
            foundStudents.setItems(list);
        }
    }

    @FXML
    void teacherBackButtonPushed() {
        userMode = CurrentUser.getInstance().getCurrent().getType().toString();
        windowLoader("/fxml/Welcome"+userMode+".fxml","Welcome"+userMode);
        Stage stage = (Stage) teacherBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void teacherExitButtonPushed() {
        System.exit(0);
    }
    // TEACHER ADD STUDENT END
    // TEACHER END
}
