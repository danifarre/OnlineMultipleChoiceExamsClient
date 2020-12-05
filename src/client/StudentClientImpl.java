package client;

import common.ProfessorServer;
import common.StudentClient;
import exam.Question;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class StudentClientImpl extends UnicastRemoteObject implements StudentClient {

    private Scanner scanner;
    private ProfessorServer server;
    private String studentId;

    public StudentClientImpl(String studentId, ProfessorServer server) throws RemoteException {
        super();
        this.server = server;
        this.studentId = studentId;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void startExam(String message) {
        System.out.println(message);
    }

    @Override
    public void sendQuestion(Question question) throws RemoteException {
        System.out.println(question);
        Integer answer;
        do {
            System.out.print("Your answer: ");
            answer = this.scanner.nextInt();
            if (!question.validQuestion(answer)) {
                System.out.println("This answer is not valid");
            }
        } while (!question.validQuestion(answer));
        question.answer(answer);
        this.server.sendAnswer(this.studentId, question);
    }

    @Override
    public void sendPreviousQuestion(Question question) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void examFinished(int grade, String message) {
        System.out.println(message + " Grade: " + grade);
    }

    @Override
    public void registerExpired(String message) {
        System.out.println(message);
    }
}
