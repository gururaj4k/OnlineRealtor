import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import uic.onlinerealtor.business.RealtorManager;
import uic.onlinerealtor.entities.Assets;

import uic.onlinerealtor.entities.UserDetails;
import uic.onlinerealtor.entities.UserPreference;
import uic.onlinerealtor.entities.UserPreference.UserType;



public class ConsoleUITest {
	static BufferedReader bufferRead;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(System.getProperty("user.dir"));
		// TODO Auto-generated method stub
		try{
		bufferRead = new BufferedReader(new InputStreamReader(System.in));
	while(true){
		process();
		System.out.println("Search Again : Enter Y");
		System.out.println("Exit : N");
		if(bufferRead.readLine().equals("Y"))
			continue;
		else
			break;
	}
		}catch(Exception ex){}
		
	//	System.out.println(new File("src/config.properties").isFile());
			
		
	}
	
	private static void process(){
		try{
			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			while(true){
		    if(autheticate()){	 
		    	System.out.println("Authentication Successful....");
		    	questionnaire();
		    	break;	    
			}else{
				System.out.println("Authentication Failed....");
			}
			}
			}catch(Exception e){
				
			}
	}
	
	public static boolean autheticate(){
		boolean resp=false;
		try{
			 System.out.println("Please Enter username");
			   	
		String username=bufferRead.readLine();
		//	 String username="guru";
	    System.out.println("Please enter passowrd");
	    String password=bufferRead.readLine();
	    //String password="pass";
	    
	    //Autheticate USer
	    RealtorManager realtorMgr=new RealtorManager();
	    UserDetails user=new UserDetails();
	    user.setUsername(username);
	    user.setPassword(password);
	    realtorMgr.authenticateUser(user);
	    
		}catch(Exception ex){
			
		}
		return resp;
	}
	public static boolean questionnaire(){
		boolean resp=false;
		try{
			 System.out.println("Please answer the following questions..");
			 System.out.println("------------------------------------------");
			 System.out.println("Please choose your Profession");
			 System.out.println("1. Student");
			 System.out.println("2. Employee");
			 System.out.println("Please select the any one of the above choice 1/2?");
			String userInput=bufferRead.readLine();
			
			// String userInput="1";
			 UserPreference userPref=new UserPreference();
			 if(!userInput.equals("1") && !userInput.equals("2")){
				 System.out.println("Please enter correct choice.");
				 userInput=bufferRead.readLine();				 
			 }
			 if(userInput.equals("1") || userInput.equals("2")){
			   	 if(userInput.equals("1"))
			   		 userPref.setUserProfession(UserType.Student);
			   	 else
				   	userPref.setUserProfession(UserType.Employee);
			 }
			 // Second Question....
			 System.out.println("Please enter the number of people staying");
			 System.out.println("1. 1 or more than 1");
			 System.out.println("2. more than 3");
			 System.out.println("3. more than 5");
			 System.out.println("Please select the any one of the above choice 1/2/3?");
			 userInput=bufferRead.readLine();
			 
			 //userInput="2";
			 if(!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")){
				 System.out.println("Please enter correct choice.");
				 userInput=bufferRead.readLine();
				 
			 }
			 if(userInput.equals("1") || userInput.equals("2")){
			   	 if(userInput.equals("1"))
			   		 userPref.setPplStayingAtHome(1);
			   	 else if(userInput.equals("2"))
			   		userPref.setPplStayingAtHome(3);
			   	 else if(userInput.equals("3"))
			   		userPref.setPplStayingAtHome(5);
			   	 
			 }
			 // Third Question..
			 System.out.println("Please enter the choice of the city");
			 System.out.println("1. Chicago");
			 System.out.println("2. Dallas");
			 System.out.println("3. Minneapolis");
			 System.out.println("Please select the any one of the above choice 1/2/3?");
			 userInput=bufferRead.readLine();
			 
			 //userInput="1";
			 if(!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")){
				 System.out.println("Please enter correct choice.");
				 userInput=bufferRead.readLine();
				 
			 }
			
			   	 if(userInput.equals("1"))
			   		 userPref.setPreferredLocation("Chicago");
			   	 else if(userInput.equals("2"))
			   		 userPref.setPreferredLocation("Newyork");
			   	 else if(userInput.equals("3"))
			   		 userPref.setPreferredLocation("Minneapolis");
			 //  	 userPref.setMaritalStatus(MaritalStatus.Married);
			   	 System.out.println("Thanks for the Details...");
			   	 System.out.println("Fetching Data from Big data...");
			   	 
			   	 //Thread.sleep(3000);
			   	 RealtorManager mngr=new RealtorManager();
			   	 List<Assets> asseltLst=mngr.searchAssets(userPref);
			   	 Assets asset=null;
			   	 int resultCount=0;
			   	 for (Assets entity : asseltLst) {
					if(entity instanceof Assets)
						asset=(Assets)entity;
					resultCount++;
					System.out.println(resultCount+". ");
						System.out.println("Address : "+asset.getAddressline1()+", "+asset.getAddressline2()+", ");
						System.out.println("City : "+asset.getCity());
						System.out.println("Price : $"+asset.getPrice());
						System.out.println("OwnerName : $"+asset.getOwnername());
						System.out.println();
						System.out.println();
				}
			   	 if(resultCount==0)
			   		 System.out.println("No Results found...");			   	 
	
		
		
		}catch(Exception ex){
			System.out.println("console... "+ex.getMessage());
		}
		return resp;
	}


}
