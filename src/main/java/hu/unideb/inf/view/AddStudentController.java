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

public class AddStudentController extends LoginController implements Initializable {
    
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert courses != null : "fx:id=\"courses\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert foundStudents != null : "fx:id=\"foundStudents\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert searchUsername != null : "fx:id=\"searchUsername\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert searchFirstName != null : "fx:id=\"searchFirstName\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert searchLastName != null : "fx:id=\"searchLastName\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert teacherBackButton != null : "fx:id=\"teacherBackButton\" was not injected: check your FXML file 'AddStudent.fxml'.";
        assert searchEmail != null : "fx:id=\"searchEmail\" was not injected: check your FXML file 'AddStudent.fxml'.";
        
        //Load courses
        ObservableList<Course> list = FXCollections.observableArrayList();
        Set<Course> createdCourses = CurrentUser.getInstance().getCurrent().getCourses();
        createdCourses.forEach((course) -> {
            list.add(course);
        });
        courses.setItems(list);
    }

    @FXML
    private ListView<Course> courses;

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
        //Get the chosen ones
        User selectedUser = foundStudents.getSelectionModel().getSelectedItem();
        Course c = courses.getSelectionModel().getSelectedItem();
        //Add the course to the user
        selectedUser.addCourse(c);
        c.addUser(selectedUser);
        //Update the database
        EduDAO uDAO = new JpaEduDAO<User>();
        try{
            uDAO.update(selectedUser);
        }catch(Exception e){
            System.out.println(e);
        }
        uDAO.close();
        
    }

    @FXML
    void courseLoadPushed() {
        ObservableList<Course> list = FXCollections.observableArrayList();
        Set<Course> createdCourses = CurrentUser.getInstance().getCurrent().getCourses();
        createdCourses.forEach((course) -> {
            list.add(course);
        });
        courses.setItems(list);
    }

    @FXML
    void courseSelectPushed() {
        //Can update the currently selecteced user
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
        handleExit(true);
    }
}
