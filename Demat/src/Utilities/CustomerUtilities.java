package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Main.CustomerFunctions;
import DataStructures.HashInHash;
import DataStructures.MultiValueHashMap;
import DataStructures.ShareMarketHashMap;
import DataStructures.UserDataHashMap;
import Entities.ApplicableCharge;
import Entities.ShareMarket;
import Entities.UserData;
import Entities.UserShare;
import Entities.UserTransaction;

public class CustomerUtilities {
	

	// Scanner is set up to scan the given User input

	Scanner input = new Scanner(System.in);

	// Displays the Main page for DEMAT Trading Account Manager

	public int firstPageMenu() {
		int choice1;

		do {
			// Starting Menu
			System.out.println("DEMAT Trading Account Manager");
			System.out.println("=============================");
			System.out.println("1 - Create Account");
			System.out.println("2 - Login");
			System.out.println("=============================");
			System.out.println("Enter valid choice either 1 or 2");	
			// Get choice from user
			choice1 = input.nextInt();

		} while (!(choice1 == 1 || choice1 == 2));

		return choice1;
	}

	// Method used to display different Main Menu options after login for admin &
	// user respectively

	public void loginChoicePage(int choice) {

		switch (choice) {
		case 1:
			createAccount();
			break;
		case 2:
			int userAccountNumber = 0;
			String inputPassword;
			do {
				String adminPassword = "admin";
				boolean wrongtypeAccountNumber = false;
				do {
					try {
						wrongtypeAccountNumber = false;
						System.out.println("Enter Account number : ");
						userAccountNumber = input.nextInt();
					} catch (InputMismatchException except) {
						System.out.println("Invalid Account Number type is entered, enter integer type account number");
						input = new Scanner(System.in);
						wrongtypeAccountNumber = true;
					}
				} while (wrongtypeAccountNumber);
				
				System.out.println("Enter Password : ");
				inputPassword = input.next();
				// Check if admin is logged in or the user to provided appropriate menu options
				if (userAccountNumber == 12345678 && inputPassword.equals(adminPassword)) {
					// Admin menu options are provided
					boolean showAdminPage = false;	
	
					do{
						int adminchoice = AdminUtilities.adminFirstPage();
					
					
						switch(adminchoice)
							{
							case 0:
								int quitchoice = firstPageMenu();
								loginChoicePage(quitchoice);
								userAccountNumber = 100000;
								inputPassword = "Sandu";
						
							case 1:
								AdminUtilities.addShare();
								break;
							case 2:
								AdminUtilities.updateSharePrice();
								break;
							case 3:
								AdminUtilities.updateShareQuantity();
								break;
							case 4:
								AdminUtilities.updateTransactionCharge();
								break;
							case 5:
								AdminUtilities.updateSTTCharge();
								break;
							}
					
					}while(true);
					
				}	
			
			}while(!UserDataHashMap.validateLogin(userAccountNumber,inputPassword));
					
					do {
						int choice2 = userMainMenu();
						switch(choice2)
						{
						case 0:
								System.out.println("You have quit the session"); 
								int quitchoice = firstPageMenu();
								loginChoicePage(quitchoice);
								userAccountNumber = 100000;
								inputPassword = "Sandu";						
						case 1:
							displayAcountDetails(userAccountNumber);
							break;
						case 2:
							CustomerFunctions.depositMoney(userAccountNumber);
							break;
						case 3:
							CustomerFunctions.withdrawMoney(userAccountNumber);
							break;
						case 4:
							CustomerFunctions.buyShares(userAccountNumber);
							break;
						case 5:
							CustomerFunctions.sellShares(userAccountNumber);
							break;
						case 6:
							viewTransactionReport(userAccountNumber);
							break;
						}
				}while(true);
		
}
}
	
	// Method to view Transaction Report Date wise or Sharename wise
		private void viewTransactionReport(int userAccountNumber) {

			System.out.println("View Transaction Report");
			System.out.println("Enter O to view transactions Date wise and 1 to view transactions Share wise");
			int choice9 = input.nextInt();
			if (choice9 == 0) {
				System.out.println(
						"Enter the range of dates between you want to view your transaction report in yyyy-MM-dd format");

				LocalDate sDate = null;
				boolean wrongInput = false;
				do {
					try {
						wrongInput = false;
						System.out.println("Enter Start date in yyyy-MM-dd format");
						String startDate = new Scanner(System.in).nextLine();
						sDate = LocalDate.parse(startDate);
					} catch (DateTimeParseException exception) {
						System.out.println("Invalid Date format is provided, Please enter Start date in yyyy-MM-dd format");
						wrongInput = true;
					}
				} while (wrongInput);

				LocalDate eDate = null;
				do {
					try {
						wrongInput = false;
						System.out.println("Enter End date in yyyy-MM-dd format");
						String endDate = new Scanner(System.in).nextLine();
						eDate = LocalDate.parse(endDate);
					} catch (DateTimeParseException exception) {
						System.out.println("Invalid Date format is provided, Please enter End date in yyyy-MM-dd format");
						wrongInput = true;
					}
				} while (wrongInput);

				if (sDate.isAfter(eDate)) {
					System.out.println(
							"Start date entered is after the End Date, Re-enter Start date before the end date by choosing option 6 from Main Menu Options");
					String startDate = new Scanner(System.in).nextLine();
					sDate = LocalDate.parse(startDate);
				} else {
					// UserTransaction list used to get Transaction date wise and display
					List<UserTransaction> userTransactionDetailsList = MultiValueHashMap
							.getUserTransactionMap(userAccountNumber, sDate, eDate);
					for (int i = 0; i < userTransactionDetailsList.size(); i++) {
						System.out.println("Transaction Date: " + userTransactionDetailsList.get(i).getTransactionDate()
								+ " " + "Share Name: " + userTransactionDetailsList.get(i).getshareName() + " "
								+ "Share Price: " + userTransactionDetailsList.get(i).getSharePrice() + " "
								+ "Transaction Type:  " + userTransactionDetailsList.get(i).getTransactionType() + " "
								+ "Quanity of Shares: " + userTransactionDetailsList.get(i).getShareQuantity() + " "
								+ "Transaction Charge: " + userTransactionDetailsList.get(i).getTransactionChargeRate()
								+ " " + "Securities Transfer Tax: "
								+ userTransactionDetailsList.get(i).securitiesTransferTaxRate());
					}
				}
			} else {
				// List of Strings used to display sharenames and transaction names based on
				// selected share

				List<String> userShareNames = new ArrayList<String>();
				for (UserTransaction userTransaction : MultiValueHashMap.getUserTransactionList(userAccountNumber)) {
					if (!userShareNames.contains(userTransaction.getshareName())) {
						userShareNames.add(userTransaction.getshareName());
					}
				}
				System.out.println("Transacted Shares List by the User");
				System.out.println("-----------------------------------");
				for (int i = 0; i < userShareNames.size(); i++) {
					System.out.println(i + " " + userShareNames.get(i));
				}
				boolean wrongOption = false;
				String selectedShareName = null;
				do {
					try {
						wrongOption = false;
						System.out.println("Enter the option of share to view all transactions");
						int option3 = input.nextInt();
						selectedShareName = userShareNames.get(option3);
					} catch (IndexOutOfBoundsException exception) {
						System.out.println("Invalid share option entered, Please enter share option listed above");
						input = new Scanner(System.in);
						wrongOption = true;
					}catch (InputMismatchException exception) {
						System.out.println("Invalid share option type entered, Please enter share option integer as listed above");
						input = new Scanner(System.in);
						wrongOption = true;
					}
				} while (wrongOption);
				/*
				 * System.out.println("Enter the option of share to view all transactions"); int
				 * option3 = input.nextInt();
				 * 
				 * String selectedShareName = userShareNames.get(option3);
				 */

				List<UserTransaction> shareTransactionDetailsList = MultiValueHashMap
						.getUserTransactionMap(userAccountNumber, selectedShareName);
				if (shareTransactionDetailsList.size() == 0) {
					System.out.println("This share is not available in your account");
				}
				for (int i = 0; i < shareTransactionDetailsList.size(); i++) {
					System.out.println("Transaction Date: " + shareTransactionDetailsList.get(i).getTransactionDate() + " "
							+ "Share Name: " + shareTransactionDetailsList.get(i).getshareName() + " " + "Share Price: "
							+ shareTransactionDetailsList.get(i).getSharePrice() + " " + "Transaction Type:  "
							+ shareTransactionDetailsList.get(i).getTransactionType() + " " + "Quanity of Shares: "
							+ shareTransactionDetailsList.get(i).getShareQuantity() + " " + "Transaction Charge: "
							+ shareTransactionDetailsList.get(i).getTransactionChargeRate() + " "
							+ "Securities Transfer Tax: " + shareTransactionDetailsList.get(i).securitiesTransferTaxRate());
				}
			}

		}
	
	
	public void displayAcountDetails( int accountnumber){
		
		UserData userDisplay = UserDataHashMap.getAccountDetails(accountnumber);
				
		System.out.println("Hi " +userDisplay.getUserName());
		System.out.println("Account number is " +userDisplay.getAccountNumber());
		System.out.println("Account balance is " +userDisplay.getAccountBalance() +"INR" +'\n');
		System.out.println("    Your Current Holdings     "); 
		System.out.println("================================="); 

		 Map <String, UserShare> userShareData =  HashInHash.getUserShareMap(accountnumber);
		 if(userShareData.isEmpty()){
			 
			 System.out.println("No Shares available in account"+'\n'); 
		 }
		 else{
			 System.out.println("ShareName      " + '\t'+ "SharePrice" + '\t' +"ShareQuantity");
			 String shareName ;
			 double sharePrice;
			 int shareQuantity ;
			 for(String share : userShareData.keySet())  
			 {
				shareName = userShareData.get(share).getShareName();
				sharePrice = userShareData.get(share).getAverageSharePrice();
				shareQuantity = userShareData.get(share).getShareQuantity();
				int shareLength = shareName.length();
				for(int i= shareLength;i<15;i++)
					{
						shareName = shareName + " ";
					}
				System.out.printf(shareName + "%8.2f"+ "%d",sharePrice,shareQuantity);
				System.out.println("");
			 }
			}
		 }
	
	public void createAccount(){
		System.out.println("Enter below details");
		System.out.println("Enter Username : ");
		String username = input.next();
		String password;
		String passwordMatch;
		// Checks for password match
		do {
			System.out.println("Enter Password : ");
			password = input.next();
			System.out.println("Re-enter Password : ");
			passwordMatch = input.next();
		} while (!password.equals(passwordMatch));

		UserData newUser = new UserData(username, password);
		UserDataHashMap.addUser(newUser);

		System.out.println("Account created successfully, Your Account number is : " + newUser.getAccountNumber());
		int quitchoice1 = firstPageMenu();
		loginChoicePage(quitchoice1);
	}
	
	public int userMainMenu(){
		System.out.println("Main Menu Options");
		System.out.println("==================");
		System.out.println("0 - Quit");
		System.out.println("1 - Display Account details");
		System.out.println("2 - Deposit Money");
		System.out.println("3 - Withdraw Money");
		System.out.println("4 - Buy Share");
		System.out.println("5 - Sell Share");
		System.out.println("6 - View Transaction report");
		System.out.println("Enter a number between 0 to 6 : " );
		int choice2 = input.nextInt();
		return choice2;
	}
	
}