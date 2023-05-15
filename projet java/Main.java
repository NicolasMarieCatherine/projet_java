import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
	public static void main(String[] args) {

		Login login = new Login();
		login.start();
		if (login.start() == 1){
			login.creerAvatar();
		}
        
        login.Authentification();


		
	}
}