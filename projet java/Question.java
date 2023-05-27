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

    public Question(){}

    public int Repondre_question(int moyenne){

        /*
         *
         *      Permet à un avatar de répondre à une question tirées aléatoirement 
         *      La dificulté de la question dépend de la moyenne de l'avatar (celle-ci est placée en paramètre)
         *      Cette difficulté permet de savoir dans quel dossier prendre la question     Ex : "Questions/facile/"
         *      Elle permet aussi de définir le nombre de points que l'avatar va perdre ou gagner on fonction de sa réponse à la question
         *      
         *      return int -> point de vie à enlever o rajouter à l'avatar
         *  
         * 
        */
        
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

            this.liste_reponses.clear();

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

    public int Repondre_QCM(int moyenne){

        int total =  0  ;
        int taille = 3;
        ArrayList<String> historique = new ArrayList<String>();
        int nb_bonne_reponse = 0;

        for (int i = 1 ; i < taille+1 ; i++){

            System.out.print("\033[H\033[2J")   ;
            System.out.flush();
            
            System.out.println("\nQcm \t\t\t Question n°"+i+" sur "+taille);
            System.out.println("\n\n");
            int point = Repondre_question(moyenne);

            if (point > 0){
                historique.add("vrai");
                nb_bonne_reponse ++;
            }
            else{historique.add("faux");}

            total += point ;
        } 

        System.out.println("Qcm terminé ! ");
        System.out.println("Vous avez "+nb_bonne_reponse+" bonne(s) réponse(s) sur "+taille);

        

        return total ;
    }

    public void Liste_question(){

        File chemin_dossier_facile = new File("Questions/facile/");
        File chemin_dossier_moyenne = new File("Questions/moyenne/");
        File chemin_dossier_dure = new File("Questions/dure/");

        String liste_fichier_facile[] = chemin_dossier_facile.list();
        String liste_fichier_moyenne[] = chemin_dossier_moyenne.list();
        String liste_fichier_dure[] = chemin_dossier_dure.list();

        int total = liste_fichier_facile.length + liste_fichier_moyenne.length + liste_fichier_dure.length;

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
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
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
         * 
         *     Test Question    
         * 
         * 
         * 
         *      Question question = new Question();
         *      question.Repondre_QCM(12);
         * 
         * 
         * 
         * 
         * 
         * 
         * 
        */

    }
    
}
