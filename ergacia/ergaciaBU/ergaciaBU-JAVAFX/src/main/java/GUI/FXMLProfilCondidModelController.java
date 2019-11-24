/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;

import entity.Condidate_Profile;

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
public class FXMLProfilCondidModelController implements Initializable {

    @FXML
    private ImageView ImgVProfil;
    @FXML
    private Hyperlink CandidNameTF;
    @FXML
    private JFXButton Subscbtn;
    
    public static Condidate_Profile profil;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
        // TODO
    	CandidNameTF.setText(profil.getUserCondidate().getFirst_name()+" "+profil.getUserCondidate().getLast_name());
    	//ImageV.setImage(profil.getImage())
    	
    }    

    @FXML
    private void OnsubscribeClick(ActionEvent event) {
    }
    
}
