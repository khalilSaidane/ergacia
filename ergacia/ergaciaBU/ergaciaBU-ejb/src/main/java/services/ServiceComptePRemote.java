package services;

import javax.ejb.Remote;

import entity.Payment;

@Remote
public interface ServiceComptePRemote {
	public void ajouterCompte(Payment p);
	public boolean CheckCompte(Payment p);
	public Payment getPayment(int id);
	public void UpdateCompte(Payment p);
	

}
