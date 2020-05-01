/*
package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.Note;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.entity.User.userType;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.modell.Simulation;
import hu.unideb.inf.util.CourseDAO;
import hu.unideb.inf.util.EduDAO;
import hu.unideb.inf.util.JpaCourseDAO;
import hu.unideb.inf.util.JpaEduDAO;
import hu.unideb.inf.util.JpaUserDAO;
import hu.unideb.inf.util.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
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
    
    //LOGIN START X
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
    
    //WELCOME STUDENT START X
    @FXML
    private Button welcomeDownloadButton;
    
     @FXML
    void welcomeLogoutButtonPushed() {
        windowLoader("/fxml/Login.fxml", "Login");
        Stage stage = (Stage) welcomeDownloadButton.getScene().getWindow();
        stage.close();
    }
    
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
    
    @FXML
    void studentCourseLogIn() {
        windowLoader("/fxml/CourseLogIn.fxml", "Course Log In");
        Stage stage = (Stage) welcomeDownloadButton.getScene().getWindow();
        stage.close();
    }
    //WELCOME STUDENT END
    
    //WELCOME TEACHER START X
    @FXML
    private Button welcomeTeacherDownloadButton;
      
       @FXML
    void welcomeTeacherLogoutButtonPushed() {
        windowLoader("/fxml/Login.fxml", "Login");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeTeacherDownloadButtonPushed() {
        windowLoader("/fxml/Download.fxml", "Download");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeTeacherExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void welcomeTeacherUploadButtonPushed() {
        windowLoader("/fxml/Upload.fxml", "Upload");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void welcomeTeacherUsersButtonPushed() {
        windowLoader("/fxml/Teacher.fxml", "Add user");
        Stage stage = (Stage) welcomeTeacherDownloadButton.getScene().getWindow();
        stage.close();
    }
    //WELCOME TEACHER STOP
    
    //WELCOME ADMIN START X
    @FXML
    private Button welcomeAdminDownloadButton;
      
       @FXML
    void welcomeAdminLogoutButtonPushed() {
        windowLoader("/fxml/Login.fxml", "Login");
        Stage stage = (Stage) welcomeAdminDownloadButton.getScene().getWindow();
        stage.close();
    }

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
            Alert alert = new Alert(AlertType.INFORMATION);
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
     
    //ADMIN START
    //ADD START
    @FXML
    private TextField adminAddlastName;

    @FXML
    private TextField adminAddfirstName;

    @FXML
    private TextField adminAddbirthDate;

    @FXML
    private TextField adminAddemailAddress;

    @FXML
    private TextField adminAddusername;

    @FXML
    private RadioButton adminAddisStudent;

    @FXML
    private PasswordField adminAddpassword;

    @FXML
    private RadioButton adminAddisTeacher;

    @FXML
    private RadioButton adminAddisAdmin;
    
    @FXML
    private Button adminAddBackButton;
    
//    void addUser(userType tipus){
//        newUsr = new User(tipus, 
//                        username.getText(), 
//                        firstName.getText(),
//                        lastName.getText(), 
//                        LocalDate.parse(birthDate.getText()), 
//                        emailAddress.getText(), 
//                        password.getText()
//        );
//        System.out.println(newUsr.toString());
//    }
    
     @FXML
    void adminAddBackButtonPushed() {
        userMode = CurrentUser.getInstance().getCurrent().getType().toString();
        windowLoader("/fxml/Welcome"+userMode+".fxml","Welcome"+userMode);
        Stage stage = (Stage) adminAddBackButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void addButtonPushed() {
        User newUsr;
        userType tipus = User.userType.TEACHER;
        //Course targy = new Course("SzoftDev");
        if(adminAddisStudent.isSelected()){
            tipus = User.userType.STUDENT;
        }else if(adminAddisTeacher.isSelected()){
            tipus = User.userType.TEACHER;
        }else if(adminAddisAdmin.isSelected()){
            tipus = User.userType.ADMIN;
        }
        
        newUsr = new User(tipus, 
                        adminAddusername.getText(), 
                        adminAddfirstName.getText(),
                        adminAddlastName.getText(), 
                        LocalDate.parse(adminAddbirthDate.getText()), 
                        adminAddemailAddress.getText(), 
                        adminAddpassword.getText()
        );
        
        
        //newUsr.addCourse(targy);
        try (EduDAO uDAO = new JpaEduDAO<User>()) {
            uDAO.save(newUsr);
        }
        System.out.println(newUsr.toString());
        
    }
    
    @FXML
    void adminAddExitButtonPushed() {
        System.exit(0);
    }
    //ADD END
    
    @FXML
    void adminExitButtonPushed() {
        System.exit(0);
    }
    //ADMIN END
	
	
    // TEACHER START
    // TEACHER ADD STUDENT
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
        //TODO: Select a course somehow. - if it will be needed...
//        //Add the course to the users
//        User selectedUser = foundStudents.getSelectionModel().getSelectedItem();
//        selectedUser.addCourse(subject);
//        try (EduDAO uDAO = new JpaEduDAO<User>()) {
//            uDAO.update(selectedUser);
//        }
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
        System.exit(0);
    }
    // TEACHER ADD STUDENT END
    // TEACHER END
    
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
}
*/