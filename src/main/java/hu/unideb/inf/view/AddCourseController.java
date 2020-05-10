package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.util.EduDAO;
import hu.unideb.inf.util.JpaEduDAO;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddCourseController extends LoginController implements Initializable {

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load courses
        ObservableList<Course> list = FXCollections.observableArrayList();
        Set<Course> createdCourses = CurrentUser.getInstance().getCurrent().getCourses();
        createdCourses.forEach((course) -> {
            list.add(course);
        });
        courseList.setItems(list);
    }

    @FXML
    private ListView<Course> courseList;

    @FXML
    private TextField newCourseName;

    @FXML
    private TextField newCourseCode;

    @FXML
    private Button addCourseBackButton;
    @FXML
    private Text addNewCourseBacklog;

    @FXML
    void courseLoadPushed() {
        //Load courses
        ObservableList<Course> list = FXCollections.observableArrayList();
        Set<Course> createdCourses = CurrentUser.getInstance().getCurrent().getCourses();
        createdCourses.forEach((course) -> {
            list.add(course);
        });
        courseList.setItems(list);
    }

    @FXML
    void saveCoursePushed() {
        //Create new course
        String name = newCourseName.getText();
        String code = newCourseCode.getText();

        if (!name.isEmpty() && !code.isEmpty()) {
            User user = CurrentUser.getInstance().getCurrent();
            Course newCourse = new Course(name, code, user.getId());
            //Save the new course
            user.addCourse(newCourse);
            newCourse.addUser(user);
            try ( EduDAO<Course> cDAO = new JpaEduDAO<>()) {
                cDAO.save(newCourse);
            } catch (Exception e) {
                System.out.println(e);
            }
            //Update the user as the main teacher
            try ( EduDAO<User> uDAO = new JpaEduDAO<>()) {
                uDAO.update(user);
                addNewCourseBacklog.setText("Tárgy hozzáaadása sikeres!");
            } catch (Exception e) {
                System.out.println(e);
                addNewCourseBacklog.setText("Nem sikerült kapcsolódni az adatbázishoz!");
            }
        }else addNewCourseBacklog.setText("Nem töltöttél ki minden mezőt!");
    }

    @FXML
    void addCourseBackButtonPushed() {
        userMode = CurrentUser.getInstance().getCurrent().getType().toString();
        windowLoader("/fxml/Welcome" + userMode + ".fxml", "Welcome" + userMode);
        Stage stage = (Stage) addCourseBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void uploadExitButtonPushed() {
        handleExit(true);
    }
}
