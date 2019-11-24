package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Payment;
import entity.User;

/**
 * Session Bean implementation class ServiceCompteP
 */
@Stateless
@LocalBean
public class ServiceCompteP implements ServiceComptePRemote, ServiceComptePLocal {
	/**
	 * Default constructor.
	 */
	@PersistenceContext(unitName = "ergaciaBU-ejb")
	EntityManager em;

	public ServiceCompteP() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ajouterCompte(Payment p) {

		em.persist(p);

	}

	@Override
	public boolean CheckCompte(Payment p) {
		int a = 0;
		boolean resultat = false;

		if (p.getFree_trial() == 0) {
			System.out.println("acheter un compte premieum");
			resultat = false;
		} else {

			resultat = true;
		}

		

		return resultat;
	}

	@Override
	public Payment getPayment(int id) {
		return em.find(Payment.class, id);
	}

	@Override
	public void UpdateCompte(Payment p) {
		
	em.merge(p);
		
	}

}
