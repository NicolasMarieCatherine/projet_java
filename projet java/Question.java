import java.util.*;

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

    public void printQuestion() {
        StringBuilder reponses = new StringBuilder();
        for (String reponse : this.liste_reponses) {
            reponses.append(reponse).append(" ");
        }
        System.out.printf("| %-70s | %-10s | %-30s | %-4d |\n", this.question, this.reponse, reponses, this.point);
    }

    /*
     * public String liste_questions_math[];
     * 
     * public String liste_question_math_facile[] = {
     * "Combien de côtés possède un hexagone ? ", // 8 3 6
     * "Quel est le résultat de 652 : 2 ? ", // 365 326 226
     * "Quel est le résultat de 1 H 40 + 1 H 40 ? (en min) ", // 280 200 300
     * "Si le rayon d'un cercle mesure 10 cm, combien mesure son diamètre ? ", //
     * 20cm 100cm 25cm
     * "Un triangle équilatéral a ... ",// 3 cotés égaux , 1 angle droit , 2 cotés
     * égaux
     * 
     * };
     * 
     * public String liste_reponse_math[];
     * 
     * public String liste_reponses_math[] = { "6", "326", "3h20", "20",
     * "3 cotés égaux" };
     * 
     * public Question() {
     * this.liste_questions_math = liste_question_math_facile;
     * this.liste_reponses_math = liste_reponse_math;
     * }
     * 
     * public void Generation_questions_qcm() {
     * System.out.println("Génération des questions ...\n");
     * 
     * String tableau[] = new String[5];
     * String tableau_reponse[] = new String[5];
     * 
     * System.out.printf(
     * "-------------------------------------------------------------------------------------------------%n"
     * );
     * System.out.printf("| %-70s | %-20s |\n", "Questions", "Réponses");
     * System.out.printf(
     * "-------------------------------------------------------------------------------------------------%n"
     * );
     * 
     * for (int i = 0; i < this.liste_questions.length; i++) {
     * 
     * Random random = new Random();
     * int nb_random = random.nextInt(this.liste_questions.length);
     * 
     * tableau[i] = this.liste_questions[nb_random];
     * tableau_reponse[i] = this.liste_reponses[nb_random];
     * System.out.printf("| %-70s | %-20s |\n", tableau[i], tableau_reponse[i]);
     * }
     * ;
     * System.out.printf(
     * "-------------------------------------------------------------------------------------------------%n"
     * );
     * }
     */

    public static void main(String[] args) {

        Question question = new Question("3+3", "6", new ArrayList<>(Arrays.asList("3", "6", "9")), 10);
        question.printQuestion();

    }

}
