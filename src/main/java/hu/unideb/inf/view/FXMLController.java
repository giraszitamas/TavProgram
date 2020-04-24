package hu.unideb.inf.view;

import hu.unideb.inf.entity.User;
import hu.unideb.inf.modell.Simulation;
import hu.unideb.inf.util.JpaUserDAO;
import hu.unideb.inf.util.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
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
        try (UserDAO uDAO = new JpaUserDAO()) {
            User user = uDAO.getByUsername(username);
            if(user != null && password.equals(user.getCode())){
                windowLoader("/fxml/Welcome.fxml", "Welcome");
                Stage stage = (Stage) loginLoginButton.getScene().getWindow();
                stage.close();
            }else{
                System.out.println("User not found or wrong password!");
            }
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
    
    //DOWNLOAD START
      @FXML
    private ListView<?> downloadCourseList;

    @FXML
    private TextField downloadCourseInput;

    @FXML
    private ListView<?> downloadNoteList;

    @FXML
    private TextField downloadCouseInput;

    @FXML
    private TextArea downloadLink;
    
    @FXML
    private Button downloadBackButton;

    @FXML
    void downloadBackButtonPushed() {
        windowLoader("/fxml/Welcome.fxml","Welcome");
        Stage stage = (Stage) downloadBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void downloadCourseOpenButtonPushed() {
        System.out.println("You clicked me!");
        //Save test data
        Simulation simulation = Simulation.getInstance();
        
        System.out.println("Done!");
    }

    @FXML
    void downloadExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void downloadNoteOpenButtonPushed() {
        //Testing for findByName
        try (UserDAO uDAO = new JpaUserDAO()) {
            User user = uDAO.getByUsername("Tesztellek");
            if(user != null){
                System.out.println(user.toString());
            }else{
                System.out.println("User not found!");
            }
        }
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
        windowLoader("/fxml/Welcome.fxml","Welcome");
        Stage stage = (Stage) uploadBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void uploadCourseOpenButtonPushed() {

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
            e.printStackTrace();
        }
    }
     
}
