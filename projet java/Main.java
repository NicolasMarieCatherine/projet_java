import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
	public static void main(String[] args) {

		ArrayList<Avatar> liste_avatar = new ArrayList<Avatar>();
		ArrayList<Question> liste_question = new ArrayList<Question>();

		System.out.print("\033c");

		boolean fin = false;

		Scanner sc = new Scanner(System.in);

		while (!fin) {

			int continu;

			System.out.print(
					"\t\tSERIOUS GAME\n\n\tMENU :\n\n1 - Creer un avatar.\n2 - Creer une question.\n3 - Quitter\n\nRéponse : ");
			int reponse = sc.nextInt();

			System.out.print("\033c");

			if (reponse == 1) { // Creer un avatar
				System.out.print("\t\tCreation d'un Avatar\n\nQuelle est le nom de votre Avatar ? ");
				String nom_avatar = sc.next();
				System.out.print("Combien de notes avez-vous ? ");
				int nb_note = sc.nextInt();
				System.out.println("\n");
				int nb_point = 0;
				for (int i = 1; i <= nb_note; i++) {
					System.out.print("Note " + i + " = ");
					nb_point += sc.nextInt();
				}
				Avatar new_avatar = new Avatar(nom_avatar, nb_point);
				liste_avatar.add(new_avatar);
				System.out.print("\033c");
				System.out.print("Creation d'Avatar reussi :\n\nNom Avatar : " + nom_avatar + "\nNombre de points = "
						+ nb_point + "\n\n1 - Continuer.\n\nRéponse : ");
				continu = sc.nextInt();
				System.out.print("\033c");
			}

			if (reponse == 2) { // Creer une question
				System.out.print("\t\tCreation d'une question\n\nQuelle est le nom de votre Avatar ? ");
				String nom_avatar = sc.next();
			}

			if (reponse == 3) { // Creer une question
				break;
			}

		}
	}
}