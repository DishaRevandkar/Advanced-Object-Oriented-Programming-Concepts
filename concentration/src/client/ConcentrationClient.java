package client;

import java.io.IOException;
import java.io.*;
import java.net.*;
import common.ConcentrationProtocol;

/**
 * The Concentration client class. This program sends the connection request to the server and takes the necessary actions
 * based on the messages from the server.
 *
 * @author Disha Revandkar dr6742@rit.edu
 * @author Shreya Pramod sp3045@rit.edu
 */
public class ConcentrationClient {

    /**
     * ConcentrationClient of the Concentration Game
     *
     */
    public static void main(String[] args) throws IOException {
         if (args.length != 2) {
             System.err.println(
                     "Usage: java ConcentrationClient <host name> <port number>");
             System.exit(1);
         }

        String hostName = args[0];

         int portNumber = Integer.parseInt(args[1]);

        ConcentrationClientBoard board = null;

        try (
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
                ) {

        System.out.println("Connected to server..");
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        String dimension_message = in.readLine();
        int dimension = get_dimensions(dimension_message);
        board = new ConcentrationClientBoard(dimension);
        board.display();

        boolean isAlive = true;
        String[] previousCardValue = new String[1];

        while(isAlive && (fromUser = stdIn.readLine()) != null){
            out.println(processUserInput(fromUser));
            fromServer = in.readLine();
            System.out.println("Server: "+fromServer);
            if(previousCardValue[0] != null){
                fromServer = in.readLine();
                System.out.println("Server: "+fromServer);
            }
            if(fromServer != null){

            if(!processServerInput(fromServer, board, previousCardValue)){
                System.exit(1);
            }
            board.display();
            }


        }
        
    } catch (UnknownHostException e) {
        System.err.println("Don't know about host " + hostName);
        System.exit(1);
    } catch (IOException e) {
        System.err.println("Couldn't get I/O for the connection to " +
                hostName);
        System.exit(1);
    }

    }

    /**
     * Board dimensions of the Concentration Game
     *
     */
    public static int get_dimensions(String dimensionMessage){
        String[] messages = dimensionMessage.split(" ");
        return Integer.parseInt(messages[1]);
    }

    /**
     * Processing User Input
     *
     */
    public static String processUserInput(String rawInput){
        String[] positions = rawInput.split(" ");
        return String.format(ConcentrationProtocol.REVEAL_MSG, Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
    }

    /**
     * Processing Server Input and checking all matching mismatching conditions.
     *
     */
    public static boolean processServerInput(String rawInput, ConcentrationClientBoard board, String[] previousCardValue){
        if(rawInput == null){
            return true;
        }
        String tokens[] = rawInput.split(" ");
        if(tokens.length <= 2){
            return true;
        }

        if(tokens[0].equals(ConcentrationProtocol.MATCH)){
            int row1 = Integer.parseInt(tokens[1]);
            int col1 = Integer.parseInt(tokens[2]);
            int row2 = Integer.parseInt(tokens[3]);
            int col2 = Integer.parseInt(tokens[4]);
            board.setCard(row1, col1, previousCardValue[0]);
            board.setCard(row2, col2, previousCardValue[0]);
            previousCardValue[0] = null;
            board.matches ++;
            if(board.isGameOver()){
                System.out.println("You Won!!");
            }
            
        }

        if(tokens[0].equals(ConcentrationProtocol.MISMATCH)){
            previousCardValue[0] = null;
        }

        if(tokens[0].equals(ConcentrationProtocol.CARD)){
            previousCardValue[0] = tokens[3] ;           
        }

        if(tokens[0].equals(ConcentrationProtocol.GAME_OVER)){
            System.out.println(ConcentrationProtocol.GAME_OVER_MSG);
            return false;
        }

        return true;

    }


}


