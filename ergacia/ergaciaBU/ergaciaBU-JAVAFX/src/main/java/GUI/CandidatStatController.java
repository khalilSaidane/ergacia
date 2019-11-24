
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import services.serviceStatRemote;
/**
 * FXML Controller class
 *
 * @author shaym
 */
public class CandidatStatController implements Initializable {

    @FXML
    private StackedBarChart<?, ?> chart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private TextField start1;
    @FXML
    private TextField end;
    @FXML
    private StackedBarChart<?, ?> chart1;
    @FXML
    private NumberAxis y1;
    @FXML
    private CategoryAxis x1;
    @FXML
    private TextField date;
    @FXML
    private Button Choosed;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    
    
    }    

    @FXML
    private void start(ActionEvent event) {
    }

    @FXML
    private void end(ActionEvent event) {
    }

    @FXML
    private void date(ActionEvent event) {
    }

    @FXML
    private void Choosed(ActionEvent event) {
        
        
        
        
        
        	
    	Hashtable ht=new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
     	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context",true);
		Context ctx;
		
        try {
            ctx = new InitialContext(ht);
                Object obj = ctx.lookup("/ergaciaBU-ear/ergaciaBU-ejb/serviceStat!services.serviceStatRemote");
        serviceStatRemote service = (serviceStatRemote) obj;
        XYChart.Series Set1 = new XYChart.Series<>();
        Map <Integer, String> list = new HashMap<Integer,String>();
        list= service.statCandidat(date.getText());
      // for (Object[] result : list){
        Iterator iterator = list.entrySet().iterator();
       
        
     // Afficher le contenu du MAP
		Set listKeys=list.keySet();  // Obtenir la liste des clés
		Iterator iterateur=listKeys.iterator();
		// Parcourir les clés et afficher les entrées de chaque clé;
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			//System.out.println (key+"=>"+list.get(key));
			//System.out.println (key);
			//System.out.println (list.get(key));
			
	          Set1.getData().add(new XYChart.Data( list.get(key),key));

		}
        
       //}
        
      /*  Set1.getData().add(new XYChart.Data("su", 123));
        Set1.getData().add(new XYChart.Data("suu", 5123));
        Set1.getData().add(new XYChart.Data("suuuu", 3123));
        Set1.getData().add(new XYChart.Data("uuuu", 2123));
        Set1.getData().add(new XYChart.Data("uuuu", 1250));*/
        chart.getData().addAll(Set1);
        } catch (NamingException ex) {
        }
    }
  
}
