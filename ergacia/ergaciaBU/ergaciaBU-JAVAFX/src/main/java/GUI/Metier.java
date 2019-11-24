package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import entity.Condidate_Profile;
import entity.Connection;
import entity.Entreprise_Profile;
import entity.Follow;
import entity.User;
import services.TreemapbyValues;

public class Metier {

	
	public static Map<Condidate_Profile, Integer> ReturnSeggestionConnections(User user) {
		// TODO Auto-generated method stub
		Map<Condidate_Profile,Integer> res = new HashMap<Condidate_Profile,Integer>();
		Condidate_Profile profile = user.getProfil();
		 
		for(Connection con : profile.getConnections())
		{
			for (Connection concon : con.getConnectedtoCondidate().getConnections())
			{
				if(res.containsKey(concon.getConnectedtoCondidate())&&((concon.getConnectedtoCondidate().getId())!=(profile.getId())))
				{
					res.put(concon.getConnectedtoCondidate(), res.get(concon.getConnectedtoCondidate())+1);
				}
				else
				{
					res.put(concon.getConnectedtoCondidate(),1);
				}
		}
		}
		 res =   TreemapbyValues.sortByValues(res);
		return res;
	}
	
	public static ArrayList<Entreprise_Profile> ReturnSeggestionEntreprise(User user) {
		// TODO Auto-generated method stub
		ArrayList<Entreprise_Profile> res = new ArrayList();
		Condidate_Profile profile = user.getProfil();
		 
		for(Connection con : profile.getConnections())
		{
			for (Follow follows : con.getConnectedtoCondidate().getFollows())
			{
					res.add(follows.getProfileEntreprise());
				
		}
		}
		return res;
	}
}
