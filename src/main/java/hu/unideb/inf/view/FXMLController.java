package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.Note;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.entity.User.userType;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.modell.Simulation;
import hu.unideb.inf.util.EduDAO;
import hu.unideb.inf.util.JpaEduDAO;
import hu.unideb.inf.util.JpaUserDAO;
import hu.unideb.inf.util.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
    public String userMode;
    
    //LOGIN START
    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;
    
    @FXML
    private Button loginLoginButton;
    
    @FXML
    void loginExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void loginLoginButtonPushed() {
        String username = loginUsername.getText();
        String password = loginPassword.getText();
        User user = CurrentUser.getInstance(username).getCurrent();
        if(user != null && password.equals(user.getCode())){
                userMode = user.getType().toString();
                windowLoader("/fxml/Welcome"+userMode+".fxml", "Welcome"+userMode);
                 Stage stage = (Stage) loginLoginButton.getScene().getWindow();
                 stage.close();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Incorrect credentials");
            alert.setHeaderText("Incorrect username or password");
            alert.showAndWait();
            System.out.println("User not found or wrong password!");
        }
    }
    //LOGIN END
    
    //WELCOME START
    @FXML
    private Button welcomeDownloadButton;
    
    @FXML
    void welcomeDownloadButtonPushed() {
        windowLoader("/fxml/Download.fxml", "Download");
        Stage stage = (Stage) welcomeDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void welcomeUploadButtonPushed() {
        windowLoader("/fxml/Upload.fxml", "Upload");
        Stage stage = (Stage) welcomeDownloadButton.getScene().getWindow();
        stage.close();
    }
    //WELCOME END
    
    //WELCOME ADMIN START
    
      @FXML
    private Button welcomeAdminDownloadButton;

    @FXML
    void welcomeAdminDownloadButtonPushed() {
        windowLoader("/fxml/Download.fxml", "Download");
        Stage stage = (Stage) welcomeAdminDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeAdminExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void welcomeAdminUploadButtonPushed() {
        windowLoader("/fxml/Upload.fxml", "Upload");
        Stage stage = (Stage) welcomeAdminDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeAdminUsersButtonPushed() {
        windowLoader("/fxml/Admin.fxml", "Add user");
        Stage stage = (Stage) welcomeAdminDownloadButton.getScene().getWindow();
        stage.close();
    }
    
    //WELCOME ADMIN STOP
    
    //DOWNLOAD START
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
        System.exit(0);
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
    
    //UPLOAD START
    
     @FXML
    private ListView<?> uploadCourseList;

    @FXML
    private TextField uploadCourseInput;

    @FXML
    private ListView<?> uploadNoteList;

    @FXML
    private TextField uploadCouseInput;

    @FXML
    private TextArea uploadLink;

    @FXML
    private TextField uploadFileName;
    
    @FXML
    private Button uploadBackButton;

    @FXML
    void uploadBackButtonPushed() {
        userMode = CurrentUser.getInstance().getCurrent().getType().toString();
        windowLoader("/fxml/Welcome"+userMode+".fxml","Welcome"+userMode);
        Stage stage = (Stage) uploadBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void uploadCourseOpenButtonPushed() {
        System.out.println("You clicked me!");
        //Save test data
        Simulation simulation = Simulation.getInstance();
        System.out.println("Done!");
    }

    @FXML
    void uploadExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void uploadNoteOpenButtonPushed() {

    }

    @FXML
    void uploadUploadButton() {

    }
    
    //UPLOAD END
    
    
    
     void windowLoader(String location, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
     /********/
    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField birthDate;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField username;

    @FXML
    private RadioButton isStudent;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton isTeacher;

    @FXML
    private RadioButton isAdmin;
    
    /*void addUser(userType tipus){
        newUsr = new User(tipus, 
                        username.getText(), 
                        firstName.getText(),
                        lastName.getText(), 
                        LocalDate.parse(birthDate.getText()), 
                        emailAddress.getText(), 
                        password.getText()
        );
        System.out.println(newUsr.toString());
    }*/
    
    @FXML
    void addButtonPushed() {
        User newUsr;
        userType tipus = User.userType.TEACHER;
        //Course targy = new Course("SzoftDev");
        if(isStudent.isSelected()){
            tipus = User.userType.STUDENT;
        }else if(isTeacher.isSelected()){
            tipus = User.userType.TEACHER;
        }else if(isAdmin.isSelected()){
            tipus = User.userType.ADMIN;
        }
        
        newUsr = new User(tipus, 
                        username.getText(), 
                        firstName.getText(),
                        lastName.getText(), 
                        LocalDate.parse(birthDate.getText()), 
                        emailAddress.getText(), 
                        password.getText()
        );
        
        
        //newUsr.addCourse(targy);
        try (EduDAO uDAO = new JpaEduDAO<User>()) {
            uDAO.save(newUsr);
        }
        System.out.println(newUsr.toString());
        
    }

    @FXML
    void adminExitButtonPushed() {
        System.exit(0);
    }
}
