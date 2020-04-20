package hu.unideb.inf.view;

import hu.unideb.inf.modell.Simulation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

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
    private TextArea downLink;

    @FXML
    private Button downLoad;

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
    private TextField felhasz;

    @FXML
    private TextField jelszo;

    @FXML
    private Button belepes;

    @FXML
    private Label szoveg;

    @FXML
    private Button bezar;

    @FXML
    private TabPane rejtett;

    @FXML
    private Pane lap;

    Boolean igaz = false;

    @FXML
    void belep() {
        String flh = felhasz.getText();
        String jlsz = jelszo.getText();
        szoveg.setText("Log: " + felhasz.getText() + " " + jelszo.getText());
        if (flh.equals("megfelelo") && jlsz.equals("ez is")) {
            rejtett.setStyle("visibility: visible;");
            lap.setStyle("visibility: hidden;");
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
    void kilep() {
        System.exit(0);
    }

    @FXML
    void upLoadButtonPushed() {

    }

}
