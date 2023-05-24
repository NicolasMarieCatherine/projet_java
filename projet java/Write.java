

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Write {
    public static void main(String[] args) {
        try {
           

            

            for (int i=1; i<11;i++){

                String path="Questions/facile/Question"+i+".txt"; // Chemin vers le fichier à créer ou existant
                //String path="Questions/facile/Question"+i+".txt";
                //String path="Questions/facile/Question"+i+".txt";
                File file = new File(path);

                FileWriter writer = new FileWriter(file,true);

                /*                                              ^
                 * Si le fichier existe déjà , on écrit à la suite ( append:true)  
                 * sinon on crée le fichier
                 * 
                */



                /*                                              ^
                 * 
                 * Les lignes à écrire dans notre fichier
                 * 
                */
                writer.write("\nQuestion_"+i+" :xxxxxxxxxxxxxxx");              
                writer.write("\nbonne_reponse :4");
                writer.write("\nmauvaise_reponse_1 :5");
                writer.write("\nmauvaise_reponse_2 :3");
                writer.write("\nmauvaise_reponse_3 :1");

                writer.close();

            }
            
            /* 
             *  on peut aussi mettre les lignes à écrire dans une liste et faire 
             *  une boucle pour écrire
             * 
             * 
             * ArrayList<String> lignes = new ArrayList<String>();

                
                lignes.add("\nNom :"+nom);
                lignes.add("\nMdp :"+mdp);
                lignes.add("\nPoints de vie :"+point);
                lignes.add("\nMoyenne :"+moyenne);
                
                String path="Avatars/"+nom+".txt";
                File file = new File(path);
    
                FileWriter writer = new FileWriter(file);
    
                for (int i=0; i<lignes.size();i++)
                    writer.write(lignes.get(i));
    
                writer.close();
             * 
            */

        
            System.out.println("Modification terminée!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}