package Application;

import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

	static Scanner sc;
	static Parser parser;
	static SalesBook sb;
	
	
	public UI(){
		sc = new Scanner(System.in);	
		sb = new SalesBook();
		parser = new Parser();
		
	}
	
	
	public static void decideOptions(){
		
		int option = 0; 
		boolean check = true;
		
		System.out.println("WELCOME TO SALESBOOK");
		System.out.println("=========================");
		System.out.println("1. ADD FRIENDS");
		System.out.println("2. REMOVE FRIENDS");
		System.out.println("3. GET DIRECT FRIENDS");
		System.out.println("4. GET INDIRECT FRIENDS");
		System.out.println("5. EXIT SALESBOOK");
		System.out.println("=========================");
		
		while(check){

		try{
		option = sc.nextInt();
		}
		catch(InputMismatchException e){
			option = 0;
			sc.nextLine();
			
		}
		
		switch(option){
		
		case 0: 
			System.out.println("Please enter options between 1 to 5");
			break;
		
		case 1: 
			//call to make friends 
			System.out.println("Add friends Module");
			System.out.println("For adding friends do, Format : ADD UserName YourFriendName");
			System.out.println(" ==========================================================");
			sc.nextLine();
			
			String addInput = sc.nextLine();
			
			String cmd = parser.getCommandFromInput(addInput);
			String usr = parser.getUserFromInput(addInput);
			String[] friends = parser.getFriendsFromInput(addInput);
			
			if(cmd.equals("ADD")){
				sb.AddFriends(usr, friends);		
			}
			
			System.out.println("                                 ");
			System.out.println("                                 ");
			
			
			break;
			
		case 2:
			//call to remove friends
			
			System.out.println("Remove friends Module");
			System.out.println("For removing friends do, Format : REMOVE UserName YourFriendName");
			System.out.println(" ==========================================================");
			sc.nextLine();
			
			String removeInput = sc.nextLine();
			
			String rmCmd = parser.getCommandFromInput(removeInput);
			String rmFromUsr = parser.getUserFromInput(removeInput);
			String[] rmfriends = parser.getFriendsFromInput(removeInput);
			
			if(rmCmd.equals("REMOVE")){
				sb.removeFriend(rmFromUsr, rmfriends);		
			}
			
			System.out.println("                                 ");
			System.out.println("                                 ");
			
			
			break;
			
		case 3:
			//call to get Direct friends
			System.out.println("SEE YOUR FRIENDS");
			System.out.println("For seeing friends do, Format : DIRECT USERNAME");
			System.out.println(" ==========================================================");
			sc.nextLine();
			
			String dirInput = sc.nextLine();
			
			String dirCmd = parser.getCommandFromInput(dirInput);
			String dirUsr = parser.getUserFromInput(dirInput);
			
			if(dirCmd.equals("DIRECT")){
				printFriendsListToUI(sb.getDirectFriends(dirUsr));		
			}
			
			System.out.println("                                 ");
			System.out.println("                                 ");
			
			break;
		case 4: 
			//call to get Indirect friends
			System.out.println("SEE YOUR INDIRECT FRIENDS");
			System.out.println("For seeing friends do, Format : INDIRECT USERNAME");
			System.out.println(" ==========================================================");
			sc.nextLine();
			
			String inDirInput = sc.nextLine();
			
			String inDirCmd = parser.getCommandFromInput(inDirInput);
			String inDirUsr = parser.getUserFromInput(inDirInput);
			
			if(inDirCmd.equals("INDIRECT")){
				printFriendsListToUI(sb.getIndirectfriends(inDirUsr));		
			}
			
			System.out.println("                                 ");
			System.out.println("                                 ");
			break;
		case 5:
			check = false;
			System.out.println("Exiting SalesBook, Thank you !");
			break;
			
		default:
			System.out.println("choose options from 1 to 5");
			break;
		
		}
		
		}
		
	}
	
	//Printing the Friend's list to UI
	
	public static void printFriendsListToUI(ArrayList<User> userName){
		
		for(User s:userName){
			
			System.out.println(s.getUserName());
			System.out.println("       ");
			
		}
		
		
	}
		
	public static void main(String args[]){
		UI ui = new UI();
		decideOptions();
	}
	

}
