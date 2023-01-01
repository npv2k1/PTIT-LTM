package rmi.exercise;

public interface ITeacher extends java.rmi.Remote {
    public String declare(String studentIP, int studentPort, String fullURL);
}
