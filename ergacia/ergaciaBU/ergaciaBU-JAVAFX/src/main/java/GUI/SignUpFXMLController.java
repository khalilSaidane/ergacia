/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Condidate_Profile;
import entity.Entreprise_Profile;
import entity.Payment;
import entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import services.ServiceComptePRemote;
import services.ServiceUserRemote;
import services.Service_Candidate_ProfileRemote;
import services.serviceEntrepriseRemote;

/**
 * FXML Controller class
 *
 * @author machd
 */
public class SignUpFXMLController implements Initializable {

	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField phoneNumber;
	@FXML
	private TextField email;
	@FXML
	private ChoiceBox<String> role;
	@FXML
	private Button signUp;
	@FXML
	private PasswordField password;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ObservableList<String> availableChoices = FXCollections.observableArrayList("Candidat", "Enterprise admin");
		role.setItems(availableChoices);
		role.setValue("Condidat");
	}

	@FXML
	private void signUp(ActionEvent event) throws NamingException{

		Hashtable ht = new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		ht.put("java.naming.provider.url", "http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context", true);
		Context ctx = new InitialContext(ht);
		ServiceUserRemote serviceUser = (ServiceUserRemote) ctx
				.lookup("ergaciaBU-ear/ergaciaBU-ejb/ServiceUser!services.ServiceUserRemote");
		Service_Candidate_ProfileRemote serviceCondidat = (Service_Candidate_ProfileRemote) ctx.lookup(
				"ergaciaBU-ear/ergaciaBU-ejb/Service_Candidate_Profile!services.Service_Candidate_ProfileRemote");
		serviceEntrepriseRemote serviceEntrepriseRemote = (serviceEntrepriseRemote) ctx
				.lookup("ergaciaBU-ear/ergaciaBU-ejb/serviceEntreprise!services.serviceEntrepriseRemote");
		User user = new User();
		user.setEmail(email.getText());
		user.setFirst_name(firstName.getText());
		user.setLast_name(lastName.getText());
		user.setPassword(password.getText());
		user.setEmail(email.getText());
		user.setRole(role.getSelectionModel().getSelectedItem());
		user.setPhone_number(Integer.parseInt(phoneNumber.getText()));
		Integer code = Mail.sendMail(user);
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("confirm your email");
		dialog.setContentText("Please enter your code here:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			System.out.println(code);
			System.out.println(result.get());
			String StringCode = code + "";
			if (result.get().equals(StringCode)) {
				int userId = serviceUser.creatUser(user);
				System.out.print(userId);

				if (user.getRole().equals("Candidat")) {
					Condidate_Profile condidate_Profile = new Condidate_Profile();
					int profileId = serviceCondidat.addCandidateProfile(condidate_Profile);
					serviceCondidat.affectUser(userId, profileId);

					try {
			            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
			            signUp.getScene().setRoot(root);
			        } catch (IOException ex) {
			            System.out.println("error!!!!!!!!!!!");
			        }

				} else {
					Entreprise_Profile entreprise_Profile = new Entreprise_Profile();
					int profileId = serviceEntrepriseRemote.addEntrepriseProfile(entreprise_Profile);
					serviceEntrepriseRemote.affectUser(userId, profileId);
					Context cte= new InitialContext(ht);
					Object obje = cte.lookup("/ergaciaBU-ear/ergaciaBU-ejb/ServiceCompteP!services.ServiceComptePRemote");

					ServiceComptePRemote servicee = (ServiceComptePRemote) obje;

					Payment p = new Payment();
					p.setFree_trial(2);
					servicee.ajouterCompte(p);
					
				  entreprise_Profile.setPayment(p);	 
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Incorrect code");
				alert.setContentText("Ooops, there was an error!");

				alert.showAndWait();
			}
		}
	}

}
