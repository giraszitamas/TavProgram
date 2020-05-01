package hu.unideb.inf.view;

import hu.unideb.inf.entity.User;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.util.EduDAO;
import hu.unideb.inf.util.JpaEduDAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController extends LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
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
    void adminAddBackButtonPushed() {
        userMode = CurrentUser.getInstance().getCurrent().getType().toString();
        windowLoader("/fxml/Welcome"+userMode+".fxml","Welcome"+userMode);
        Stage stage = (Stage) adminAddBackButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void addButtonPushed() {
        User newUsr;
        User.userType tipus = User.userType.TEACHER;
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
        handleExit(true);
    }
    //ADD END
    
    //ADMIN END
}
