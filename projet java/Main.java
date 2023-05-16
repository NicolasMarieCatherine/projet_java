import java.util.Scanner;
import Exeptions.InputException;
import Exeptions.LoginException;
public class Main {
    
    public static void main(String args[]) {
        
        Avatar avatar = new Avatar();
		boolean validation = true ;
		while (validation){

			System.out.print("\033c");
			Scanner sc = new Scanner(System.in);
			System.out.print(
				"\t\tSERIOUS GAME\n\n\tMENU :\n\n1 - Creer un avatar.\n2 - Creer une question.\n3 - Quitter\n\nRéponse : ");
			int reponse = sc.nextInt();

            if (reponse == 1){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                avatar.createAvatar();}

            if (reponse == 2){validation=false;}
            if (reponse == 3){validation=false;}
		}

    }
    
}
