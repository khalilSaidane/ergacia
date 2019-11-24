/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.mapping.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.serviceStatRemote;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;




/**
 *
 * @author HP I3
 */
public class Main extends Application {
    
    public static Stage pStage;

    @Override
    public void start(Stage primaryStage) throws IOException, NamingException {
        
      
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));//kanet Login
            Scene scene = new Scene( root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ergacia");
            pStage = primaryStage;
            primaryStage.show();
            
            Hashtable ht=new Hashtable();
    		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
         	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
    		ht.put("jboss.naming.client.ejb.context",true);
    		Context ctx;
            ctx = new InitialContext(ht);
			Object obj = ctx.lookup("/ergaciaBU-ear/ergaciaBU-ejb/serviceStat!services.serviceStatRemote");
			serviceStatRemote service = (serviceStatRemote) obj;
			
			//List result = service.statCandidat();
			//Map<Integer, String> result = service.statCandidat();  
			 /*Set cles = result.keySet();
			 Iterator it = cles.iterator();
			 while (it.hasNext()){
			    Object cle = it.next(); // tu peux typer plus finement ici
			    Object valeur = map.get(cle); // tu peux typer plus finement ici
			 }*/
			 //System.out.println(result);
		      /*  Iterator iterator = result.entrySet().iterator();
		        while (iterator.hasNext()) {
		          Map.Entry mapentry = (Map.Entry) iterator.next();
		          System.out.println("cl�: "+mapentry.getKey()
		                            + " | valeur: " + mapentry.getValue());
		        } */
			 
            //result.forEach((n) -> System.out.println(n));
         
			/* Iterator iterator = result.entrySet().iterator();
		        while (iterator.hasNext()) {
		          Map.Entry mapentry = (Map.Entry) iterator.next();
		          System.out.println("cl�: "+mapentry.getKey()
		                            + " | valeur: " + mapentry.getValue());
		        }*/
      
    
            primaryStage.show(); 
        } 

    

    /**
     * @param args the command line arguments
     * @throws NamingException 
     */
    public static void main(String[] args) throws NamingException {
        launch(args);
    	
        
        
    }

}
