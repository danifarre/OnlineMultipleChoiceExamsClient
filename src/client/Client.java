package client;

import common.*;
import exam.Question;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String host = (args.length < 1) ? null : args[0];

        try {
            Registry registry = LocateRegistry.getRegistry(host);

            ClientMessages.enterId();
            String studentId = scanner.nextLine();

            ClientMessages.waitingExam();

            ProfessorServer server = (ProfessorServer) registry.lookup("exam");
            StudentClientImpl client = new StudentClientImpl(studentId, server);

            server.registerStudent(client, studentId);

            synchronized (client) {
                client.wait();
                while (true) {
                    client.wait();
                    Question question = client.getAnswer();
                    server.sendAnswer(studentId, question);
                }
            }
        } catch (NotBoundException | ConnectException e) {
            ClientMessages.serverNotAvailable();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString()); e.printStackTrace();
        }
        System.exit(0);
    }
}
