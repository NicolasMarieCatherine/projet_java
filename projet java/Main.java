import java.util.Scanner;
import Exeptions.InputException;
import Exeptions.LoginException;
public class Main {
    
    public static void main(String args[]) {
        
        
		boolean validation = true ;
		while (validation){

			System.out.print("\033c");
			Scanner sc = new Scanner(System.in);
			System.out.print(
				"\t\tSERIOUS GAME\n\n\tMENU \n\n1 - Creer un avatar\n2 - Se connecter\n3 - Afficher la liste des avatars\n4 - Admin \n5 - Quitter\n\nRéponse : ");
			int reponse = sc.nextInt();

            Avatar avatar = new Avatar();

            if (reponse == 1){

                System.out.print("\033[H\033[2J");
                System.out.flush();

                avatar.createAvatar()

                ;}

            if (reponse == 2){

                System.out.print("\033[H\033[2J");
                System.out.flush();

                avatar.Connexion();
            }
            if (reponse == 3){

                System.out.print("\033[H\033[2J");
                System.out.flush();

                avatar.Liste();
                avatar.Est_vivant("Nicolas");

            }
            if (reponse == 4){}
            if (reponse == 5){validation=false;}
		}

    }
    
}

// TODO avatar connexion /

/*
 *          1 Creer avatar  - Fait
 * 
 *          2 Se connecter - X
 *              |
 *              | ------> Se connecter - Fait
 *              | ------> lancer defi , qcm etc ...
 *              
 * 
 *          3 Afficher liste avatar  - X
 *          
 *          4 Admin  - X
 * 
 *          5 Quitter  - Fait
 * 
*/