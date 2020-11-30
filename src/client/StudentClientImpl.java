package client;

import common.StudentClient;
import exam.Question;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StudentClientImpl extends UnicastRemoteObject implements StudentClient {

    public StudentClientImpl() throws RemoteException {
        super();
    }

    @Override
    public void startExam(String message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendQuestion(Question question) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendPreviousQuestion(Question question) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void examFinished(int grade, String message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void registerExpired(String message) {
        System.out.println(message);
        System.exit(0);
    }
}
