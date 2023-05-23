import java.util.Scanner;

import javax.swing.text.StyledEditorKit;

import Exeptions.InputException;
import Exeptions.LoginException;
public class Main {

    public static void main(String args[]) {
        
        
		boolean validation = true ;
		while (validation){

			System.out.print("\033c");
			Scanner sc = new Scanner(System.in);
			System.out.print(
				"\n\nMENU\n\n1 - Creer un avatar\n2 - Se connecter\n3 - Afficher la liste des avatars\n4 - Admin \n5 - Quitter\n\nChoix :");
			int reponse = sc.nextInt();

            Avatar avatar = new Avatar();

            if (reponse == 1){ // Creer un avatar

                System.out.print("\033[H\033[2J");
                System.out.flush();

                avatar.createAvatar();

            }

            if (reponse == 2){ // Se connecter

                System.out.print("\033[H\033[2J");
                System.out.flush();

                avatar.Menu();

            }
            
            if (reponse == 3){ // afficher liste des avatars

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();

                avatar.Liste();
            
            }

            if (reponse == 4){ // admin

                System.out.print("\033[H\033[2J");
                System.out.flush();

                avatar.Admin();

            }

            if (reponse == 5){// Quitter

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
                validation=false;
                
            } 

		}
    }

}
    



/**
 *  *     1 Creer avatar  
 * 
 *  *     2 Se connecter 
 *  *          |______> verification
 *  *          |______> menu
 *  *               |______> afficher stat de l'avatar 
 *  !               |______> répondre à une question 
 *  !               |______> répondre à un défi
 *  !               |______> répondre à un qcm 
 *  !               |______> voir notification
 *  *               |______> déconnexion 
 *                  
 *  *      3 Afficher liste avatar  
 *          
 *  *      4 Admin  
 *  *          |______> menu
 *  *               |______> afficher liste avatar  
 *  *               |______> supprimer avatar 
 *  *               |______> modifier avatar  
 *  !               |______> ajouter question 
 *  *               |______> déconnexion
 * 
 *  *      5 Quitter  
 * 
 **/