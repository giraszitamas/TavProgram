package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.Note;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.modell.Simulation;
import hu.unideb.inf.util.EduDAO;
import hu.unideb.inf.util.JpaEduDAO;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UploadController extends LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load courses
        ObservableList<Course> list = FXCollections.observableArrayList();
        //All if admin
        if(CurrentUser.getInstance().getCurrent().getType().toString().equals("ADMIN")){
            List<Course> courses;
            try(EduDAO<Course> cDao = new JpaEduDAO<>()){
                courses = cDao.getData(Course.class);
            }
            courses.forEach((course) -> {
                list.add(course);
            });
        }
        //Only the allowed ones if normal user
        else{
            Set<Course> createdCourses = CurrentUser.getInstance().getCurrent().getCourses();
            createdCourses.forEach((course) -> {
                list.add(course);
            });
        }
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
        saveChanges();
        userMode = CurrentUser.getInstance().getCurrent().getType().toString();
        windowLoader("/fxml/Welcome"+userMode+".fxml","Welcome"+userMode);
        Stage stage = (Stage) uploadBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void uploadExitButtonPushed() {
        saveChanges();
        handleExit(true);
    }

    @FXML
    void uploadUploadButton() {
        //Get data
        User user = CurrentUser.getInstance().getCurrent();
        String name = uploadFileName.getText();
        String value = uploadLink.getText();
        Course course = uploadCourseList.getSelectionModel().getSelectedItem();
        //create new note
        Note newNote = new Note(name, value);
        course.addNote(newNote);
        //Save this new note
        try(EduDAO<Note> nDAO = new JpaEduDAO<>()){
            nDAO.save(newNote);
        }catch(Exception e){
            System.out.println(e);
        }
        if(user.getType().toString().equals("ADMIN")){
            try(EduDAO<Course> cDAO = new JpaEduDAO<>()){
                cDAO.update(course);
            }
            //REEEEEEload courses
            //uploadCourseOpenButtonPushed();
        }
    }
    
    void saveChanges(){
        User user = CurrentUser.getInstance().getCurrent();
        try(EduDAO<Course> cDAO = new JpaEduDAO<>()){
            if(user.getType().toString().equals("ADMIN")){
                var courses = cDAO.getData(Course.class);
                courses.forEach((c) -> {
                    cDAO.update(c);
                });
            }else{
                var courses = user.getCourses();
                courses.forEach((c) -> {
                    cDAO.update(c);
                });
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
