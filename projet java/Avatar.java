import java.util.*;
import Exeptions.InputException;
import Exeptions.LoginException;
import java.io.*;

/**
 * @author FloFA1998 NicolasMarieCatherine
 * 
 *  Initialisation de la classe Avatar /
 *  nom : Nom de l'avatar    /
 *  mdp : Mot de passe de l'avatar /
 *  poit_de_vie : Point de vie  de l'avatar /          
 *  moyenne : Moyenne de l'avatar /
*/
public class Avatar {

    public String nom;
    public String mdp ;
    public int point_vie = 100;
    public int moyenne ;


    /**
     * 
     * Creation d'un avatar vide
     * 
    */
    public Avatar(){}


    /** 
     *  Ecrit les informations dans le fichier de l'avatar qui appelle cette fonction
     *  @param nom nom de l'avatar
     *  @param mdp mot de passe de l'avatar
     *  @param point_de_vie point de vie de l'avatar
     *  @param moyenne moyenne de l'avatar
     * 
    */ 
    public void Ecrire(String nom , String mdp ,int point_de_vie , int moyenne){

        ArrayList<String> lignes = new ArrayList<String>();

        lignes.add("\nNom :"+nom);
        lignes.add("\nMdp :"+mdp);
        lignes.add("\nPoints de vie :"+point_vie);
        lignes.add("\nMoyenne :"+moyenne);

        try{
            String path="Avatars/"+nom+".txt";
            File file = new File(path);
    
            FileWriter writer = new FileWriter(file);
    
            for (int i=0; i<lignes.size();i++)
                writer.write(lignes.get(i));
    
            writer.close();
        }catch(Exception err){
            System.err.println(err);
        }
        
    }

    /** 
     *   Supprime un avatar à partir de son nom
     *   @param nom nom de l'avatar
    */
    public void Supprimer(String nom){ 

        
        try{

            File avatar = new File("Avatars/"+nom+".txt");
            avatar.delete();

            File notification = new File("Notifications/"+nom+".txt");
            notification.delete();

        }catch(Exception err){
            System.err.println(err);
        }

        System.out.println("Suppression de l'avatar "+nom+" ...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) { 
            e.printStackTrace();
        }

        System.out.print("\033[H\033[2J")   ;
        System.out.flush();

    }

    /** 
     *  Permet de  savoir si un avatar avec le nom en paramètre existe
     *  @param nom nom de l'avatar
     *  @return Renvoie true si un avatar avec ce nom existe
    */
    public boolean Exist(String nom){ 

        
        String nom_test = nom ;
        

        boolean fichier_exist = true ;
        try{

            BufferedReader reader = new BufferedReader(new FileReader(new File("Avatars/"+nom_test+".txt")));


        }catch(Exception ignored){ 
            fichier_exist = false 

        ;}

        return fichier_exist ;
    }


   /** 
     *   Permet de savoir si un avatar a plus 0 point de vie
     *   @param nom nom de l'avatar
     *   @return renvoie true si l'avatar a plus de 0 point de vie
    */
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


    /** 
     *   Cree un fichier avec le nom de l'avatar dans "Avatars/" et un fichier notification dans "Notifications/"
    */
    public void createAvatar(){

        System.out.println("\nCreation d'avatar\n");

        System.out.println("Quel est le nom de votre Avatar ?  : \n");
        
        Scanner sc = new Scanner(System.in);
        this.nom = sc.next();

        if(Exist(this.nom) == true){

            System.out.println("Un Avatar "+this.nom+" existe dejà ! Veuiller vous connecter");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
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
               System.out.println("La note entree n'est pas valide ! Recommencer ...");
            }
            
        }

        this.point_vie = this.point_vie + nb_point;
        this.moyenne =  nb_point /  nb_note ;

        
        try {

            Ecrire(this.nom, this.mdp, this.point_vie, this.moyenne); // on crée notre avatar et on crée un fichier notification 

            File notification = new File("Notifications/"+this.nom+".txt");
            FileWriter writer = new FileWriter(notification,true);
            writer.write("");
            writer.close();


        }catch(IOException err){
            System.err.println(err);
        }
        
        System.out.println("Votre compte à bien etait cree ! ");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     *   Vérifie si le nom et mot de passe entrés par l'utilisateur correspondent aux données dans le fichier de l'avatar
     *   @return Renvoie le nom de l'avatar si connexion authorisée
    */
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
                            this.moyenne = Integer.parseInt(temp[1]);
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


    /**
        *   Affiche la liste des avatars et leurs statistiques à partir des fichier dans "Avatars/"
    */
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
    

    /**  
     *   Utilisation de la methode Connexion() pour savoir si le compte connecté est celui de l'administrateur
     *   Apres verification on peut effectuer plusieurs actions comme :
     *
     *   Afficher la liste des avatars /
     *   Modifier un avatar      - > choisir quelle donnee à modifier /
     *   Ajouter une question    - > Question.Ajouter_Question() /
     *   Supprimer un avatar     - > Supprimer(nom) / 
     *   Se déconnecter
     *
    */
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

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return ;
        }

        
        boolean validation = false;
        while(!validation){

            Scanner scanner = new Scanner(System.in);
            System.out.println("Connecte en tant qu'"+this.nom+"\n\n");
            System.out.println("1 - Afficher liste d'avatar");
            System.out.println("2 - Modifier un avatarr");
            System.out.println("3 - Ajouter une question");
            System.out.println("4 - Supprimer un avatar");
            System.out.println("5 - Se deconnecter\n\n");
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
                int point=0;
                int moyenne =0;

                Scanner sc = new Scanner(System.in);
                String reponse = sc.nextLine();

                while(!valid){
    
                    try{
                        
                        System.out.print("\033[H\033[2J")   ;
                        System.out.flush();
    
                        BufferedReader reader = new BufferedReader(new FileReader(new File("Avatars/"+reponse+".txt")));
                        String ligne;
                    
                        
                        System.out.println("\nEntrez le parametre à modifier : \n");
                        
                        System.out.println("1 - Mot de passe\n");
                        System.out.println("2 - Points de vie\n");
                        System.out.println("3 - Moyenne\n\n");
                        System.out.println("Choix : ");

                        String option = sc.nextLine();
                        
                        while((ligne = reader.readLine()) != null){ // on recupere les donnee de l'avatar  au cas ou on ne modifie pas tout

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
                                point = Integer.parseInt(temp[1]);
                            }

                            if(ligne.startsWith("Moyenne :")){
                                String temp[] = ligne.split(":");
                                moyenne = Integer.parseInt(temp[1]);
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
                            point = Integer.parseInt(nouvelle_valeur) ;
                            
                        }   

                        if(option.equals("3")){ //  modifier la moyenne

                            System.out.print("\033[H\033[2J")   ;
                            System.out.flush();
                            System.out.println("Entrer la nouvelle moyenne\n");

                            String nouvelle_valeur = sc.nextLine();
                            moyenne = Integer.parseInt(nouvelle_valeur) ;
                                
                        }
                        
                        Ecrire(nom, mdp, point, moyenne);
 
                        System.out.println("Modification terminee!");

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {

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

                boolean valid = false ; 

                while(!valid){ // test si avatar existe , si oui on le supprime

                    try{
                        
                        Scanner sc = new Scanner(System.in);
                        String nom = sc.nextLine();

                        Supprimer(nom);
                        valid=true ;

                    }catch(Exception err){
                        System.err.println(err);
                    }

                }

            }   

            if (choix == 5){validation = true;} // Retour
            
        }
    }  


    /** 
     *   Utilisation de la methode Connexion() pour se connecter a un avatar autre que Admin
     *   Si verification authorisée 
     *   
     *   Plusieurs options disponibles :
     *   Afficher les statistiques de l 'avatar connecté /    
     *   Afficher les questions disponibles pour les question , qcm ... /
     *   Repondre à une question     - > Question.Repondre_Question() /
     *   Repondre à une defi         - > Question.Repondre_Defi() /
     *   Repondre à un Qcm           - > Question.Repondre_QCM() /
     *   Voir les notifications de l'avatar connecté /
     *   Se déconnecter  /
     *   
    */
    public void Menu(){

    
        Connexion() ;

        Question question = new Question();

        System.out.print("\033[H\033[2J")   ;
        System.out.flush();

        

        boolean validation = false ;
        while(!validation){

            System.out.println("Connecte en tant que "+this.nom+"\n\n");   
            
            System.out.println("1 - Afficher les statistiques ");
            System.out.println("2 - Afficher les questions ");
            System.out.println("3 - Repondre à une question");
            System.out.println("4 - Repondre à un defi");
            System.out.println("5 - Repondre à un qcm");
            System.out.println("6 - Voir les notifications");
            System.out.println("7 - Deconnexion\n\n");
            System.out.println("Choix : ");


            Scanner sc = new Scanner(System.in);
            int reponse = sc.nextInt();

            if(reponse == 1){ // statistique

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();
                System.out.println("Statistique de "+this.nom+"\n\n");


                System.out.printf("%-20s %-20s \n","Point de vie","Moyenne");
                System.out.printf("%-20s %-20s",this.point_vie,this.moyenne);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.print("\033[H\033[2J");
                System.out.flush();
            }


            if(reponse == 2){ // afficher liste des questions
                
                System.out.print("\033[H\033[2J")   ;
                System.out.flush();
                
                question.Liste_question();

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();
            } 

            if(reponse == 3){ // repondre question

                    
                System.out.print("\033[H\033[2J")   ;
                System.out.flush();

                int point = question.Repondre_question(this.moyenne);
                this.point_vie = this.point_vie + point ;

                if ( point > 0 ){

                    System.out.println("Bonne reponse ! "+point+" seront ajoutes à votre avatar");

                    Ecrire(this.nom, this.mdp, this.point_vie, this.moyenne);

                    
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    System.out.print("\033[H\033[2J")   ;
                    System.out.flush();

                }else{

                    System.out.println("Mauvaise reponse ! "+point+" seront retires à votre avatar");

                    Ecrire(this.nom, this.mdp, this.point_vie, this.moyenne);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.print("\033[H\033[2J")   ;
                    System.out.flush();
                    
                }

            } 

            if(reponse == 4){} // repondre defi

            if(reponse == 5){ // repondre qcm

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();

                int point = question.Repondre_QCM(this.moyenne);
                this.point_vie = this.point_vie + point ;

                if (point > 0) {

                    System.out.println("\n\nVous remportez "+point+" points !");

                    Ecrire(this.nom, this.mdp, this.point_vie, this.moyenne);

                    
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    System.out.print("\033[H\033[2J")   ;
                    System.out.flush();

                }
                else{

                    System.out.println("\n\nVous perdez "+point+" points !");


                    Ecrire(this.nom, this.mdp, this.point_vie, this.moyenne);

                    
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    System.out.print("\033[H\033[2J")   ;
                    System.out.flush();

                }
                
            }

            if(reponse == 6){// notifications

                System.out.print("\033[H\033[2J")   ;
                System.out.flush();
                
            }

            if(reponse == 7){validation = true ;} // deconnexion

        }
    }

}
