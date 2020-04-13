package hu.unideb.inf.view;

import hu.unideb.inf.entity.Course;
import hu.unideb.inf.entity.User;
import hu.unideb.inf.modell.Simulation;
import hu.unideb.inf.util.JpaUserDAO;
import hu.unideb.inf.util.UserDAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class FXMLController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private TextField downSearch;

    @FXML
    private ListView<?> downOptions;
    
    @FXML
    private Button downLoad;

    @FXML
    private TextArea downLink;
    
    @FXML
    private Button upLoad;

    @FXML
    private TextField upSearch;

    @FXML
    private ListView<?> UpOptions;

    @FXML
    private TextArea upLink;

    @FXML
    private TextField upName;

    @FXML
    void downLoadButtenPushed() {
         System.out.println("You clicked me!");
        
        //Save test data
        Simulation simulation = new Simulation();
        
        System.out.println("Done!");

    }

    @FXML
    void upLoadButtonPushed() {

    }
}
