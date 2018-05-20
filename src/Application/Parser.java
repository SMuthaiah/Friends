package Application;

public class Parser {

public String[] getFriendsFromInput(String input){
		
		String[] parsedSet = input.split("\\s+");
		String[] resultSet = new String[parsedSet.length-2];
		
		if(parsedSet.length>2){
		for(int i=0;i<resultSet.length;i++){
			resultSet[i] = parsedSet[i+2];
		}
		}
		return resultSet;
		
	}
	
	public String getUserFromInput(String input){
		
		String[] parsedSet = input.split("\\s+");
		String component = null;
		if(parsedSet.length > 1){
			component = parsedSet[1];
		}
		return component;
	}
	
	public String getCommandFromInput(String input){
		
		String[] parsedSet = input.split("\\s+");
		String command = null;
		
		if(parsedSet.length > 0){
			command = parsedSet[0];
		}
		
		return command;
	}
}
