package Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SalesBook {

	
	private static SalesbookUtility tools;
	
	
	private static HashMap<String, User> UserProfiles = new HashMap<String, User>();
	
	public SalesBook(){
		tools = new SalesbookUtility(UserProfiles);
	}
	
	
	
	public void AddFriends(String User, String[] friends){
		
		User user;
		if(!tools.checkIfUserExists(User)){
			AddUsertoSocialBook(User);
		}
		
		/*
		 * Check if the Friends the user is trying to add is in SocialBook, 
		 * If not add them.
		 * 
		 */
		
		for(String friend: friends){
			if(!tools.checkIfUserExists(friend)){
				AddUsertoSocialBook(friend);
			}	
		}
		
		user = tools.getUserObjectByName(User);
		
		for(int i=0; i<friends.length;i++){
			
			User frend = tools.getUserObjectByName(friends[i]);
			
			if(!tools.checkIfUserInFriendList(User,friends[i])){
				//Add it to the users Friends List also add the User to the friend's Friend List
				try{
				if(user!=null){
					user.getFriendsList().add(tools.getUserObjectByName(friends[i]));
					frend.getFriendsList().add(user);
				}
				}
				catch(Exception e){
					System.err.println("ERROR IN ADDING FRIEND");
				}
				
			}

		}
	
	}
	
	/*
	 * Breadth-First search algorithm to get the all indirect Friends. 
	 * This method is tuned to get indirect friends only up to level 2. 
	 */
	
	public ArrayList<User> getAllFriends(String User){
		
		int level=0;
		
		User user = tools.getUserObjectByName(User);
		
		ArrayList<User> allFriendsList = new ArrayList<User>();
		Queue<User> q = new LinkedList<User>();
		
		q.offer(user);
		q.offer(null);
		
		user.setVisited(true);
		
		while(!q.isEmpty()){
			
			User userFromQ = q.poll();
			
			if(userFromQ == null){
				level++;
				q.offer(null);
				if(q.peek()==null)
					break;
				else
					continue;
			}
			
			/*Do not add the current User to the all friend's list.
			 * Get the list until level 2. 
			 */
			if(userFromQ!=null && level<3){
			if(!(userFromQ.getUserName().equals(User))){
				allFriendsList.add(userFromQ);
			}
			}
			LinkedList<User> friendsFriends = userFromQ.getFriendsList();
			
			if(friendsFriends!=null && friendsFriends.size()>0){
				
				for(User u: friendsFriends){
					
					if(u.isVisited() == false)
					{
						u.setVisited(true);
						q.offer(u);
					}
					
				}
				
				
			}
					
		}
		
		//Reset the Flags back to false. 
		for(User usr:UserProfiles.values()){	
			usr.setVisited(false);	
		}
	
		return allFriendsList;
		
	}
	
	//Method to get the list of Direct Friends
	
	public ArrayList<User> getDirectFriends(String Name){
		
		
		if(!tools.checkIfUserExists(Name)){
			System.err.println("This User" +" "+ Name + "does not exist in SalesBook!");
			return null;
		}
		
		User usr = tools.getUserObjectByName(Name);
		if(usr!=null){
			if(usr.getFriendsList()!=null && usr.getFriendsList().size()>0){
				ArrayList<User> result = new ArrayList<User>();
				LinkedList<User> u = usr.getFriendsList();
				for(User friends:u){
					result.add(friends);
				}
				
				return result;
			}
		}
		
		return null;
		
	}
	
	
	public ArrayList<User> getIndirectfriends(String Name){
		
		if(!tools.checkIfUserExists(Name)){
			System.err.println("This User" +" "+ Name + "does not exist in SalesBook!");
			return null;
		}
		
		ArrayList<User> result = new ArrayList<User>();
		User usr = tools.getUserObjectByName(Name);
		if(usr!=null){
			
			ArrayList<User> friends = getAllFriends(Name);
			ArrayList<User> directFriends = getDirectFriends(Name);
			boolean directFlag = false;
			
			//This is the logic to exclude Direct friends from the all Friends list. 
			for(User u: friends){
				
				for(User ur: directFriends){
					if(u.getUserName().equals(ur.getUserName())){
						directFlag=true;
					}
					
				}
					
				if(!directFlag){
					result.add(u);
				}
				
				//Reset The Flag
				directFlag = false;
						
			}
			
			return result;
			
		}
		
		return null;
	}
	
	
	public void removeFriend(String Name, String[] friends){
		
		User user = tools.getUserObjectByName(Name);
		
		
		for(int i=0; i<friends.length;i++)
		{
		
		User frend = tools.getUserObjectByName(friends[i]);
		if(tools.checkIfUserInFriendList(Name, friends[i])){
			
		   user.getFriendsList().remove(frend);			
		   frend.getFriendsList().remove(user);
			
		}
		}
		
	}
	

	//getListofUsers
	public HashMap<String, User> getUserProfiles() {
		return UserProfiles;
	}

	public void setUserProfiles(HashMap<String, User> userProfiles) {
		UserProfiles = userProfiles;
	}
	
	public void AddUsertoSocialBook(String Name){
		User user = new User(Name,false);
		UserProfiles.put(Name, user);	
	}
	
}
