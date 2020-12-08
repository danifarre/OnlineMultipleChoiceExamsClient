package client;

import common.ProfessorServer;
import common.StudentClient;
import exam.Question;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class StudentClientImpl extends UnicastRemoteObject implements StudentClient {

    private Scanner scanner;
    private String studentId;
    private boolean examInProgress;
    private Question question;

    public StudentClientImpl(String studentId, ProfessorServer server) throws RemoteException {
        super();
        this.studentId = studentId;
        this.scanner = new Scanner(System.in);
        this.examInProgress = true;
    }

    @Override
    public synchronized void startExam(String message) {
        System.out.println(message);
        notify();
    }

    @Override
    public synchronized void sendQuestion(Question question) throws RemoteException {
        System.out.println(question);
        this.question = question;
        notify();
    }

    @Override
    public void sendPreviousQuestion(Question question) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void examFinished(int grade, String message) {
        this.examInProgress = false;
        System.out.println("\n" + message + " Grade: " + grade);
        System.exit(0);
    }

    @Override
    public void registerExpired(String message) {
        System.out.println(message);
    }

    public Question getAnswer() {
        Integer answer;
        do {
            System.out.print("Your answer: ");
            answer = this.scanner.nextInt();
            if (!this.question.validQuestion(answer)) {
                System.out.println("This answer is not valid");
            }
        } while (!this.question.validQuestion(answer));
        this.question.answer(answer);
        return this.question;
    }
}
