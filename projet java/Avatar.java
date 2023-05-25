import java.util.*;
import Exeptions.InputException;
import Exeptions.LoginException;
import java.io.*;

public class Avatar {

    public String nom;
    public String mdp ;
    public int point_vie = 100;
    public double moyenne ;
    public String[] liste_question;

    public Avatar(){}

    public boolean Exist(String nom){ // test pour savoir si profile existe

        String nom_test = nom ;
        

        boolean fichier_exist = true ;
        try{

            BufferedReader reader = new BufferedReader(new FileReader(new File("Avatars/"+nom_test+".txt")));


        }catch(Exception ignored){ 
            fichier_exist = false 

        ;}

        return fichier_exist ;
    }

    public boolean Est_vivant(String nom){

        boolean est_vivant = true ;
        try{

            BufferedReader reader = new BufferedReader(new FileReader(new File("Avatars/"+nom+".txt")));
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


        System.out.println("\nCréation d'avatar\n");

        System.out.println("Quel est le nom de votre Avatar ?  : \n");
        
        Scanner sc = new Scanner(System.in);
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

            return;
        }

        System.out.println("\nQuel est votre mot de passe ? : ");
        this.mdp = sc.next();

        System.out.println("\nCombien de notes avez-vous ? : ");
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
            
            File avatar = new File("Avatars/"+this.nom+".txt");
            FileWriter writer = new FileWriter(avatar,true);

            for (int i=0; i<lignes.size();i++)
                writer.write(lignes.get(i));

            writer.close();

            File notification = new File("Notifications/"+this.nom+".txt");
            FileWriter writer1 = new FileWriter(notification,true);
            writer1.write("");
            writer1.close();


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


    public String Connexion(){

        boolean validation = false;
        Scanner scan = new Scanner(System.in);
        

        System.out.println("\nConnexion \n");

        while(!validation){
            
            try {

                System.out.println("Saisir votre nom d'utilisateur  : ");
                String input_nom = scan.nextLine();

                System.out.println("Saisir votre mot de passe  : ");
                String input_pwd = scan.nextLine();

                String ligne;

                if(Exist(input_nom) == true){ // si cet avatar existe

                    BufferedReader reader = new BufferedReader(new FileReader(new File("Avatars/"+input_nom+".txt")));

                    while((ligne = reader.readLine()) != null){ // si fichier n est  pas vide

                        if(ligne.startsWith("Nom :")){ // rechercher du nom
                            String temp[] = ligne.split(":");
                            
                            if( (temp[1].equals(input_nom)) == false ){
                                throw new LoginException("Nom ou mot de passe invalide ! . Recommencer \n");
                            }
                           this.nom = temp[1];
                        }
    
                        if(ligne.startsWith("Mdp :")){
                            String temp[] = ligne.split(":");
                            
                            if( (temp[1].equals(input_pwd)) == false){
                                throw new LoginException("Nom ou mot de passe invalide ! . Recommencer \n");
                            }
                            this.mdp = temp[1];
                        }
                        
                        if(ligne.startsWith("Points de vie :")){
                            String temp[] = ligne.split(":");
                            this.point_vie = Integer.parseInt(temp[1]);
                        }  

                        if(ligne.startsWith("Moyenne :")){
                            String temp[] = ligne.split(":");
                            this.moyenne = Double.parseDouble(temp[1]);
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
        
        return this.nom ;

    }

    
    public void Liste(){
    
       
        File chemin_dossier = new File("Avatars/");
        String liste_fichier[] = chemin_dossier.list();

        System.out.printf("\n\nListe des avatars existants    \t\t\t\t     nombre : "+liste_fichier.length);
        System.out.printf("\n\n\n");

        
        System.out.printf(" %-20s %-20s %-20s %-20s \n","Nom","En vie ","Pv","Moyenne");
        System.out.printf("-----------------------------------------------------------------------\n\n");

        String nom ="";
        String point="";
        String moyenne ="";

        try {

            for(int i=0; i<liste_fichier.length; i++) {

                String fichier[] = liste_fichier[i].split(".txt");
                boolean est_vivant = Est_vivant(fichier[0]);

                BufferedReader reader = new BufferedReader(new FileReader(new File("Avatars/"+fichier[0]+".txt")));
                String ligne;

                while((ligne = reader.readLine()) != null){
                    if(ligne.startsWith("Nom :")){
                        String temp[] = ligne.split(":");
                        nom=temp[1];
                    }

                    if(ligne.startsWith("Points de vie :")){
                        String temp[] = ligne.split(":");
                        point=temp[1];
                    }

                    if(ligne.startsWith("Moyenne :")){
                        String temp[] = ligne.split(":");
                        moyenne=temp[1];
                    }
                }

                //System.out.printf(" %-24s %-35b \n",fichier[0],est_vivant);
                System.out.printf(" %-20s %-20b %-20s %-20s \n",nom,est_vivant,point,moyenne);
                
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.printf("\n\n\n\n");
        System.out.printf("Retour (oui) ? ");


        Scanner sc = new Scanner(System.in);
        String reponse = sc.nextLine();
        if ( reponse.equals("oui")){
            return;} // entrer 'oui' pour sortir
    }
    


    public void Admin(){

        Connexion();
        
        try{
            if (this.nom.equals("Admin") == true){

            }else{
                throw new InputException("Ce compte n'a pas les droits administrateur ");   
            }
        }catch(InputException err){
            System.out.println(err);

            try {
                // Adding a 3-second delay (3000 milliseconds)
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Handle the exception
                e.printStackTrace();
            }

            return ;
        }

        
        boolean validation = false;
        while(!validation){

            Scanner scanner = new Scanner(System.in);
            System.out.println("Connecté en tant qu'"+this.nom+"\n\n");
            System.out.println("1 - Afficher liste d'avatar");
            System.out.println("2 - Modifier un avatarr");
            System.out.println("3 - Ajouter une question");
            System.out.println("4 - Supprimer un avatar");
            System.out.println("5 - Se déconnecter\n\n");
            System.out.println("Choix");
    
            int choix = scanner.nextInt();

            if (choix == 1){ // affichage liste

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();

                Liste();

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();
            }
    
            if (choix == 2){ // Modifier avatar 
    

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();

                System.out.println("Modifier un avatar\n");
                System.out.println("Entrer le nom de l'avatar à modifier ");
    
                boolean valid = false ; 

                String nom ="";
                String mdp="";
                String point="";
                String moyenne ="";

                Scanner sc = new Scanner(System.in);
                String reponse = sc.nextLine();

                while(!valid){
    
                    try{


                        System.out.print("\033[H\033[2J")   ;
                        System.out.flush();
    
                        BufferedReader reader = new BufferedReader(new FileReader(new File("Avatars/"+reponse+".txt")));
                        String ligne;
                    
                        
                        System.out.println("\nEntrez le paramètre à modifier : \n");
                        
                        System.out.println("1 - Mot de passe\n");
                        System.out.println("2 - Points de vie\n");
                        System.out.println("3 - Moyenne\n\n");
                        System.out.println("Choix : ");

                        String option = sc.nextLine();
                        

                        while((ligne = reader.readLine()) != null){ // on récupere les donnée de l'avatar  au cas ou on ne modifie pas tout

                            if(ligne.startsWith("Nom :")){
                                String temp[] = ligne.split(":");
                                nom = temp[1];
                            }

                            if(ligne.startsWith("Mdp :")){
                                String temp[] = ligne.split(":");
                                mdp = temp[1];
                            }
                            
                            if(ligne.startsWith("Points de vie :")){
                                String temp[] = ligne.split(":");
                                point = temp[1];
                            }

                            if(ligne.startsWith("Moyenne :")){
                                String temp[] = ligne.split(":");
                                moyenne = temp[1];
                            }
                        }



                        if(option.equals("1")){ // modifier le mot de passe

                            
                            System.out.print("\033[H\033[2J")   ;
                            System.out.flush();
                            System.out.println("Entrer le nouveau mot de passe \n");

                            
                            String nouvelle_valeur = sc.nextLine();
                            mdp = nouvelle_valeur ;
                
                        }

                        if(option.equals("2")){ // modifier Points de vie

                            System.out.print("\033[H\033[2J")   ;
                            System.out.flush();
                            System.out.println("Entrer les nouveaux points de vie \n");

                            String nouvelle_valeur = sc.nextLine();
                            point = nouvelle_valeur ;
                            
                        }   

                        if(option.equals("3")){ //  modifier la moyenne

                            System.out.print("\033[H\033[2J")   ;
                            System.out.flush();
                            System.out.println("Entrer la nouvelle moyenne\n");

                            String nouvelle_valeur = sc.nextLine();
                            moyenne = nouvelle_valeur ;
                                
                        }
    
                        ArrayList<String> lignes = new ArrayList<String>();

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
    
                        
                        System.out.println("Modification terminée!");

                        try {
                            // Adding a 3-second delay (3000 milliseconds)
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            // Handle the exception
                            e.printStackTrace();
                        }

                        

                        System.out.print("\033[H\033[2J")   ;
                        System.out.flush();

                        valid = true ;

    
                    }catch(Exception err){
    
                        System.err.println(err);
                    }
    
                }

            }
            
        
            if (choix == 3){  //ajouer question 

                Question question = new Question();
                question.Ajouter_Question();
            } 
            
            if (choix == 4){ // suprrimer un avatar

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();

                System.out.println("Supression d'un avatar\n");
                System.out.println("Entrer le nom de l'avatar à supprimer ");
                String nom ="";
                boolean valid = false ; 

                while(!valid){ // test si avatar existe , si oui on le supprime

                    try{
                        
                        Scanner sc = new Scanner(System.in);
                        String reponse = sc.nextLine();
                        nom = reponse;


                        File avatar = new File("Avatars/"+reponse+".txt");
                        avatar.delete();

                        File notification = new File("Notifications/"+reponse+".txt");
                        notification.delete();
                        
                        valid=true ;

                    }catch(Exception err){
                        System.err.println(err);
                    }

                }

                System.out.println("Suppression de l'avatar "+nom+" ...");

                try { // Adding a 3-second delay (3000 milliseconds)
                    Thread.sleep(2000);
                } catch (InterruptedException e) { // Handle the exception
                     e.printStackTrace();
                }

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();
    
            }   

            if (choix == 5){validation = true;} // Retour
            
        }
    }  

    public void Menu(){

        Connexion() ;

        System.out.print("\033[H\033[2J")   ;
        System.out.flush();

        System.out.println("Connecter en tant que "+this.nom+"\n\n");   

        boolean validation = false ;
        while(!validation){

            
            System.out.println("1 - Afficher les statistiques ");
            System.out.println("2 - Répondre à une question");
            System.out.println("3 - Répondre à un défi");
            System.out.println("4 - Répondre à un qcm");
            System.out.println("5 - Voir les notifications");
            System.out.println("6 - Déconnexion\n\n");
            System.out.println("Choix : ");


            Scanner sc = new Scanner(System.in);
            int reponse = sc.nextInt();

            if(reponse == 1){ // statistique

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();

                System.out.println("Statistique de "+this.nom);
                System.out.println(this.point_vie);
                System.out.println(this.moyenne);
            }

            if(reponse == 2){} // répondre question

            if(reponse == 3){} // répondre défi

            if(reponse == 4){}// repondre qcm

            if(reponse == 5){// notifications

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();
                
            }

            if(reponse == 6){validation = true ;} // déconnexion

        }
    }

    public static void main(String args[]) {
    }
    
}
