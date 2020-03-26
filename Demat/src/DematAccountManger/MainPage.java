package DematAccountManger;

import java.util.Scanner;

import DataStructures.UserDataHashMap;
import Utilities.AdminUtilities;
import Utilities.CustomerUtilities;

public class MainPage {
	
	public static void main(String[] args) {

	CustomerUtilities CustomerPage = new CustomerUtilities();
	int choice = CustomerPage.firstPageMenu();
    CustomerPage.loginChoicePage(choice);
	int choice1 = CustomerPage.firstPageMenu();
    CustomerPage.loginChoicePage(choice1);
    
    
    
    
//	CustomerPage.displayAcountDetails();
	
	// AdminPage = new AdminUtilities();
//	AdminPage.display();
	
	}
}
	
	