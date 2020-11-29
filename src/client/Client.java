package client;

import common.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Objects;

public class Client {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            StudentClient client = new StudentClientImpl();
            ProfessorServer stub = (ProfessorServer) registry.lookup("exam");
            stub.registerStudent(client, "1");
            System.out.println("Client registered, waiting for notification");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString()); e.printStackTrace();
        }
    }
}
