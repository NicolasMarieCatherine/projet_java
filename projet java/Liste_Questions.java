import java.util.*;

public class Liste_Questions {

    private List<Question> Liste_questions;

    /*
     * public Liste_Questions(List<Question> new_Liste_questions) {
     * this.Liste_questions = new_Liste_questions;
     * }
     */
    public void addQuestion(Question question) {
        this.Liste_questions.add(question);
    }

    public void printListeQuestions() {
        System.out.printf(
                "-------------------------------------------------------------------------------------------------%n");
        System.out.printf(
                "| %-70s | %-10s | %-30s | %-4s |\n", "Question", "Bonne réponse", "Reponses", "Point");
        System.out.printf(
                "-------------------------------------------------------------------------------------------------%n");
        for (Question question : this.Liste_questions) {
            question.printQuestion();
        }
        System.out.printf(
                "-------------------------------------------------------------------------------------------------%n");
    }

    public static void main(String[] args) {

        Liste_Questions listeQuestions = new Liste_Questions();
        Question question_1 = new Question("3+3", "6", new ArrayList<>(Arrays.asList("3", "6", "9")), 10);
        listeQuestions.addQuestion(question_1);
        Question question_2 = new Question("2+2", "4", new ArrayList<>(Arrays.asList("3", "4", "5")), 12);
        listeQuestions.addQuestion(question_2);
        listeQuestions.printListeQuestions();

    }
}
