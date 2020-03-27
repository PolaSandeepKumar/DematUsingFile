package Utilities;
 
import java.util.*;

import DataStructures.UserDataHashMap;
import Database.DatabaseMaintainer;
import DataStructures.HashInHash;
import DataStructures.ShareMarketHashMap;
import Entities.UserShare;
import Entities.ApplicableCharge;
import Entities.ShareMarket;
import Entities.UserData;


public class AdminUtilities {
	
	static Scanner input = new Scanner(System.in);
	
//Function show options when Admin login
public static int adminFirstPage(){
	Scanner inputone = new Scanner(System.in);
	System.out.println("Admin Menu Options");
	System.out.println("==================");
	System.out.println("0 - Quit");
	System.out.println("1 - Add Shares");
	System.out.println("2 - Update Share price");
	System.out.println("3 - Update Share quantity");
	System.out.println("4 - Update Transaction Charge ");
	System.out.println("5 - Update STT");
	System.out.println("Enter Option : ");
	int adminchoice = inputone.nextInt();
	return adminchoice;
}

//Admin uses this function to add new share
public static void addShare(){
	Map<String, ShareMarket> allShareDetails = ShareMarketHashMap.getShareMarket();
	ShareMarket addUniqueShare = new ShareMarket();
	displayMap(allShareDetails);
	System.out.println("Enter Share Name : ");
	String shareName = input.next();
	if(allShareDetails.containsKey(shareName))	{
	System.out.println("This Share is already available, try to add different Share :( .You will be returned to main menu" +'\n');
	return;
	}
	else if(isNumber(shareName)){
		System.out.println("Share should be alphabets only :( .You will be returned to main menu" +'\n');
		return;
	}
	System.out.println("Enter Share Quantity : ");
	if(!input.hasNextInt()) {
		System.out.println("Quantity entered in invalid, it should be a postive number.You will be returned to main menu" +'\n');
		return;
	}	
	int shareQuantity = input.nextInt();
	System.out.println("Enter Share Price : ");
	if(!input.hasNextInt()) {
		System.out.println("Price entered in invalid, it should be a postive number.You will be returned to main menu" +'\n');
		return;
	}	
	int sharePrice = input.nextInt();

	addUniqueShare.setShareName(shareName.trim());
	addUniqueShare.setShareQuantity(shareQuantity);
	addUniqueShare.setSharePrice(sharePrice);
	ShareMarketHashMap.addShare(addUniqueShare);
	Map<String, ShareMarket> shareAfterAdding = ShareMarketHashMap.getShareMarket();
	System.out.println("Share Name :" + shareAfterAdding.get(shareName).getShareName() + ',' + " Share Price:" + shareAfterAdding.get(shareName).getSharePrice() + ',' + " Share Quantity: " + shareAfterAdding.get(shareName).getShareQuantity());
	System.out.println("Successfully added the share" + '\n');
}
	

//Admin Function update quantity of share
public static void updateShareQuantity(){

	Map<String, ShareMarket> everyShareDetails = ShareMarketHashMap.getShareMarket();
	displayMap(everyShareDetails);
	ShareMarket editUniqueSharePrice = new ShareMarket();	
	System.out.println('\n' + "Enter Share Name for updating quantity: ");
	String shareName = input.next();
	if(!everyShareDetails.containsKey(shareName))	{
		System.out.println("Share doesn't exist :( .You will be returned to main menu" +'\n');
		return;
	}
	System.out.println("Enter Share Quantiy : ");
	if(!input.hasNextInt()) {
		System.out.println("Quantity entered in invalid, it should be a postive integer.You will be returned to main menu" + '\n');
		return;
	}	
	int updateshareQuantity = input.nextInt();			
	editUniqueSharePrice.setShareName(shareName);
	editUniqueSharePrice.setShareQuantity(updateshareQuantity);
	editUniqueSharePrice.setSharePrice(everyShareDetails.get(shareName).getSharePrice());
	everyShareDetails.put(editUniqueSharePrice.getShareName(),editUniqueSharePrice);
	ShareMarketHashMap.editShare();
	Map<String, ShareMarket> everyShareDetailsupdate = ShareMarketHashMap.getShareMarket();
	System.out.println(" Share Name: " + everyShareDetailsupdate.get(shareName).getShareName() +',' + " Share Price: " + everyShareDetailsupdate.get(shareName).getSharePrice() +',' + " Share Quantity: " + everyShareDetailsupdate.get(shareName).getShareQuantity());

	System.out.println("Successfully updated quantiy, Please find above available shares" + '\n');
}

//Admin Function update price of share
public static void updateSharePrice(){
	
	Map<String, ShareMarket> allShareDetails = ShareMarketHashMap.getShareMarket();
	displayMap(allShareDetails);
	ShareMarket editUniqueShareQuantity = new ShareMarket();
	System.out.println('\n'+"Enter Share Name for updating price : ");
	String updateShareName = input.next();
	if(!allShareDetails.containsKey(updateShareName))	{
		System.out.println("Share doesn't exist :( .You will be returned to main menu" +'\n');
		return;
	}
	System.out.println("Enter Share Price : ");
	if(!input.hasNextInt()) {
		System.out.println("Price entered in invalid, it should be a postive integer.You will be returned to main menu" + '\n');
		return;
	}	
	int updateSharePrice = input.nextInt();			
	editUniqueShareQuantity.setShareName(updateShareName);
	editUniqueShareQuantity.setSharePrice(updateSharePrice);
	editUniqueShareQuantity.setShareQuantity(allShareDetails.get(updateShareName).getShareQuantity());
	allShareDetails.put(editUniqueShareQuantity.getShareName(),editUniqueShareQuantity);
	ShareMarketHashMap.editShare();
	Map<String, ShareMarket> allShareDetailsupdated = ShareMarketHashMap.getShareMarket();
	System.out.println("Share Name: " + allShareDetailsupdated.get(updateShareName).getShareName() +',' + " Share Price:" + allShareDetailsupdated.get(updateShareName).getSharePrice() +',' + " Share Quantity:" + allShareDetailsupdated.get(updateShareName).getShareQuantity());
	System.out.println("Successfully updated price, please find above available shares" +'\n');	
}

//Admin Function update transaction charge
public static void updateTransactionCharge(){
	Scanner userInput = new Scanner(System.in);
	System.out.println("Current STT charge is" +ApplicableCharge.getSecuritiesTransferTaxRate());
	System.out.println("Current Transaction charge is" +ApplicableCharge.getTransactionChargeRate());
	System.out.println("Enter Transaction Charge ");
	if(!userInput.hasNextDouble()) {
		System.out.println("Transaction charge entered in invalid, it should be a numeric value. You will be returned to main menu" + '\n');
		return;
	}	
	double transactionCharge = userInput.nextDouble();
	ApplicableCharge.setTransactionChargeRate(transactionCharge);
	System.out.println("Updated Transaction charge is" +ApplicableCharge.getTransactionChargeRate() +'\n');
}

//Admin Function update STT charge
public static void updateSTTCharge(){
	Scanner userInputStt = new Scanner(System.in);

	System.out.println("Current STT charge is" +ApplicableCharge.getSecuritiesTransferTaxRate());
	System.out.println("Current Transaction charge is" +ApplicableCharge.getTransactionChargeRate());
	System.out.println("Enter STT Charge ");
	
	if(!userInputStt.hasNextDouble()) {
		System.out.println("STT charge entered in invalid, it should be a numeric value. You will be returned to main menu" + '\n');
		return;
	}
	
	double STT = userInputStt.nextDouble();
	ApplicableCharge.setSecuritiesTransferTaxRate(STT);
	System.out.println("Updated STT charge is" +ApplicableCharge.getSecuritiesTransferTaxRate() +'\n');	
}

//Function to check whether entered string is Integer
public static boolean isNumber(String s) 
{ 
    for (int i = 0; i < s.length(); i++) 
    if (Character.isDigit(s.charAt(i))  
        == false) 
        return false; 

    return true; 
} 

public static void displayMap(Map<String, ShareMarket> inputMap){
	for (Map.Entry<String, ShareMarket> entry : inputMap.entrySet())  {
        System.out.println(entry.getValue());
	 }
}

}
