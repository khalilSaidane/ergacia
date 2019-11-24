/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.effects.JFXDepthManager;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextInputDialog;





import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.jws.soap.SOAPBinding.Use;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.Condidate_Profile;
import entity.Experience;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import services.Service_Candidate_ProfileRemote;

/**
 * FXML Controller class
 *
 * @author HP I3
 */
public class CondidatProfileFXMLController implements Initializable {
    
    private User currentUser = LoginFXMLController.CurrentUser;
    private static final String FX_BACKGROUND_COLOR_WHITE = "-fx-background-color:WHITE;";
    @FXML
    private VBox vbox;

    private static int counter = 0;
    private static int step = 1;
   

    

    @FXML
    private TextField SearchTf;
    @FXML
    private Button Homebtn;
    @FXML
    private Button Notifbtn;
    @FXML
    private Button Messagesbtn;
    @FXML
    private Button Jobbtn;
    @FXML
    private Button Profilbtn;
    @FXML
    private AnchorPane Container;
    @FXML
    private Label name;
    @FXML
    private Label last_name;
    @FXML
    private JFXButton logOut;
    @FXML
    private HBox listOfExperiences;
    @FXML
    private JFXButton newExperience;
    @FXML
    private JFXButton newSkill;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	LoginFXMLController loginFXMLController = new LoginFXMLController();
    	User user =loginFXMLController.CurrentUser;
      
    	name.setText(user.getFirst_name());
    	last_name.setText(user.getLast_name());
    	
    
    }    

    @FXML
    private void OnSearch(KeyEvent event) {
    }

    @FXML
    private void HomebtnOnclick(ActionEvent event) {
    }

    @FXML
    private void NotifbtnOnclick(ActionEvent event) {
    }


    @FXML
    private void ProfilbtnOnclick(ActionEvent event) {
       

    }


    @FXML
    private void MessagebtnOnclick(ActionEvent event) {
    }

    @FXML
    private void JobsBtnOnClick(ActionEvent event) {
    }

   

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

    @FXML
    private void logoutOnclick(ActionEvent event) {
    	try {
          	 LoginFXMLController.CurrentUser = null ;
               Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
               logOut.getScene().setRoot(root);
               
               
          } catch (IOException ex) {
              System.out.println("oooops there was an error");
          }
    }

    @FXML
    private void NewExpereience(ActionEvent event){
    	Hashtable ht = new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		ht.put("java.naming.provider.url", "http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context", true);
		Context ctx;
		try {
			ctx = new InitialContext(ht);
			Service_Candidate_ProfileRemote serviceCondidat = (Service_Candidate_ProfileRemote) ctx.lookup(
					"ergaciaBU-ear/ergaciaBU-ejb/Service_Candidate_Profile!services.Service_Candidate_ProfileRemote");
			TextInputDialog dialog = new TextInputDialog();
	    	dialog.setTitle("New Expirience");
	    	dialog.setContentText("Please enter bew expirience");
	    	


	    	Optional<String> result = dialog.showAndWait();
	    	result.ifPresent(content -> {
	    		Experience experience = new Experience();
	    		experience.setContent(content);
	    		experience.setCondidate(currentUser.getProfil());
	    		serviceCondidat.addExperience(experience, currentUser.getProfil().getId());
	    		System.out.println(currentUser.getProfil());
	    		JFXButton b = new JFXButton();
	    		b.setText(experience.getContent());
	    		b.setStyle(FX_BACKGROUND_COLOR_WHITE);
	    		listOfExperiences.setSpacing(5);
	    		listOfExperiences.getChildren().add(b);
	   	    	});
			
			
	    	
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	
    }

    @FXML
    private void NewSkill(ActionEvent event) {
    }
    
}
