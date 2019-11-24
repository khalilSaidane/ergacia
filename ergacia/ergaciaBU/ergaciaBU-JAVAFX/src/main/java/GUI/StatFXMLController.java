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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import services.serviceStatRemote;

/**
 * FXML Controller class
 *
 * @author shaym
 */
public class StatFXMLController implements Initializable {
    @FXML
    private HBox stat;
    @FXML
    private Text L1;
    @FXML
    private Text L2;
    @FXML
    private Button cand;
    @FXML
    private Button rec;
    @FXML
    private Button entre;
    @FXML
    private Button add;
    @FXML
    private Text L5;
    @FXML
    private Text L3;
    @FXML
    private Text L4;
    @FXML
    private Text L6;
    @FXML
    private Text L7;
    @FXML
    private Text L8;
    @FXML
    private Text L9;
    @FXML
    private Text L10;
    @FXML
    private Tab dateactuel;
    @FXML
    private Tab datechoisi;
    @FXML
    private Button next;
    @FXML
    private TextField choosed;
    @FXML
    private Button choose;
    @FXML
    private TextField Start;
    @FXML
    private TextField End;
    @FXML
    private Button ch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    	
    	Hashtable ht=new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
     	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context",true);
	
                      

			
		
		
		
    }    

    @FXML
    private void statis(MouseEvent event) {
    }


    @FXML
    private void Candidate(ActionEvent event) {
    }

    @FXML
    private void Recrutement(ActionEvent event) {
    }

    @FXML
    private void skills(ActionEvent event) {
    }

    @FXML
    private void adresse(ActionEvent event) {
    }

    @FXML
    private void Next(ActionEvent event) {
        
         Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root;
                
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/StatLeterarFXML.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Ergacia");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
          
        }
              
        
    }


    @FXML
    private void Choose(ActionEvent event) {
    	 // TODO
    	
    	Hashtable ht=new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
     	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context",true);
		Context ctx;
		try {
			ctx = new InitialContext(ht);
			Object obj = ctx.lookup("/ergaciaBU-ear/ergaciaBU-ejb/serviceStat!services.serviceStatRemote");
			serviceStatRemote service = (serviceStatRemote) obj;
		      
	        
			


	        
	        	//SELECT s.content , count(s.content) FROM Skill s WHERE s.speciality = 'informatique' group by s.content
	        	//select `content` ,count(`content`) from skill WHERE `speciality`='informatique' group by (`content`)
	        	//List<Offer> result = quer.setParameter("year", year).getResultList();
	        	//float result = service.statCandidat();  
	        	//System.out.println(result);
	        	
		      
		      
		      
	
		
                L6.setText(Float.toString(service.statEntre(choosed.getText())));
                L7.setText(Float.toString(service.statEntr(choosed.getText())));
                L8.setText(Float.toString(service.statEnt(choosed.getText())));
                L9.setText(Float.toString(service.statEn(choosed.getText())));
                L10.setText(Float.toString(service.statE(choosed.getText())));
		} catch (NamingException e) {
		}
    }

    @FXML
    private void ChooseSE(ActionEvent event) {
    	
    	 // TODO
    	Hashtable ht=new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
     	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context",true);
		Context ctx;
		try {
			ctx = new InitialContext(ht);
			Object obj = ctx.lookup("/ergaciaBU-ear/ergaciaBU-ejb/serviceStat!services.serviceStatRemote");
			serviceStatRemote service = (serviceStatRemote) obj;
			L1.setText(Float.toString(service.statEntreprise(Start.getText(),End.getText())));
                        L2.setText(Float.toString(service.statEntrepris(Start.getText(),End.getText())));
                        L3.setText(Float.toString(service.statEntrepri(Start.getText(),End.getText())));
                        L4.setText(Float.toString(service.statEntrepr(Start.getText(),End.getText())));
                        L5.setText(Float.toString(service.statEntrep(Start.getText(),End.getText())));
                      

			
			
			
		} catch (NamingException e) {
		}
    
}

    @FXML
    private void Start(ActionEvent event) {
    }

    @FXML
    private void End(ActionEvent event) {
    }

    @FXML
    private void choosed(ActionEvent event) {
    }

}