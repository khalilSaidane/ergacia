import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.Condidate_Profile;
import entity.Experience;
import entity.User;
import services.ServiceUserRemote;
import services.Service_Candidate_ProfileRemote;

public class TestService__ {
	public static void main(String[] args) throws NamingException {
		Hashtable ht=new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
     	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context",true);
		Context ctx = new InitialContext(ht);
		ServiceUserRemote servUser = (ServiceUserRemote)ctx.lookup("/ergaciaBU-ear/ergaciaBU-ejb/ServiceUser!services.ServiceUserRemote");
		Service_Candidate_ProfileRemote servP = (Service_Candidate_ProfileRemote) ctx.lookup("ergaciaBU-ear/ergaciaBU-ejb/Service_Candidate_Profile!services.Service_Candidate_ProfileRemote");
		System.out.println("---------------------------------------");
//		
//		Condidate_Profile c = new Condidate_Profile();
//		
//		
//		c.setIntro("Intro");
//		
//		int id =servP.addCandidateProfile(c);
//		
//		Experience e = new Experience();
//		e.setContent("Sleeping");
//		e.setId(0);
//		e.setRate(5);
//		servP.addExperience(e,id);
		System.out.println("---------------------------------------");
		Condidate_Profile c = servP.getProfile(1);
		System.out.println(c.toString());
				
	}
}
