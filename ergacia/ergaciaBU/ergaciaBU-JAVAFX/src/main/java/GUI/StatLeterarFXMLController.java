/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import services.serviceStatRemote;


/**
 * FXML Controller class
 *
 * @author shaym
 */
public class StatLeterarFXMLController implements Initializable {

    @FXML
    private HBox stat;
    @FXML
    private Button entre;
    @FXML
    private Button cand;
    @FXML
    private Button rec;
    @FXML
    private Button add;
    @FXML
    private Tab dateactuel;
    @FXML
    private Text F5;
    @FXML
    private Text F2;
    @FXML
    private Text F3;
    @FXML
    private Text F4;
    @FXML
    private Text F1;
    @FXML
    private TextField Start;
    @FXML
    private TextField End;
    @FXML
    private Button ChooseSE;
    @FXML
    private Tab datechoisi;
    @FXML
    private Text F6;
    @FXML
    private Text F7;
    @FXML
    private Text F8;
    @FXML
    private Text F9;
    @FXML
    private Text F10;
    @FXML
    private TextField choisiry;
    @FXML
    private Button choose;
    @FXML
    private Button next;
    @FXML
    private Button Back;

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
    private void skills(ActionEvent event) {
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
    private void adresse(ActionEvent event) {
    }

    @FXML
    private void Start(ActionEvent event) {
    }

    @FXML
    private void End(ActionEvent event) {
    }

    @FXML
    private void ChooseSE(ActionEvent event) {
        Hashtable ht=new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
     	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context",true);
		Context ctx;
		try {
			ctx = new InitialContext(ht);
			Object obj = ctx.lookup("/ergaciaBU-ear/ergaciaBU-ejb/serviceStat!services.serviceStatRemote");
			serviceStatRemote service = (serviceStatRemote) obj;
			F1.setText(Float.toString(service.statEntrepriseF(Start.getText(),End.getText())));
                        F2.setText(Float.toString(service.statEntrepriseA(Start.getText(),End.getText())));
                        F3.setText(Float.toString(service.statEntrepriseG(Start.getText(),End.getText())));
                        F4.setText(Float.toString(service.statEntrepriseAr(Start.getText(),End.getText())));
                        F5.setText(Float.toString(service.statEntrepriseFAR(Start.getText(),End.getText())));
                      

			
			
			
		} catch (NamingException e) {
		}
    }

    @FXML
    private void choisiry(ActionEvent event) {
      
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
                F6.setText(Float.toString(service.statEL(choisiry.getText())));
                F7.setText(Float.toString(service.statEnL(choisiry.getText())));
                F8.setText(Float.toString(service.statEntL(choisiry.getText())));
                F9.setText(Float.toString(service.statEntrL(choisiry.getText())));
                F10.setText(Float.toString(service.statEntreL(choisiry.getText())));
		} catch (NamingException e) {
		}
        
        
        
    }

    @FXML
    private void Back(ActionEvent event) {
          
         Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root;
                
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/StatFXML.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Ergacia");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
          
        }
    }

    @FXML
    private void Next(ActionEvent event) {
          
         Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root;
                
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/StatMedcineFXML.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Ergacia");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
          
        }
    }
    
}
