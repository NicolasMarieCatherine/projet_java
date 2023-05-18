import java.util.*;

public class Liste_Questions {

    protected List<Question> Liste_questions;

    public Liste_Questions(List<Question> new_Liste_questions) {
        this.Liste_questions = new_Liste_questions;
    }

    public void addQuestion(Question question) {
        this.Liste_questions.add(question);
    }

    public void printListeQuestions() {
        System.out.printf(
                "------------------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-70s | %-15s | %-30s | %-10s |\n", "Question", "Bonne reponse", "Choix", "Points");
        System.out.printf(
                "------------------------------------------------------------------------------------------------------------------------------------------%n");
        for (Question question : this.Liste_questions) {
            question.printQuestion();
        }
        System.out.printf(
                "------------------------------------------------------------------------------------------------------------------------------------------%n");
    }

    public static void main(String[] args) {

        List<Question> list_quest = new ArrayList<>();

        list_quest.add(new Question("3+3", "6", new ArrayList<>(Arrays.asList("3", "6", "9")), 10));
        list_quest.add(new Question("2+2", "4", new ArrayList<>(Arrays.asList("3", "4", "5")), 10));

        Liste_Questions listeQuestions = new Liste_Questions(list_quest);

        listeQuestions.printListeQuestions();
    }
}
