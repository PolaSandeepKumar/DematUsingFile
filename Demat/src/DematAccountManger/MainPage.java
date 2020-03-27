package DematAccountManger;

import java.util.Scanner;

import DataStructures.UserDataHashMap;
import Utilities.AdminUtilities;
import Utilities.CustomerUtilities;

public class MainPage {
	
	public static void main(String[] args) {

		CustomerUtilities CustomerPage = new CustomerUtilities();
		int choice1 = CustomerPage.firstPageMenu();
	    CustomerPage.loginChoicePage(choice1);
		
	}
}
	
	