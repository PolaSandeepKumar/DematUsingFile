package userInterface;

public class ApplicationLaunch {

	public static void main(String[] args) {
		int userChoice = UserMenu.applicationLaunch();
		UserMenu.loginChoicePage(userChoice);
	}
}
