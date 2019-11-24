import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.Condidate_Profile;
import entity.User;
import services.ServiceUserRemote;
import services.Service_Candidate_ProfileRemote;

public class TestServices {

	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub
		Hashtable ht=new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
     	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context",true);
		Context ctx = new InitialContext(ht);
		Object obj = ctx.lookup("/ergaciaBU-ear/ergaciaBU-ejb/ServiceUser!services.ServiceUserRemote");
		Service_Candidate_ProfileRemote servCondidat = (Service_Candidate_ProfileRemote)ctx.lookup("ergaciaBU-ear/ergaciaBU-ejb/Service_Candidate_Profile!services.Service_Candidate_ProfileRemote");
		User u1 = new User();
		u1.setFirst_name("Benfoulan1");
		u1.setLast_name("foulan1");
		u1.setRole("condidat");
		u1.setId(1);
		User u2 = new User();
		u2.setFirst_name("Benfoulan2");
		u2.setLast_name("foulan2");
		u2.setId(2);
		u2.setRole("condidat");
		ServiceUserRemote service = (ServiceUserRemote) obj;
		service.creatUser(u1);
		service.creatUser(u2);
		List<User> users = service.ReturnUsersbyRole("condidat");
		for(User u : users)
		System.out.println(u.getId()+" : "+u.getFirst_name()+" "+u.getLast_name());
		System.out.println("---------------------------------------");
		u1.setFirst_name("Benfoulan1Modified");
		service.UpdateUser(u1);
		User us = service.ReturnUserbyId(u1.getId());
		System.out.println(us.getId()+" : "+us.getFirst_name()+" "+us.getLast_name());
		System.out.println("---------------------------------------");
		service.DelateUser(u2);
		users = service.ReturnUsersbyRole("condidat");
		for(User u : users)
		System.out.println(u.getId()+" : "+u.getFirst_name()+" "+u.getLast_name());
		
}
	
}
