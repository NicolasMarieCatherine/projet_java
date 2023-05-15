import java.util.Scanner;
import Exeptions.InputException;
import Exeptions.LoginException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Login {
    private String login , pwd; 

    public Login (){
        this.login ="";
        this.pwd="";
    }

    public void GetLogin(){
        System.out.println(this.login);
    }

    public void GetPwd(){
        System.out.println(this.pwd);
    }

    public void SetLogin(String login){
        this.login=login;
    }

    public void SetPwd(String pwd){
        this.pwd=pwd;
    }

    public void creerAvatar(){

        boolean validation = false; 
         while (!validation){
            try {

                System.out.println("\n");
                System.out.println(" == Création de l'utilisateur == ");
                System.out.println("Saisir nom d'utilisateur  : ");
                Scanner scan = new Scanner(System.in);
                String nom_user = scan.nextLine();                
                
                System.out.println("Saisir votre mot de passe  : ");
                String pwd_user = scan.nextLine();

                if ((nom_user.length() > 10) || (pwd_user.length() > 10)){
                    throw new InputException("Votre nom ou mot ne doit pas dépasser 10 caractères !  ");
                }

                //ArrayList<String> lignes = new ArrayList<String>();
                //lignes.add("\n \n");
                //lignes.add("Nom:"+nom_user);
                //lignes.add("\nPwd:"+pwd_user);  

                validation=true;
                SetLogin(nom_user);
                SetPwd(pwd_user);

            }catch (InputException e){
                System.out.println(e);
            }
         }
       
    }

    public Boolean Authentification(){
        boolean validation = false;
        Scanner scan = new Scanner(System.in);

        while(!validation){
            
            try {

                //BufferedReader reader = new BufferedReader(new FileReader(new File("profile.txt")));
                String ligne;

                System.out.println("\n");
                System.out.println("== Connexion ==");
                System.out.println("Saisir nom d'utilisateur  : ");
                String nom_user = scan.nextLine();

                System.out.println("Saisir votre mot de passe  : ");
                String pwd_user = scan.nextLine();

                //
                /*while((ligne = reader.readLine()) != null){

                    if(ligne.startsWith("Nom:")){
                        String temp[] = ligne.split(":");
                        
                        if( (temp[1].equals(nom_user)) == false ){
                            throw new LoginException("Nom ou mot de passe invalide ! . Recommencer \n");
                        }
                    }

                    if(ligne.startsWith("Pwd:")){
                        String temp[] = ligne.split(":");
                        
                        if( (temp[1].equals(pwd_user)) == false){
                            throw new LoginException("Nom ou mot de passe invalide ! . Recommencer \n");
                        }
                    }

                }
                */
            
                if((this.login.equals(nom_user)) & (this.pwd.equals(pwd_user)) ){
                    validation = true;  
                }else {
                    throw new LoginException("Nom ou mot de passe invalide ! . Recommencer \n");
                }  
                

            }catch (LoginException error ){ // | IOException error){
                System.out.println(error);
            }
        }

        System.out.println("Vous êtes connecté(e) ! ");
        return true ;
    } 
    public static void main(String args[]){
        
        Login login = new Login();
        login.creerAvatar();
        login.Authentification();
    }
} 







