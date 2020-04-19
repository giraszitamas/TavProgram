package hu.unideb.inf.view;

import hu.unideb.inf.modell.Simulation;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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

    }

    @FXML
    void upLoadButtonPushed() {

    }
}
/*

    arg

*/