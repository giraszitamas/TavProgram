package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.Note;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.modell.Simulation;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UploadController extends LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Course> list = FXCollections.observableArrayList();
        Set<Course> createdCourses = CurrentUser.getInstance().getCurrent().getCourses();
        createdCourses.forEach((course) -> {
            list.add(course);
        });
        uploadCourseList.setItems(list);
    }

    @FXML
    private ListView<Course> uploadCourseList;

    @FXML
    private TextArea uploadLink;

    @FXML
    private Button uploadBackButton;

    @FXML
    private TextField uploadFileName;

    @FXML
    void simulationPushed() {
        System.out.println("You clicked me!");
        //Save test data
        Simulation simulation = Simulation.getInstance();
        System.out.println("Done!");
    }

    @FXML
    void uploadBackButtonPushed() {
        userMode = CurrentUser.getInstance().getCurrent().getType().toString();
        windowLoader("/fxml/Welcome"+userMode+".fxml","Welcome"+userMode);
        Stage stage = (Stage) uploadBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void uploadCourseOpenButtonPushed() {
        ObservableList<Course> list = FXCollections.observableArrayList();
        Set<Course> createdCourses = CurrentUser.getInstance().getCurrent().getCourses();
        createdCourses.forEach((course) -> {
            list.add(course);
        });
        uploadCourseList.setItems(list);
    }

    @FXML
    void uploadExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void uploadUploadButton() {
        //Get data
        String name = uploadFileName.getText();
        String value = uploadLink.getText();
        Course course = uploadCourseList.getSelectionModel().getSelectedItem();
        //create new note
        Note newNote = new Note(name, value);
        course.addNote(newNote);
        //Save this new note
        EduDAO nDAO = new JpaEduDAO<Note>();
        EduDAO cDAO = new JpaEduDAO<Course>();
        cDAO.update(course);
        nDAO.close();
        cDAO.close();
    }
}
