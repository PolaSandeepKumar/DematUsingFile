package Utilities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Main.CustomerFunctions;
import DataStructures.HashInHash;
import DataStructures.MonoValueHashMap;
import DataStructures.MultiValueHashMap;
import DataStructures.ShareMarketHashMap;
import DataStructures.UserDataHashMap;
import Entities.ApplicableCharge;
import Entities.ShareMarket;
import Entities.UserData;
import Entities.UserShare;
import Entities.UserTransaction;

public class CustomerUtilities {
	
	Scanner input = new Scanner(System.in);
	
	
	public int firstPageMenu() {
		
			// Starting Menu
				System.out.println("DEMAT Trading Account Manager");
				System.out.println("=============================");
				System.out.println("1 - Create Account");
				System.out.println("2 - Login");
				System.out.println("=============================");
				System.out.println("Enter choice");
				

				// Setup Scanner				
				// Get choice from user
				int choice1 = input.nextInt();
				return choice1;
				
	}
	
	public void loginChoicePage(int choice) {

		switch(choice)
		{
		case 1:
			System.out.println("Enter below details");
			// Setup Scanner Create Account input
			System.out.println("Enter Username : ");
			String username = input.next();
			String password;
			String passwordMatch;
			// Checks for password match
			do
			{
				System.out.println("Enter Password : ");
				password = input.next();
				System.out.println("Re-enter Password : ");
				passwordMatch = input.next(); 
			}while(!password.equals(passwordMatch));
			
			UserData newUser = new UserData();
			newUser.setUserName(username);
			newUser.setAccountNumber(900);
			newUser.setAccountPassword(password);
			newUser.setAccountBalance(10000);
			MonoValueHashMap.addUser(newUser);
			
			System.out.println("Your Account number is : 900" );
			int quitchoice1 = firstPageMenu();
			loginChoicePage(quitchoice1);
			
		case 2:
			int userAccountNumber;
			String inputPassword;
			do {
				String adminPassword = "admin";
				System.out.println("Enter Account number : ");
				userAccountNumber = input.nextInt();
				System.out.println("Enter Password : ");
				inputPassword = input.next();
				
				if(userAccountNumber == 12345678 && inputPassword.equals(adminPassword))
				{
					//Admin class methods to be provided
					boolean showAdminPage = false;
					do{
					System.out.println("Admin Menu Options");
					System.out.println("==================");
					System.out.println("0 - Quit");
					System.out.println("1 - Add Shares");
					System.out.println("2 - Update Share price/quantity");
					System.out.println("3 - Update Transaction Charge / STT");
					System.out.println("Enter Option : ");
					int adminchoice = input.nextInt();
					switch(adminchoice)
					{
					case 0:
						int quitchoice = firstPageMenu();
						loginChoicePage(quitchoice);
						userAccountNumber = 100000;
						inputPassword = "Sandu";
						
					case 1:
						
						ShareMarket addUniqueShare = new ShareMarket();
						System.out.println("Enter Share Name : ");
						String shareName = input.next();
						System.out.println("Enter Share Quantity : ");
						int shareQuantity = input.nextInt();
						System.out.println("Enter Share Price : ");
						int sharePrice = input.nextInt();

						addUniqueShare.setShareName(shareName);
						addUniqueShare.setShareQuantity(shareQuantity);
						addUniqueShare.setSharePrice(sharePrice);
						// Below statement is not required.
						//Map<String, ShareMarket> allShareDetails = ShareMarketHashMap.getShareMarket();
						ShareMarketHashMap.addShare(addUniqueShare);
						showAdminPage = true;
						
						
					case 2:
						ShareMarket editUniqueShare = new ShareMarket();
						System.out.println("Enter Share Name : ");
						String updateshareName = input.next();
						System.out.println("Enter Share Quantity : ");
						int updateshareQuantity = input.nextInt();
						System.out.println("Enter Share Price : ");
						int updatesharePrice = input.nextInt();					
						editUniqueShare.setShareName(updateshareName);
						editUniqueShare.setShareQuantity(updateshareQuantity);
						editUniqueShare.setSharePrice(updatesharePrice);
						Map<String, ShareMarket> allShareDetails = ShareMarketHashMap.getShareMarket();
						allShareDetails.put(editUniqueShare.getShareName(),editUniqueShare);
						ShareMarketHashMap.editShare();
						showAdminPage = true;
						
					case 3:
						System.out.println("Enter Transaction Charge ");
						double transactionCharge = input.nextDouble();
						System.out.println("Enter STT Charge ");
						double sttCharge = input.nextDouble();
						AdminUtilities.TaxUpdate(transactionCharge,sttCharge);
					}
					
					}while(!showAdminPage);
					
				}	
			
			}while(!UserDataHashMap.validateLogin(userAccountNumber,inputPassword));
				boolean quitMainmenu = false;
					
					do {
						System.out.println("Main Menu Options");
						System.out.println("==================");
						System.out.println("0 - Quit");
						System.out.println("1 - Display Account details");
						System.out.println("2 - Deposit Money");
						System.out.println("3 - Withdraw Money");
						System.out.println("4 - Buy transaction");
						System.out.println("5 - Sell transaction");
						System.out.println("6 - View Transaction report");
						System.out.println("Enter a number between 0 to 6 : " );
						int choice2 = input.nextInt();
						switch(choice2)
						{
						case 0:
								System.out.println("You have quit the session"); 
								int quitchoice = firstPageMenu();
								loginChoicePage(quitchoice);
								quitMainmenu = true;	
								userAccountNumber = 100000;
								inputPassword = "Sandu";								
						case 1:
							displayAcountDetails(userAccountNumber);
							quitMainmenu = true;
							break;
							
						case 2:
							CustomerFunctions.depositMoney(userAccountNumber);
							
						case 3:
							CustomerFunctions.withdrawtMoney(userAccountNumber);
							
						case 4:
							CustomerFunctions.buyShares(userAccountNumber);
							
						case 5:
							CustomerFunctions.sellShares(userAccountNumber);
							
						case 6:
							System.out.println("View Transaction Report");
							System.out.println("Enter O to view transactions Date wise and any key to view transactions Share wise");
							int choice9 = input.nextInt();
							if(choice9 == 0){
								System.out.println("Enter the range of dates between you want to view your transaction report in yyyy-MM-dd format");
								System.out.println("Enter Start date in yyyy-MM-dd format");
								String startDate = new Scanner(System.in).nextLine();
								LocalDate sDate = LocalDate.parse(startDate);
								System.out.println("Enter End date in yyyy-MM-dd format");
								String endDate = new Scanner(System.in).nextLine();
								LocalDate eDate = LocalDate.parse(endDate);
								
									if(sDate.isAfter(eDate)) {
										System.out.println("Start date entered is after the End Date, Re-enter Start date in yyyy-MM-dd format");
										startDate=new Scanner(startDate).nextLine();
										sDate = LocalDate.parse(startDate);
									}
									else {
										// View Transaction Method
										List<UserTransaction> userTransactionDetailsList = MultiValueHashMap.getUserTransactionMap(userAccountNumber,sDate, eDate);
										for(int i = 0; i < userTransactionDetailsList.size(); i++)
										{
											System.out.println("Transaction Date: " + userTransactionDetailsList.get(i).getTransactionDate() + "Share Name: " + userTransactionDetailsList.get(i).getshareName() + "Share Price: " + userTransactionDetailsList.get(i).getSharePrice() + "Quanity of Shares: " + userTransactionDetailsList.get(i).getShareQuantity() + "Transaction Charge" + userTransactionDetailsList.get(i).getTransactionChargeRate() + "Securities Transfer Tax: " + userTransactionDetailsList.get(i).securitiesTransferTaxRate());
										}
									}
							}
							else {
								// Transaction report method
									List<ShareMarket> allSharesList = new ArrayList(MonoValueHashMap.getShareMarket().values());
									
									for(int i = 0; i < allSharesList.size(); i++)
									{
										System.out.println(i + " Share name: " + allSharesList.get(i).getShareName() + " Share Price:  " + allSharesList.get(i).getSharePrice() + " Share Quantity : " + allSharesList.get(i).getShareQuantity());
									}
									System.out.println("Enter the option of share to view all transactions");
									int option3 = input.nextInt();
									
									ShareMarket selectedShareName = allSharesList.get(option3);
									
								List<UserTransaction> shareTransactionDetailsList = MultiValueHashMap.getUserTransactionMap(userAccountNumber, selectedShareName.getShareName());
								if(shareTransactionDetailsList.size() == 0){
									System.out.println("This share is not available in your account");
								}
								for(int i = 0; i < shareTransactionDetailsList.size(); i++)
								{
									System.out.println("Transaction Date: " + shareTransactionDetailsList.get(i).getTransactionDate() + "Share Name: " +shareTransactionDetailsList.get(i).getshareName() + "Share Price: " + shareTransactionDetailsList.get(i).getSharePrice() + "Quanity of Shares: " + shareTransactionDetailsList.get(i).getShareQuantity() + "Transaction Charge" + shareTransactionDetailsList.get(i).getTransactionChargeRate() + "Securities Transfer Tax: " + shareTransactionDetailsList.get(i).securitiesTransferTaxRate());
								}
							}
							
							quitMainmenu = false;
									       	
							
				}
						
				}while(!quitMainmenu);
		
}
}
	
	public void displayAcountDetails( int accountnumber){
		
		UserData userDisplay = UserDataHashMap.getAccountDetails(accountnumber);
		
		System.out.println(userDisplay.getUserName());
		System.out.println(userDisplay.getAccountNumber());
		System.out.println(userDisplay.getAccountBalance());
	
		 Map <String, UserShare> userShareData =  HashInHash.getUserShareMap(986);
	        System.out.println(userShareData); 
	}
	
	
}