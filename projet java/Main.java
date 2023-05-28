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

                    Thread.sleep(1500);
                } catch (InterruptedException e) {
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
 *  *               |______> menu
 *  *                      |______> afficher stat de l'avatar                    - Fait
 *  *                       |______> afficher  les questions du jeu              - Fait  
 *  *                       |______> répondre à une question                     - Fait
 *  !                       |______> répondre à un défi                          - X
 *  *                       |______> répondre à un qcm                           - Fait
 *  *                       |______> voir notification                           - Fait
 *  *                       |______> déconnexion                                 - Fait
 *                          
 *  *      3 Afficher liste avatar                                               - Fait
 *          
 *  *      4 Admin                                                               - Fait
 *  *          |______> menu                                                     - Fait
 *  *               |______> afficher liste avatar                               - Fait
 *  *               |______> supprimer avatar                                    - Fait
 *  *               |______> modifier avatar                                     - Fait
 *  *               |______> ajouter question                                    - Fait
 *  *               |______> déconnexion                                         - Fait
 *  *
 *  *      5 Quitter                                                             - Fait
 *  *
 *  *
 *  *
 *  *       
 *  *       Ajouter un delay (ms)
 *  *
 *  *       try {    
 *  *           Thread.sleep(2000);
 *  *       } catch (InterruptedException e) {
 *  *           e.printStackTrace();
 *  *       }
 *  * 
 *  *
 *  *       
 *  *                   
 *  *       Nettoyer la console
 *  *   
 *  *       System.out.print("\033[H\033[2J")   ;
 *  *       System.out.flush();
 *  *
 *  *
 *  *
 *  *
 * 
 **/