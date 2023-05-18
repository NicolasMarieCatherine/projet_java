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

            if (reponse == 1){ // Creer avatar

                System.out.print("\033[H\033[2J");
                System.out.flush();

                avatar.createAvatar()

                ;}

            if (reponse == 2){ // Se connecter

                System.out.print("\033[H\033[2J");
                System.out.flush();

                avatar.Connexion();
            }
            if (reponse == 3){ // afficher liste avatar

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();

                avatar.Liste();
            
            }
            if (reponse == 4){ // admin
                System.out.print("\033[H\033[2J");
                System.out.flush();

                avatar.Connexion();

            }
            if (reponse == 5){
                System.out.print("\033[H\033[2J")   ;
                System.out.flush();
                System.out.println("Fermeture ....");

                try {
                    // Adding a 3-second delay (3000 milliseconds)
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    // Handle the exception
                    e.printStackTrace();
                }

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();
                validation=false;} // Quitter
		}

    }
    
}


/*
 *          1 Creer avatar  - Fait
 * 
 *          2 Se connecter - X
 *              |
 *              | ------> Se connecter - Fait
 *              | ------> lancer defi , qcm etc ...
 *              
 * 
 *          3 Afficher liste avatar  - Fait
 *          
 *          4 Admin  - X
 *              |______> 
 * 
 *          5 Quitter  - Fait
 * 
*/