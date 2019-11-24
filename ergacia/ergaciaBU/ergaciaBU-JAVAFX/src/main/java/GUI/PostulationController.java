/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Application;
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
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import services.ServiceUserRemote;
import services.servicePostulationRemote;
import static GUI.LoginFXMLController.u;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PostulationController implements Initializable {
 @FXML
    private Button postuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      @FXML
    private void Postuler(ActionEvent event) throws NamingException, IOException {
    	Hashtable ht=new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
     	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context",true);
		Context ctx = new InitialContext(ht);
		Object obj = ctx.lookup("/ergaciaBU-ear/ergaciaBU-ejb/servicePostulation!services.servicePostulationRemote");
		servicePostulationRemote service = (servicePostulationRemote) obj;
		
                Application app=new Application();
                app.setUserapplies(u);
                service.AjouterPostulation(app);
		
		
    }
}
