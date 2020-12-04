package client;

import common.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    private Scanner scanner;

    public static void main(String[] args) {
        new Client().run(args);
    }

    public  void run(String[] args) {
        this.scanner = new Scanner(System.in);
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);

            System.out.println("Enter your student id:");
            String studentId = this.scanner.nextLine();

            ProfessorServer server = (ProfessorServer) registry.lookup("exam");
            StudentClient client = new StudentClientImpl(studentId, server);

            server.registerStudent(client, studentId);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString()); e.printStackTrace();
        }
    }
}
