/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Entreprise_Profile;
import entity.Payment;
import entity.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import static GUI.LoginFXMLController.userStatic;
import services.ServiceComptePRemote;
import services.ServiceCompteP;
import services.ServiceUserRemote;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class PaymentController implements Initializable {

	@FXML
	private AnchorPane InscriptionBT;
	@FXML
	private Button SincrireBT;
	@FXML
	private Button ButtonPUb;

	/**
	 * Initializes the controller class.
	 */

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void Create(ActionEvent event) throws NamingException, IOException {

		Hashtable ht = new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		ht.put("java.naming.provider.url", "http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context", true);

		Context cte= new InitialContext(ht);
		Object obje = cte.lookup("/ergaciaBU-ear/ergaciaBU-ejb/ServiceCompteP!services.ServiceComptePRemote");

		ServiceComptePRemote servicee = (ServiceComptePRemote) obje;

		Payment p = new Payment();
		p.setFree_trial(2);
		servicee.ajouterCompte(p);
		

	}
	


	

	@FXML
	private void Poste(ActionEvent event) throws Exception {

		Hashtable ht = new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		ht.put("java.naming.provider.url", "http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context", true);

		Context ct = new InitialContext(ht);
		Object obj = ct.lookup("/ergaciaBU-ear/ergaciaBU-ejb/ServiceCompteP!services.ServiceComptePRemote");

		ServiceComptePRemote service = (ServiceComptePRemote) obj;
		User user1 = new User();
		user1 = userStatic;

		Payment p = new Payment();
		
		p = user1.getEntreprise().getPayment();
	
		System.out.println(service.CheckCompte(p));
	
		System.out.println(" "+p.getDuration());
		
		int a = 0;
	
		Timestamp timestamp =new Timestamp(System.currentTimeMillis());

		if (service.CheckCompte(p)) {

			a = p.getFree_trial();
			a--;
			p.setFree_trial(a);
			service.UpdateCompte(p);
			System.out.println("ok");

		}
		else{
			System.out.println(p.getDuration());
				if(p.getDuration()==null)  {

					JOptionPane.showMessageDialog(null,
							"sorry you can't post this offer job, you don't have a Premium Compte !  ",
							"Display Message", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else	if (p.getDuration().equals("Month")) {
		
				
				if (timestamp.after(p.getDate_debut()) && timestamp.before(p.getDate_fin())) {
					
					System.out.println("you can posteeeeeeeeeeeeeeeeeeeeeeee");
				} else {

					JOptionPane.showMessageDialog(null,
							"sorry you can't post this offer job, you don't have a Premium Compte !  ",
							"Display Message", JOptionPane.INFORMATION_MESSAGE);
				}

			}
				else	if (p.getDuration().equals("Semestre")) {
				
				
				
				if (timestamp.after(p.getDate_debut()) && timestamp.before(p.getDate_fin())) {
					System.out.println("you can post Semestre");
				} else {

					JOptionPane.showMessageDialog(null,
							"sorry you can't post this offer job, you don't have a Premium Compte !  ",
							"Display Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}

				else if (p.getDuration().equals("Year") ) {
			

				if (timestamp.after(p.getDate_debut()) && timestamp.before(p.getDate_fin())) {
					System.out.println("you can post year");

				}
			}
		}
		

				
			
			
		
	

		System.out.println(p.getFree_trial());

	}

}
