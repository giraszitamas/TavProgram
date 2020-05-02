package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.Note;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.util.HibernateUtil;
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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DownloadController extends LoginController implements Initializable {
    
    //DOWNLOAD START
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load the user's courses - Test if this works
        ObservableList<Course> list = FXCollections.observableArrayList();
        if(CurrentUser.getInstance().getCurrent().getType().toString().equals("ADMIN")){
            Session session;
            Transaction transaction;
            session = HibernateUtil.getSessionFactory().openSession();
            Course asd;
            var hql = "FROM Course";
            Query query = session.createQuery(hql);
            List<Course> tar = query.list();
            for (int i = 0; i < tar.size(); i++) {
                list.add(tar.get(i));

        }
        }
        else{
        Set<Course> courses = CurrentUser.getInstance().getCurrent().getCourses();
        for(var course : courses){
            list.add(course);
        }       
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
    void downloadCourseOpenButtonPushed() {
        ObservableList<Course> list = FXCollections.observableArrayList();
        Set<Course> courses = CurrentUser.getInstance().getCurrent().getCourses();
        for(var course : courses){
            list.add(course);
        }
        downloadCourseList.setItems(list);
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