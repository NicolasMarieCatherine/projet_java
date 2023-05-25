import java.util.*;
import java.io.*;

public class Question {

    protected String question;
    protected String reponse;
    protected List<String> liste_reponses;
    protected int point;

    public Question(String new_question, String new_reponse, List<String> new_liste_reponse, int new_point) {
        this.question = new_question;
        this.reponse = new_reponse;
        this.liste_reponses = new_liste_reponse;
        this.point = new_point;
    }

    public Question(){}

    public void printQuestion() {
        StringBuilder reponses = new StringBuilder();
        for (String reponse : this.liste_reponses) {
            reponses.append(reponse).append(" ");
        }
        System.out.printf("| %-70s | %-15s | %-30s | %-10d |\n", this.question, this.reponse, reponses, this.point);
    }

    public void Ajouter_Question(){


        System.out.println("Ajout d'une question");

       boolean validation = false; 
       while(!validation){

            try {

                

                /* 
                String question ;
                String bonne_reponse ="";
                String mauvais_reponse_1 ="";
                String mauvais_reponse_2 ="";
                String mauvais_reponse_3 ="";
                */

                Scanner sc = new Scanner(System.in);

                System.out.println("Entrer la dificultée  facile / dure / moyenne : ");
                String dificulte = sc.nextLine();

                System.out.println("Entrer votre question : ");
                String question = sc.nextLine();

                System.out.println("Entrer la bonne reponse : ");
                String bonne_reponse =sc.nextLine();
                
                System.out.println("Entrer une mauvaise reponse : ");
                String mauvais_reponse_1 =sc.nextLine();

                System.out.println("Entrer une mauvaise reponse : ");
                String mauvais_reponse_2 =sc.nextLine();

                System.out.println("Entrer une mauvaise reponse : ");
                String mauvais_reponse_3 =sc.nextLine();


                File chemin_dossier = new File("Questions/"+dificulte);
                String liste_fichier[] = chemin_dossier.list();

                String path=chemin_dossier+"/Question"+(liste_fichier.length+1)+".txt"; // Chemin vers le fichier à créer ou existant

                File file = new File(path);

                FileWriter writer = new FileWriter(file,true);

                writer.write("\nQuestion_"+(liste_fichier.length+1)+" :"+question);              
                writer.write("\nbonne_reponse :"+bonne_reponse);
                writer.write("\nmauvaise_reponse_1 :"+mauvais_reponse_1);
                writer.write("\nmauvaise_reponse_2 :"+mauvais_reponse_2);
                writer.write("\nmauvaise_reponse_3 :"+mauvais_reponse_3);

                writer.close();

                validation = true ;

            }catch(Exception err){
                System.err.println(err);
            }
            

       }
        


    }

    /* 
    public static void main(String[] args) {

        Question question = new Question("3+3", "6", new ArrayList<>(Arrays.asList("3", "6", "9")), 10);
        question.printQuestion();

    }
    */
}
