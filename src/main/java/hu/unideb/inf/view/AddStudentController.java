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
import javafx.scene.control.Label;
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
    private TextField getUsernameForAddStudent;
    @FXML
    private Label backlogForAddStudent;


    @FXML
    void addStudentPushed() {
        //Get the chosen ones

            User selectedUser = foundStudents.getSelectionModel().getSelectedItem();
            Course c = courses.getSelectionModel().getSelectedItem();
        if(selectedUser!=null &&c!=null){
            //Add the course to the user
            selectedUser.addCourse(c);
            c.addUser(selectedUser);
            //Update the database
            
            try(EduDAO<User> uDAO = new JpaEduDAO<>()){
                if(uDAO.update(selectedUser))backlogForAddStudent.setText("Hallgató sikeresen hozzáadva!");
                else backlogForAddStudent.setText("Sikeretlen hozzáadás!"); 
            }catch(Exception e){
                System.out.println(e);
            }
        }else backlogForAddStudent.setText("Nem választottál ki kurzust/ hallgatót!");
    }

    @FXML
    void searchStudentByPartUernamePushed() {
        String partUsername = getUsernameForAddStudent.getText();
        
        if(!partUsername.isEmpty()){
            backlogForAddStudent.setText("");
            try(JpaUserDAO getStudentsForAddign = new JpaUserDAO()){
                
                List<User> bufferForShearcingByPartname = getStudentsForAddign.getByPartUsername(partUsername);
                if(bufferForShearcingByPartname.size()>0){
                    backlogForAddStudent.setText("Felhasználók kilistásva");
                    ObservableList<User> students = FXCollections.observableArrayList();
                    bufferForShearcingByPartname.forEach((user) -> {
                        if(user.getType().equals(userType.STUDENT)){
                            students.add(user);
                        }
                    });
                    foundStudents.setItems(students);
                }else backlogForAddStudent.setText("Nincs ilyen felhasználó");
            }
        }else backlogForAddStudent.setText("Nem adtál meg felhasználó nevet");
        
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
