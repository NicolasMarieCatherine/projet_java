

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
            File file = new File("profile.txt");

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