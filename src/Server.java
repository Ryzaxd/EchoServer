import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1978);
            System.out.println("Accepting connection on port 1978");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection established " + socket.getRemoteSocketAddress());
                handleClient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            while (true) {
                String incomingText = inputStream.readUTF();
                System.out.println("Text received: " + incomingText);
                outputStream.writeUTF("Thank you for the text - it has been received: " + incomingText);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

