import java.util.*;
import Exeptions.InputException;
import Exeptions.LoginException;
import java.io.*;

public class Avatar {

    public String nom;
    public String mdp ;
    public int point_vie;
    public double moyenne ;
    public String[] liste_question;

    public Avatar(){}

    public boolean Exist(String nom){ // test pour savoir si profile existe

        this.nom = nom ;

        boolean fichier_exist = true ;
        try{

            BufferedReader reader = new BufferedReader(new FileReader(new File("profile/"+this.nom+".txt")));


        }catch(Exception ignored){ 
            fichier_exist = false 

        ;}

        return fichier_exist ;
    }

    public boolean Est_vivant(String nom){

        boolean est_vivant = true ;
        try{

            BufferedReader reader = new BufferedReader(new FileReader(new File("profile/"+nom+".txt")));
            String ligne;

            while((ligne = reader.readLine()) != null){ // si fichier n est  pas vide

                if(ligne.startsWith("Points de vie :")){ // rechercher du nom
                    String temp[] = ligne.split(":");

                    double point = Double.parseDouble(temp[1]);
                    if (point <= 0){
                        est_vivant = false ;
                    }
                }
            }
            
        }catch(Exception ignored){ 
            est_vivant = false 
        
        ;}
        
        
        return est_vivant ;
    }

    public void createAvatar(){


        Scanner sc = new Scanner(System.in);

        System.out.printf("%n----------------------------------------------------------------\n");
        System.out.printf("| %-60s |\n", "Création d'avatar");
        System.out.printf("----------------------------------------------------------------\n");

        System.out.println("Quel est le nom de votre Avatar ?  : ");
        this.nom = sc.next();

        if(Exist(this.nom) == true){
            System.out.println("Un Avatar "+this.nom+" existe déjà ! Veuiller vous connecter");

                try {
                    // Adding a 3-second delay (3000 milliseconds)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // Handle the exception
                    e.printStackTrace();
                }
            return;}


        System.out.println("\n");

        System.out.println("Quel est votre mot de passe ? : ");
        this.mdp = sc.next();
        System.out.println("\n");

        System.out.println("Combien de notes avez-vous ? : ");
        int nb_note = sc.nextInt();
        System.out.println("\n");

        

        int nb_point = 0;
        for (int i = 1; i <= nb_note; i++) {
            System.out.println("Note " + i + " = ");

            try{
                
                nb_point += sc.nextInt();

            }catch(Exception err){
               System.out.println("La note entrée n'est pas valide ! Recommencer ...");
            }
            
        }

        this.point_vie = nb_point;
        this.moyenne =  nb_point /  nb_note ;

        try {
            ArrayList<String> lignes = new ArrayList<String>();
            lignes.add("\nNom :"+this.nom);
            lignes.add("\nMdp :"+this.mdp);
            lignes.add("\nPoints de vie :"+this.point_vie);
            lignes.add("\nMoyenne :"+this.moyenne);
            
            String path="profile/"+this.nom+".txt";
            File file = new File(path);

            FileWriter writer = new FileWriter(file,true);

            for (int i=0; i<lignes.size();i++)
                writer.write(lignes.get(i));

            writer.close();
        }catch(IOException err){
            System.err.println(err);
        }
        


        System.out.println("Votre compte à bien était créé ! ");
        
        try {
            // Adding a 3-second delay (3000 milliseconds)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle the exception
            e.printStackTrace();
        }

    }

    public boolean Connexion(){

        boolean validation = false;
        Scanner scan = new Scanner(System.in);
        String nom_user  = "";
        
        System.out.printf("%n----------------------------------------------------------------\n");
        System.out.printf("| %-60s |\n", "\t\tConnexion ");
        System.out.printf("----------------------------------------------------------------\n");

        while(!validation){
            
            try {

                System.out.println("Saisir votre nom d'utilisateur  : ");
                String input_nom = scan.nextLine();

                System.out.println("Saisir votre mot de passe  : ");
                String input_pwd = scan.nextLine();

                String ligne;

                if(Exist(input_nom) == true){ // si cet avatar existe

                    BufferedReader reader = new BufferedReader(new FileReader(new File("profile/"+input_nom+".txt")));

                    while((ligne = reader.readLine()) != null){ // si fichier n est  pas vide

                        if(ligne.startsWith("Nom :")){ // rechercher du nom
                            String temp[] = ligne.split(":");
                            
                            if( (temp[1].equals(input_nom)) == false ){
                                throw new LoginException("Nom ou mot de passe invalide ! . Recommencer \n");
                            }
                           nom_user = temp[1];
                        }
    
                        if(ligne.startsWith("Mdp :")){
                            String temp[] = ligne.split(":");
                            
                            if( (temp[1].equals(input_pwd)) == false){
                                throw new LoginException("Nom ou mot de passe invalide ! . Recommencer \n");
                            }
                        }                        
                    } 
                }
                
                validation = true ;

            }catch (LoginException  | IOException error ){ 
                System.err.println(error);
            }
        }


        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Binvenue "+nom_user+" !");

        try {
            // Adding a 3-second delay (3000 milliseconds)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle the exception
            e.printStackTrace();
        }

        return true ;

    }

    public void Liste(){
    
       
        File chemin_dossier = new File("profile/");
        String liste_fichier[] = chemin_dossier.list();

        System.out.printf("\n\nListe des avatars existants  \n");
        System.out.println("Nombre de d'avatar  : "+liste_fichier.length+"\n\n");
        
        System.out.printf("----------------------------------------------------------------\n");
        System.out.printf("| %-24s %-35s |\n","Nom","En vie ?");
        System.out.printf("----------------------------------------------------------------\n");

        

        try {

            for(int i=0; i<liste_fichier.length; i++) {

                String fichier[] = liste_fichier[i].split(".txt");
                boolean est_vivant = Est_vivant(fichier[0]);
                System.out.printf("| %-24s %-35b |\n",fichier[0],est_vivant);

            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.printf("----------------------------------------------------------------\n\n");
        System.out.printf("Quitter --> (exit) ");


        Scanner sc = new Scanner(System.in);
        String reponse = sc.nextLine();
        if ( reponse.equals("exit")){return;} // entrer 'exit' pour sortir
 
    }


    public static void main(String args[]) {
    }
    
}
