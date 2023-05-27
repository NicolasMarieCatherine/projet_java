import java.util.*;

import javax.swing.text.StyledEditorKit;

import java.io.*;
import Exeptions.InputException;

public class Question {

    public String question;
    public String reponse;
    public String difficulte ;
    public ArrayList<String> liste_reponses=new ArrayList<String>();
    public int point;

    
    public Question(String new_question, String new_reponse, ArrayList<String> new_liste_reponse, int new_point) {
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

    public int Repondre_question(int moyenne){

        
        if (moyenne <= 10 ){ this.difficulte = "facile" ; this.point = 10;}
        if (10 < moyenne  && moyenne < 14 ){ this.difficulte="moyenne";  this.point = 15;}
        if (moyenne >= 14 ){ this.difficulte="dure"; this.point = 15 ;}

        try{

            File chemin_dossier = new File("Questions/"+difficulte+"/"); // on choisit la question et sa dificulte  en fonction de la moyenne de l'avatar
            int taille_dossier = chemin_dossier.list().length ;
            Random random = new Random();
            int max = taille_dossier+1;
    
            BufferedReader reader = new BufferedReader(new FileReader(new File(chemin_dossier+"/Question"+ random.nextInt(1,max) +".txt")));
            String ligne;

    
            while((ligne = reader.readLine()) != null){

                if(ligne.startsWith("bonne_reponse :")){
                    String temp[] = ligne.split(":");
                    this.reponse=temp[1];
                }

                if(ligne.startsWith("Question :")){
                    String temp[] = ligne.split(":");
                    this.question=temp[1];
                }

                for(int i= 1 ; i < 3 ; i++){
                    if(ligne.startsWith("mauvaise_reponse"+i+" :")){
                        String temp[] = ligne.split(":");
                        this.liste_reponses.add(temp[1]);
                    }
                }
                
            }
        }catch(Exception err){
            System.err.println(err);
        }
       
        System.out.println("Voici la question à laquelle répondre ");
        System.out.println(this.question);
        System.out.println("\n");
        System.out.println("Réponses possibles : "+"\t"+this.reponse+"\t"+this.liste_reponses.get(0)+"\t"+this.liste_reponses.get(1));
        System.out.println("Votre réponse : ");
        Scanner sc = new Scanner(System.in);
        String reponse_input = sc.nextLine();


        System.out.print("\033[H\033[2J")   ;
        System.out.flush();

        if(reponse_input.equals(this.reponse) == true){

            return this.point;

        }else {

            return -this.point;
        }

    }   

    public void Repondre_QCM(int moyenne){}

    public void Liste_question(){

        File chemin_dossier_facile = new File("Questions/facile/");
        File chemin_dossier_moyenne = new File("Questions/moyenne/");
        File chemin_dossier_dure = new File("Questions/dure/");

        String liste_fichier_facile[] = chemin_dossier_facile.list();
        String liste_fichier_moyenne[] = chemin_dossier_moyenne.list();
        String liste_fichier_dure[] = chemin_dossier_dure.list();

        int total = liste_fichier_facile.length + liste_fichier_moyenne.length + liste_fichier_dure.length;
        //ArrayList<String> liste_question = new ArrayList<String>();

        System.out.printf("\n\nListe des questions existantes    \t\t\t\t        nombre : "+total);
        System.out.printf("\n\n\n");

        
        System.out.printf(" %-70s %-50s \n","Question","Difficulté ");
        System.out.printf("-----------------------------------------------------------------------------------\n\n");


        try {

            for(int i=0; i<liste_fichier_facile.length; i++) {

                String question ="";
                String difficulte ="";

                BufferedReader reader = new BufferedReader(new FileReader(new File(chemin_dossier_facile+"/Question"+ (i+1) +".txt")));
                String ligne;

                while((ligne = reader.readLine()) != null){
                    if(ligne.startsWith("Question :")){
                        String temp[] = ligne.split(":");
                        question=temp[1];
                    }
                }

                difficulte = "facile";
                System.out.printf(" %-70s %-50s \n",question,difficulte);

            }

            for(int i=0; i<liste_fichier_moyenne.length; i++) {


                String question = "";
                String difficulte ="";

                BufferedReader reader = new BufferedReader(new FileReader(new File(chemin_dossier_moyenne+"/Question"+ (i+1) +".txt")));
                String ligne;

                while((ligne = reader.readLine()) != null){
                    if(ligne.startsWith("Question :")){
                        String temp[] = ligne.split(":");
                        question=temp[1];
                    }
                }

                difficulte = "moyenne";
                System.out.printf(" %-70s %-50s \n",question,difficulte);
                
            }

            for(int i=0; i<liste_fichier_dure.length; i++) {


                String question = "";
                String difficulte ="";
                
                BufferedReader reader = new BufferedReader(new FileReader(new File(chemin_dossier_dure+"/Question"+ (i+1) +".txt")));
                String ligne;

                while((ligne = reader.readLine()) != null){
                    if(ligne.startsWith("Question :")){
                        String temp[] = ligne.split(":");
                        question=temp[1];
                    }

                }

                difficulte = "dure";
                System.out.printf(" %-70s %-50s \n",question,difficulte);
                
            }

            
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.printf("\n\n\n\n");
        System.out.printf("Retour (oui) ? ");


        Scanner sc = new Scanner(System.in);
        String reponse = sc.nextLine();

        if ( reponse.equals("oui")){return;} // entrer 'oui' pour sortir
    }
    


    public void Ajouter_Question(){


        System.out.println("Ajout d'une question");

       boolean validation = false; 
       while(!validation){

            try {


                Scanner sc = new Scanner(System.in);

                System.out.println("Entrer la dificultée  facile / dure / moyenne : ");
                String dificulte = sc.nextLine();

                System.out.println("\n");
                System.out.println("Entrer votre question : ");
                String question = sc.nextLine();

                System.out.println("\n");
                System.out.println("Entrer la bonne reponse : ");
                String bonne_reponse =sc.nextLine();
                
                System.out.println("\n");
                System.out.println("Entrer une premier mauvaise reponse : ");
                String mauvais_reponse1 =sc.nextLine();

                System.out.println("\n");
                System.out.println("Entrer une seconde mauvaise reponse : ");
                String mauvais_reponse2 =sc.nextLine();


                if (dificulte == "" || question == "" || bonne_reponse == "" || mauvais_reponse1 == "" || mauvais_reponse2 == ""){
                    throw new InputException("Une ou plusieurs données entrées sont vide . Recommencer ");
                }

                File chemin_dossier = new File("Questions/"+dificulte);
                String liste_fichier[] = chemin_dossier.list();
                String path=chemin_dossier+"/Question"+(liste_fichier.length+1)+".txt"; // Chemin vers le fichier à créer ou existant

                File file = new File(path);
                FileWriter writer = new FileWriter(file,true);

                writer.write("\nQuestion :"+question);              
                writer.write("\nbonne_reponse :"+bonne_reponse);
                writer.write("\nmauvaise_reponse1 :"+mauvais_reponse1);
                writer.write("\nmauvaise_reponse2 :"+mauvais_reponse2);
                writer.close();


                System.out.println("\n\nLa question a bien était ajoutée ! ");

                try {
                    // Adding a 3-second delay (3000 milliseconds)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // Handle the exception
                    e.printStackTrace();
                }

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();

                validation = true ;

            }catch(Exception err){
                System.err.println(err);
            }
       }
    }

    
    public static void main(String[] args) {

        /* 
        Question question = new Question("3+3", "6", new ArrayList<>(Arrays.asList("3", "6", "9")), 10);
        question.printQuestion();
        

        Question question = new Question();
        //question.Liste_question();
        //question.Repondre_question(12);
        */
    }
    
}
