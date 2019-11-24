/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;

import entity.Condidate_Profile;
import entity.Entreprise_Profile;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author HP I3
 */
public class FXMLEntrepriseModelController implements Initializable {

    @FXML
    private ImageView ImgVEntreprise;
    @FXML
    private Hyperlink EtrepriseLink;
    @FXML
    private JFXButton Followbtn;
    public static Entreprise_Profile profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	EtrepriseLink.setText(profil.getHeadquarter()); 
    	//ImgVEntreprise.setImage();
    }    

    @FXML
    private void GoToEtrepriseProfil(ActionEvent event) {
    }

    @FXML
    private void OnFollowClick(ActionEvent event) {
    }
    
}
