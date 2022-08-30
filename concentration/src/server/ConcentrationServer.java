package server;
import java.net.*;
import java.io.*;

/**
 * The main concentration server class which waits for connection from the clients.
 *
 * @author Disha Revandkar dr6742@rit.edu
 * @author Shreya Pramod sp3045@rit.edu
 */
public class ConcentrationServer {
    /**
     * Server for the Concentration Game.
     */
    public static void main(String[] args) throws IOException {

         if (args.length != 2) {
             System.err.println("Usage: java MultiServer <port number>");
             System.exit(1);
         }

        int clientNumber = 1;

         int portNumber = Integer.parseInt(args[0]);
         int dimension = Integer.parseInt(args[1]);

        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("--Started Server");
            while (listening) {
                new ConcentrationClientServerThread(serverSocket.accept(), clientNumber, dimension).start();
                clientNumber++;
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}