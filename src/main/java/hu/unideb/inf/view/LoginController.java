
package hu.unideb.inf.view;

import hu.unideb.inf.entity.User;
import hu.unideb.inf.modell.CurrentUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
    public String userMode;
    
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
        User user = CurrentUser.getInstance(username).getCurrent();
        if(user != null && password.equals(user.getCode())){
                userMode = user.getType().toString();
                windowLoader("/fxml/Welcome"+userMode+".fxml", "Welcome"+userMode);
                Stage stage = (Stage) loginLoginButton.getScene().getWindow();
                stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect credentials");
            alert.setHeaderText("Incorrect username or password");
            alert.showAndWait();
            System.out.println("User not found or wrong password!");
        }
    }
    //LOGIN END
    
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
