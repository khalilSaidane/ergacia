package services;

import java.util.List;

import javax.ejb.Remote;

import entity.Post;
import entity.Reaction;

@Remote
public interface serviceReactionsRemote {
    
	public void AddReaction(Reaction reaction);
	public void UpdateReaction(Reaction reaction);
	public void DeleteReaction(Reaction reaction);
	public List<Reaction> ReturnReactionbypub(Post post);
	
}
