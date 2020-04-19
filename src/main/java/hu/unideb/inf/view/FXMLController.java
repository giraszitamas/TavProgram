package hu.unideb.inf.view;

<<<<<<< HEAD
import hu.unideb.inf.modell.Simulation;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
=======
>>>>>>> Bejelentkező felület
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
<<<<<<< HEAD

public class FXMLController implements Initializable {
    private Socket socket; 
    private ObjectInputStream input; 
    private ObjectOutputStream out;

    public FXMLController() {
                try
		{ 
			socket = new Socket("127.0.0.1", 5000); 
			System.out.println("Connected"); 
			// takes input from terminal 
			input = new ObjectInputStream(socket.getInputStream()); 
			// sends output to the socket 
			out = new ObjectOutputStream(socket.getOutputStream()); 
		} 
		catch(UnknownHostException u) 
		{ 
			System.out.println(u); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
    }
    
    @Override
    protected void finalize(){
        try{ 
            out.writeObject("aaaa");
            input.close(); 
            out.close(); 
            socket.close(); 
	}catch(IOException i){ 
            System.out.println(i); 
	} finally { 
            try {
                super.finalize();
            } catch (Throwable ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    
    }
    
    
=======
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class FXMLController implements Initializable {

>>>>>>> Bejelentkező felület
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
    private TextField felhasz;

    @FXML
    private TextField jelszo;

    @FXML
    private Button belepes;

    @FXML
<<<<<<< HEAD
    private Label szoveg;
    
=======
    private Button upLoad;

    @FXML
    private TextField felhasz;

    @FXML
    private TextField jelszo;

    @FXML
    private Button belepes;

    @FXML
    private Label szoveg;

>>>>>>> Bejelentkező felület
    @FXML
    private Button bezar;

    @FXML
<<<<<<< HEAD
    void belepes(){
        String beolvas ="";
        try {
            out.writeObject("A szervernek elkuldtem: "+felhasz.getText()+" "+jelszo.getText());
            System.out.println("Itt");
            beolvas = (String)input.readObject();
            System.out.println("Itt2");
            szoveg.setText("A szerver válasza: "+beolvas);
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    @FXML
    void bezaras() {
        try {
            out.writeUTF("aaaa");
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void downLoadButtenPushed() {
         /*System.out.println("You clicked me!");
        
        //Save test data
        Simulation simulation = new Simulation();
        
        System.out.println("Done!");*/
=======
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
>>>>>>> Bejelentkező felület

    }

    @FXML
    void kilep() {
        System.exit(0);
    }

    @FXML
    void upLoadButtonPushed() {

    }

}
/*

    arg

*/