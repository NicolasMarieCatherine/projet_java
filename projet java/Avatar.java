import java.util.*;

public class Avatar {

    public String nom;
    public String mdp ;
    public int point_vie;
    public int moyenne ;
    public String[] liste_question;

    public Avatar() {}

    public void createAvatar(){
        //this.nom = nom ;
        //this.mdp = mdp ;
        Scanner sc = new Scanner(System.in);

        System.out.printf("%n----------------------------------------------------------------%n");
        System.out.printf("| %-60s |\n", "Création d'avatar");
        System.out.printf("----------------------------------------------------------------%n");

        System.out.println("Quel est le nom de votre Avatar ? %n");
        this.nom = sc.next();

        System.out.println("Quel est votre mot de passe ? \n");
        this.mdp = sc.next();

        System.out.println("Combien de notes avez-vous ? \n");
        int nb_note = sc.nextInt();

        System.out.println("\n");

        int nb_point = 0;
        for (int i = 1; i <= nb_note; i++) {
            System.out.println("Note " + i + " = ");
            nb_point += sc.nextInt();
        }

        this.point_vie = nb_point;
        this.moyenne =  nb_point /  nb_note ;


    }


    public static void main(String args[]) {

        Avatar avatar = new Avatar();
        avatar.createAvatar();
    }
    
}
