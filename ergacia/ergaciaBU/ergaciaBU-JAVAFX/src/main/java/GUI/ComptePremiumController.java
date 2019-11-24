/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.LoginFXMLController.userStatic;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import entity.Entreprise_Profile;
import entity.Payment;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.embed.swing.*;
import javafx.scene.control.TextField;
import services.ServiceComptePRemote;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class ComptePremiumController implements Initializable {

	@FXML
	private Button BackBT;
	@FXML
	private CheckBox PostCheck;
	@FXML
	private CheckBox notifCheck;
	@FXML
	private CheckBox EmailCheck;
	@FXML
	private RadioButton Month;
	@FXML
	private RadioButton Semestre;
	@FXML
	private RadioButton Year;
    @FXML
    private TextField Total;
    @FXML
    private Button NextPageBT;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Month.setSelected(true);

	}


	@FXML
	private void Post(ActionEvent event) {
		if(PostCheck.isSelected()){
		notifCheck.setSelected(false);
		EmailCheck.setSelected(false);
		}
		calculerPrix();

	}

	@FXML
	private void Notif(ActionEvent event) {
		if (notifCheck.isSelected()){
			PostCheck.setSelected(false);
		}
		calculerPrix();

	}

	@FXML
	private void Email(ActionEvent event) {
		if(EmailCheck.isSelected())
		{
			PostCheck.setSelected(false);
		}
		calculerPrix();

	}

	@FXML
	private void MonthFC(ActionEvent event) {
		Month.setSelected(true);
		if (Month.isSelected()) {
			Semestre.setSelected(false);
			Year.setSelected(false);
			
		}
		calculerPrix();
	}

	@FXML
	private void SmestreFC(ActionEvent event) {
		Semestre.setSelected(true);
		if (Semestre.isSelected()) {
			Month.setSelected(false);
			Year.setSelected(false);
			
		}
		calculerPrix();

	}

	@FXML
	private void YearFC(ActionEvent event) {
		Year.setSelected(true);
		if (Year.isSelected()) {
			Semestre.setSelected(false);
			Month.setSelected(false);
			
		}
		calculerPrix();

	}

	@FXML
	private void Back(ActionEvent event) {
	}

	private Long dayToMiliseconds(int days) {
		Long result = Long.valueOf(days * 24 * 60 * 60 * 1000);
		return result;
	}

	public Timestamp addDays(int days, Timestamp t1) throws Exception {
		if (days < 0) {
			throw new Exception("Day in wrong format.");
		}
		if (days==30){
			t1.setMonth(t1.getMonth()+1);
			return	(t1) ;
		}
		if (days==180){
			t1.setMonth(t1.getMonth()+6);
			return	(t1) ;
		}
		if (days==360){
			t1.setYear(t1.getYear()+1);
			return	(t1) ;
		}
		Long miliseconds = dayToMiliseconds(days);
		return new Timestamp(t1.getTime() + miliseconds);
	
	}

    @FXML
    private void NextPage(ActionEvent event) throws Exception {
    	
    	String duration = null;
    	String type = null;

    	int a = 0;

    	
		
		if (PostCheck.isSelected()) {
			a = 5;
		}
		if (notifCheck.isSelected()) {
			if(a!=0){
				a=a+6;
			}
			else{
			a = 6;
			}
		}
		if (EmailCheck.isSelected()) {
			if (a!=0){
			a = a+7;
			}
			else{
				a=7;
			}
		}
		if(a==5){
			type="post";
		}
		if(a==6){
			type="notif";
		}
		if(a==7){
			type="email";
		}
		if(a==13){
			type="notif&email";
		}
		if (Month.isSelected()) {
			a = a * 30;
			duration ="Month";
		}
		if (Semestre.isSelected()) {
			a = (a * 30)*6;
			int b = (a * 10) / 100;
			a = a - b;
			duration ="Semestre";
		}
		if (Year.isSelected()) {
			a = (a * 30)*12;
			int b = (a * 30) / 100;
			a = a - b;
			duration="Year";
		}
                
		
                
		System.out.println(a);
	
	if ((a==0)||(a==5)||(a==6)||(a==7)||(a==13)){
		JOptionPane.showMessageDialog(null, "choose your items", "Display Message",
				JOptionPane.INFORMATION_MESSAGE);
                
	}
	else 
	{
	Hashtable ht=new Hashtable();
	ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
 	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
	ht.put("jboss.naming.client.ejb.context",true);
	
	
	Context ct = new InitialContext(ht);
    Object obj = ct.lookup("/ergaciaBU-ear/ergaciaBU-ejb/ServiceCompteP!services.ServiceComptePRemote");

    ServiceComptePRemote service = (ServiceComptePRemote) obj;
    User user1= new User();
	user1=userStatic;
	System.out.println(user1.getId());
	
	Payment p = new Payment();
	p=user1.getEntreprise().getPayment();
	
	p.setDuration(duration);
	p.setType(type);
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	p.setDate_debut(timestamp);
	if (duration.equals("Month")){
	Timestamp t1=new Timestamp(System.currentTimeMillis());
	Timestamp t2=addDays(30,t1);
		p.setDate_fin(t2);
	}
	if (duration.equals("Semestre")){
		Timestamp t1=new Timestamp(System.currentTimeMillis());
		Timestamp t2=addDays(180,t1);
			p.setDate_fin(t2);
		}
	if (duration.equals("Year")){
		Timestamp t1=new Timestamp(System.currentTimeMillis());
		Timestamp t2=addDays(360,t1);
			p.setDate_fin(t2);
		}
	service.UpdateCompte(p);
	System.out.println(p.getDuration());
    }
    }
    
	int a = 0;
 
    public void calculerPrix(){

    	 a = 0;
		
		

		if (Month.isSelected()) {
			if (PostCheck.isSelected()) {
				a = 5;
			}
			if (notifCheck.isSelected()) {
				if(a!=0){
					a=a+6;
				}
				else{
				a = 6;
				}
			}
			if (EmailCheck.isSelected()) {
				if (a!=0){
				a = a+7;
				}
				else{
					a=7;
				}
			}
			a = a * 30;
		}
		if (Semestre.isSelected()) {
			if (PostCheck.isSelected()) {
				a = 5;
			}
			if (notifCheck.isSelected()) {
				if(a!=0){
					a=a+6;
				}
				else{
				a = 6;
				}
			}
			if (EmailCheck.isSelected()) {
				if (a!=0){
				a = a+7;
				}
				else{
					a=7;
				}
			}
			a = (a *30 )*6;
			int b = (a * 10) / 100;
			a = a - b;
		}
		if (Year.isSelected()) {
			if (PostCheck.isSelected()) {
				a = 5;
			}
			if (notifCheck.isSelected()) {
				if(a!=0){
					a=a+6;
				}
				else{
				a = 6;
				}
			}
			if (EmailCheck.isSelected()) {
				if (a!=0){
				a = a+7;
				}
				else{
					a=7;
				}
			}
			a = (a * 30)*12;
			int b = (a * 30) / 100;
			a = a - b;
		}
		 Total.setText(Integer.toString(a)+" $");
    }
    
}
