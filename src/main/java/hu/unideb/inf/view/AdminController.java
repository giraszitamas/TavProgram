package hu.unideb.inf.view;

import hu.unideb.inf.entity.User;
import hu.unideb.inf.entity.User.userType;
import hu.unideb.inf.modell.CurrentUser;
import hu.unideb.inf.util.EduDAO;
import hu.unideb.inf.util.JpaEduDAO;
import hu.unideb.inf.util.JpaUserDAO;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    @FXML
    private Label adminAddisSuccessfull;
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
        windowLoader("/fxml/Welcome" + userMode + ".fxml", "Welcome" + userMode);
        Stage stage = (Stage) adminAddBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addButtonPushed() {
        User newUsr;
        User.userType tipus = User.userType.TEACHER;
        //Course targy = new Course("SzoftDev");
        if (adminAddisStudent.isSelected()) {
            tipus = User.userType.STUDENT;
        } else if (adminAddisTeacher.isSelected()) {
            tipus = User.userType.TEACHER;
        } else if (adminAddisAdmin.isSelected()) {
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
        try (EduDAO<User> uDAO = new JpaEduDAO<>()) {
            if(uDAO.save(newUsr))
                adminAddisSuccessfull.setText("Sikeresen hozzá lett adva!");
            else adminAddisSuccessfull.setText("Sikertelen hozzáadás!");
        }
        System.out.println(newUsr.toString());

    }

    @FXML
    void adminAddExitButtonPushed() {
        handleExit(true);
    }
    //ADD END
    // EDIT

    @FXML
    private TextField adminEditLastName;

    @FXML
    private TextField adminEditFirstName;

    @FXML
    private TextField adminEditBirthDate;

    @FXML
    private TextField adminEditEmailAddress;

    @FXML
    private TextField adminEditUserName;

    @FXML
    private RadioButton adminEditisStudent;

    @FXML
    private PasswordField adminEditPassword;

    @FXML
    private RadioButton adminEditisTeacher;

    @FXML
    private RadioButton adminEditisAdmin;

    @FXML
    private TextField serachForEditing;
    
    @FXML
    private Text adminEditIsSuccessfull;

    boolean canEdit = false;
    User underEditUser;
    @FXML
    void getDataForEdit() {
        canEdit = true;
        String idForGetting = serachForEditing.getText();
        try (JpaUserDAO getterForEdit = new JpaUserDAO()) {
            underEditUser = getterForEdit.getById(Long.parseLong(idForGetting));
            adminEditLastName.setText(underEditUser.getLastName());
            adminEditFirstName.setText(underEditUser.getFirstName());
            adminEditBirthDate.setText(String.valueOf(underEditUser.getBirthDate()));
            adminEditEmailAddress.setText(underEditUser.getEmail());
            adminEditUserName.setText(underEditUser.getUsername());
            adminEditPassword.setText(underEditUser.getCode());

            userType typeOfUser = underEditUser.getType();

            if (null != typeOfUser) switch (typeOfUser) {
                case ADMIN:
                    adminEditisAdmin.setSelected(true);
                    break;
                case TEACHER:
                    adminEditisTeacher.setSelected(true);
                    break;
                case STUDENT:
                    adminEditisStudent.setSelected(true);
                    break;
                default:
                    break;
            }
            /*List<User> tar = getterForEdit.getByPartUsername("ma");
            for (User tar1 : tar) {
                System.out.println(tar1.toString());
            }*/
        }
        
    }
//userType type, String username, String FirstName, String LastName, LocalDate birthDate, String email, String code
    // Ne csak id alapján
    @FXML
    void updateButtonPushed() {
        if (canEdit) {
            try (JpaEduDAO<User> updateForEditing = new JpaEduDAO<>()) {
                final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
                underEditUser.setBirthDate(LocalDate.parse(adminEditBirthDate.getText()));
                underEditUser.setFirstName(adminEditFirstName.getText());
                underEditUser.setLastName(adminEditLastName.getText());
                underEditUser.setEmail(adminEditEmailAddress.getText());
                underEditUser.setUsername(adminEditUserName.getText());
                underEditUser.setCode(adminEditPassword.getText());
                if(adminEditisAdmin.isSelected())underEditUser.setType(User.userType.ADMIN);
                else if(adminEditisTeacher.isSelected())underEditUser.setType(User.userType.TEACHER);
                else if(adminEditisStudent.isSelected())underEditUser.setType(User.userType.STUDENT);
                //Save the updated user
                if(updateForEditing.update(underEditUser))
                    adminEditIsSuccessfull.setText("Sikeres szerkesztés!");
                else adminEditIsSuccessfull.setText("Sikeretlen szerkesztés!");

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR!");
            alert.setHeaderText("There is no id given or you do not get data before!");
            alert.showAndWait();
        }

    }
    //
    //
    //ALma
    @FXML
    void deleteButtonPushed() {
        if (canEdit) {
            try (EduDAO<User> updateForEditing = new JpaEduDAO<User>()) {
                if(underEditUser.getId() == (CurrentUser.getInstance().getCurrent().getId()))
                    adminEditIsSuccessfull.setText("Önmagát nem törölheti!");
                else
                if(updateForEditing.delete(underEditUser))
                    adminEditIsSuccessfull.setText("Sikeres törlés!");
                else adminEditIsSuccessfull.setText("Sikeretlen törlés!");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR!");
            alert.setHeaderText("There is no id given or you do not get data before!");
            alert.showAndWait();
        }
    }
    // EDIT END
    //ADMIN END
}
