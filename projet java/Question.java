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
        System.out.printf("| %-70s | %-15s | %-30s | %-10d |\n", this.question, this.reponse, reponses, this.point);
    }

    public static void main(String[] args) {

        Question question = new Question("3+3", "6", new ArrayList<>(Arrays.asList("3", "6", "9")), 10);
        question.printQuestion();

    }

}
