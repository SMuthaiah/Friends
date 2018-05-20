package Application;

import java.util.LinkedList;

public class User {
	private String UserName;
	boolean isVisited;
	LinkedList<User> friendsList = new LinkedList<User>();
	
	public User(String UserName, boolean isVisited){
		this.UserName = UserName;
		this.isVisited = isVisited;	
	}

	//Getters and Setter for the attributes of USER class 
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public LinkedList<User> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(LinkedList<User> friendsList) {
		this.friendsList = friendsList;
	}
}
