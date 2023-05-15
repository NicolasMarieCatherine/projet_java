

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Write {
    public static void main(String[] args) {
        try {
            
            String nom = "Nicolas";
            int Age = 21;
            ArrayList<String> lignes = new ArrayList<String>();
            lignes.add("\nNom : "+nom);
            lignes.add("\nAge : "+Age); 
            
            String path="profile/"+nom+".txt";
            File file = new File(path);

            FileWriter writer = new FileWriter(file,true);

            for (int i=0; i<lignes.size();i++)
                writer.write(lignes.get(i));

            writer.close();

            System.out.println("Modification terminée!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}