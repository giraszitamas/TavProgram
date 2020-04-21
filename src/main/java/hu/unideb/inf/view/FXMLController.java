package hu.unideb.inf.view;

import hu.unideb.inf.modell.Simulation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    void loginExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void loginLoginButtonPushed() {
        String flh = loginUsername.getText();
        String jlsz = loginPassword.getText();
        if (flh.equals("megfelelo") && jlsz.equals("ez is")) {
            windowLoader("/fxml/Welcome.fxml", "Welcome");
        }
    }
    //LOGIN END
   
    //WELCOME START
    @FXML
    void welcomeDownloadButtonPushed() {
        windowLoader("/fxml/Download.fxml", "Download");
    }

    @FXML
    void welcomeExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void welcomeUploadButtonPushed() {
        windowLoader("/fxml/Upload.fxml", "Upload");
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
    void downloadBackButtonPushed() {
        windowLoader("/fxml/Welcome.fxml","Welcome");
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
    void uploadBackButtonPushed() {
        windowLoader("/fxml/Welcome.fxml","Welcome");
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
