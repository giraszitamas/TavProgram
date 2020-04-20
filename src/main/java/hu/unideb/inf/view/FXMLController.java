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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField downSearch;

    @FXML
    private ListView<?> downOptions;

    @FXML
    private TextArea downLink;

    @FXML
    private Button downLoad;
    
    @FXML
    private Button downClose;

    @FXML
    private TextField upSearch;

    @FXML
    private ListView<?> UpOptions;

    @FXML
    private TextArea upLink;

    @FXML
    private TextField upName;

    @FXML
    private Button upLoad;
    
    @FXML
    private Button upClose;
    
    @FXML
    void loginExitButtonPushed() {
        System.exit(0);
    }

    @FXML
    void loginLoginButtonPushed() {
        String flh = loginUsername.getText();
        String jlsz = loginPassword.getText();
        if (flh.equals("megfelelo") && jlsz.equals("ez is")) {
            windowLoader("/fxml/Scene.fxml", "Felulet");
        }

    }

    @FXML
    void downLoadButtenPushed() {
        System.out.println("You clicked me!");
        
        //Save test data
        Simulation simulation = Simulation.getInstance();
        
        System.out.println("Done!");
    }

    @FXML
    void exit() {
        System.exit(0);
    }
    @FXML
    void upExit() {
        System.exit(0);
    }
    @FXML
    void downExit() {
        System.exit(0);
    }

    @FXML
    void upLoadButtonPushed() {
        
    }
     void windowLoader(String location, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
