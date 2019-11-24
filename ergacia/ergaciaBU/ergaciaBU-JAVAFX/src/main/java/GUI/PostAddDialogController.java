/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import services.servicePublicationRemote;
import entity.Post;
/**
 * FXML Controller class
 *
 * @author HP I3
 */
public class PostAddDialogController implements Initializable {

    @FXML
    private JFXTextField titleTF;
    @FXML
    private JFXTextArea ContentTF;
    @FXML
    private MediaView MediaV;
    @FXML
    private Button PostBtn;
    @FXML
    private JFXButton AddImageBtn;
    @FXML
    private JFXButton AddVideoBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        

    }    

    @FXML
    private void Post(ActionEvent event) throws NamingException {
    	Post p = new Post();
    	if(MediaV.getFitHeight()==0)
    	
    	{
    	p.setTitre(titleTF.getText());
    	p.setContent(ContentTF.getText());
    	p.setMultimedia_url("");// a complete
    	p.setType("text");
    	p.setUsersPosts(LoginFXMLController.CurrentUser);
    	Date  d = new Date();
    	p.setDate(new  Timestamp(d.getYear()+1900,d.getMonth(),d.getDate(),d.getHours(),d.getMinutes(),d.getSeconds(),0));
    	
    	Hashtable ht=new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
     	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context",true);
		
		
			Context ctx = new InitialContext(ht);	
			Object obj = ctx.lookup("/ergaciaBU-ear/ergaciaBU-ejb/servicePublication!services.servicePublicationRemote");
			servicePublicationRemote service = (servicePublicationRemote) obj;
		    service.creatPost(p); 
		    PublicationsFXMLController.PostForm.close();
    	}
    	
    	
    }

    @FXML
    private void AddImageAction(ActionEvent event) {
    	MediaV.setFitWidth(400);
    	MediaV.setFitHeight(300);
    }

    @FXML
    private void AddVideoAction(ActionEvent event) {
    	MediaV.setFitWidth(400);
    	MediaV.setFitHeight(300);
    }
    
}
