package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.util.CourseDAO;
import hu.unideb.inf.util.EduDAO;
import hu.unideb.inf.util.JpaCourseDAO;
import hu.unideb.inf.util.JpaEduDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CourseLogInController extends LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
    //COURSE ADD
    @FXML
    private ListView<Course> foundCourse;

    @FXML
    private TextField courseName;

    @FXML
    private Button courseLogInBackButton;

    @FXML
    private TextField courseCode;
    
    @FXML
    void addCoursePushed() {
        var code = courseCode.getText();
        var course = foundCourse.getSelectionModel().getSelectedItem();
        var theChosenOne = CurrentUser.getInstance().getCurrent();
        if(code.equals(course.getCode())){
            theChosenOne.addCourse(course);
            try(EduDAO uDao = new JpaEduDAO<User>()){
                uDao.update(theChosenOne);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect credentials");
            alert.setHeaderText("Incorrect password!");
            alert.showAndWait();
        }
    }

    @FXML
    void courseLogInBackButtonPushed() {
        userMode = CurrentUser.getInstance().getCurrent().getType().toString();
        windowLoader("/fxml/Welcome"+userMode+".fxml","Welcome"+userMode);
        Stage stage = (Stage) courseLogInBackButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void searchShowAllPushed() {
        //Get all courses
        ObservableList<Course> list = FXCollections.observableArrayList();
        List<Course> courses;
        try(EduDAO cDao = new JpaEduDAO<Course>()){
            courses = cDao.getData(Course.class);
        }
        for(var course : courses){
            list.add(course);
        }
        foundCourse.setItems(list);
    }

    @FXML
    void searchCoursePushed() {
        ObservableList<Course> list = FXCollections.observableArrayList();
        String name = courseName.getText();
        Course course;
        //Get a course
        try(CourseDAO courseDao = new JpaCourseDAO()){
             course = courseDao.getByName(name);
        }
        //Add course to the list
        list.add(course);
        foundCourse.setItems(list);
    }

    @FXML
    void courseLogInExitButtonPushed() {
        System.exit(0);
    }
    //COURSE LOG IN END
}
