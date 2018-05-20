package Application;

import java.util.HashMap;
import java.util.LinkedList;

public class SalesbookUtility {

	private static HashMap<String, User> hm;
	
	public  SalesbookUtility(HashMap<String, User> hmap){
		hm = hmap;
	}
	
	public User getUserObjectByName(String Name){
		User user = hm.get(Name);
		return user;
		
	}
	
	public boolean checkIfUserExists(String Name){
		
		if(!hm.containsKey(Name)){
			return false;
		}
		else{
			return true;
		}
		
	}
	
	public boolean checkIfUserInFriendList(String usr, String Friend){
		
		User user = getUserObjectByName(usr);
		LinkedList<User> usersFriendList = user.getFriendsList(); 
		
		for(User u:usersFriendList){
			
			if(u.getUserName().equals(Friend))
				return true;
			
		}
		return false;
		
	}
	

}
