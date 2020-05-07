package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.Note;
import hu.unideb.inf.modell.CurrentUser;
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

public class DownloadController extends LoginController implements Initializable {
    
    //DOWNLOAD START
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
        downloadCourseList.setItems(list);
    }
    
    @FXML
    private ListView<Course> downloadCourseList;

    @FXML
    private TextField downloadCourseInput;

    @FXML
    private ListView<Note> downloadNoteList;

    @FXML
    private TextField downloadCouseInput;

    @FXML
    private TextArea downloadLink;
    
    @FXML
    private Button downloadBackButton;

    @FXML
    void downloadBackButtonPushed() {
        userMode = CurrentUser.getInstance().getCurrent().getType().toString();
        windowLoader("/fxml/Welcome"+userMode+".fxml","Welcome"+userMode);
        Stage stage = (Stage) downloadBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void downloadExitButtonPushed() {
        handleExit(true);
    }

    @FXML
    void downloadNoteOpenButtonPushed() {
        ObservableList<Note> list = FXCollections.observableArrayList();
        //Get a course and it's notes
        var notes = downloadCourseList.getSelectionModel().getSelectedItem().getNotes();
        //Add notes to the list
        for(Note note : notes){
            list.add(note);
        }
        downloadNoteList.setItems(list);
    }
    
    @FXML
    void downloadValueOpenButtonPushed() {
        //Get a note's link
        var link = downloadNoteList.getSelectionModel().getSelectedItem().getValue();
        //Add notes to the list
        downloadLink.setText(link);
    }
    //DOWNLOAD END
}