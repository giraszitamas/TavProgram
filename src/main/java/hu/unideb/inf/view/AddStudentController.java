package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.entity.User.userType;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.util.EduDAO;
import hu.unideb.inf.util.JpaEduDAO;
import hu.unideb.inf.util.JpaUserDAO;
import hu.unideb.inf.util.UserDAO;
import java.net.URL;
import java.util.List;
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
    private Button teacherBackButton;

    


    @FXML
    void addStudentPushed() {
        //Get the chosen ones
        User selectedUser = foundStudents.getSelectionModel().getSelectedItem();
        Course c = courses.getSelectionModel().getSelectedItem();
        //Add the course to the user
        selectedUser.addCourse(c);
        c.addUser(selectedUser);
        //Update the database
        EduDAO<User> uDAO = new JpaEduDAO<>();
        try{
            uDAO.update(selectedUser);
        }catch(Exception e){
            System.out.println(e);
        }
        uDAO.close();
        
    }



    @FXML
    void searchStudentPushed() {
        //Load students
        ObservableList<User> students = FXCollections.observableArrayList();
            List<User> users;
            try(UserDAO userDAO = new JpaUserDAO()){
                users = userDAO.getEveryUser();
            }
            users.forEach((user) -> {
                if(user.getType().equals(userType.STUDENT)){
                students.add(user);
                }
            });          
            foundStudents.setItems(students);
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
