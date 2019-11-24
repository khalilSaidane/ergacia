/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author HP I3
 */
public class BaseFXMLController implements Initializable {

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
    private JFXButton logOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                       
        
        
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
      User user  = LoginFXMLController.CurrentUser;
      if(user.getRole().equals("Candidat")){
    	  try {
	            Parent root = FXMLLoader.load(getClass().getResource("/GUI/CondidatProfileFXML.fxml"));
	            Profilbtn.getScene().setRoot(root);
	        } catch (IOException ex) {
	            
	        }
      }

    }


    @FXML
    private void MessagebtnOnclick(ActionEvent event) {
    }

    @FXML
    private void JobsBtnOnClick(ActionEvent event) {
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
    
}
