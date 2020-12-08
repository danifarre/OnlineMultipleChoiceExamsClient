package client;

import exam.Question;

public class ClientMessages {

    public static void sendMessage(String message) {
        System.out.println(message);
    }

    public static void sendQuestion(Question question) {
        System.out.println(question);
    }

    public static void enterId() {
        String enterId = "Enter your student id: ";
        System.out.print(enterId);
    }

    public static void yourAnswer() {
        String answer = "Your answer: ";
        System.out.print(answer);
    }

    public static void answerNotValid() {
        String notValidAnswer = "This answer is not valid.";
        System.out.println(notValidAnswer);
    }

    public static void examFinished(int grade,String message) {
        System.out.println("\n" + message);
        String yourGrade = "Your grade: ";
        System.out.println(yourGrade + grade);
    }
}
