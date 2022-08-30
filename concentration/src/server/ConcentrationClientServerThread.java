 package server;
 import common.ConcentrationException;
 import common.ConcentrationProtocol;
 import game.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

 /**
  * The Concentration server thread class which in order to accept multiple client connections.
  *
  * @author Disha Revandkar dr6742@rit.edu
  * @author Shreya Pramod sp3045@rit.edu
  */
public class ConcentrationClientServerThread extends  Thread {
     /** the server socket connection */
    private Socket socket = null;
     /** Concentration board for the server */
    private ConcentrationBoard concentrationBoard = null;
     /** the dimension of the game board */
    int dimension;
     /** number of clients requesting connection to server*/
    int clientNumber;

    /**
     * The default constructor that prints the initial messages and sets the connection
     */
    public ConcentrationClientServerThread(Socket socket, int clientNumber, int dimension) {
        super("MultiServerThread");
        this.clientNumber = clientNumber;
        System.out.println("--New client attached");
        this.socket = socket;
        this.dimension = dimension;
    }

     /**
      * The run method which performs the display operation.
      */
    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        ) {
            String inputLine, outputLine;


            concentrationBoard = new ConcentrationBoard(dimension);

            out.println(String.format(ConcentrationProtocol.BOARD_DIM_MSG, dimension));

            while ((inputLine = in.readLine()) != null) {
                System.out.println("client #"+clientNumber+": "+inputLine);
                try {
                    String output = processInput(inputLine, out);
                    System.out.println("Sending: "+output);
                    out.println(output);
                }
                catch (ConcentrationException e) {
                    out.println(e);
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(ConcentrationException e){
            System.out.println(e);
        }
    }

     /**
      * Performs operations on the board depending on the client messages.
      */
    public String processInput(String message, PrintWriter out) throws ConcentrationException {
        String[] messageChunks = message.split(" ");
        String output = "";
        if (messageChunks[0].equals(ConcentrationProtocol.REVEAL)) {
            int row = Integer.parseInt(messageChunks[1]);
            int col = Integer.parseInt(messageChunks[2]);
            ConcentrationBoard.CardMatch cardMatch =
                    concentrationBoard.reveal(row, col);

            if (cardMatch.isReady()) {
                if (cardMatch.isMatch()) {
                    ConcentrationCard card = cardMatch.getCard2();
                    output = String.format(ConcentrationProtocol.CARD_MSG, card.getRow(), card.getCol(), card.getLetter());
                    out.println(output);
                    output = String.format(ConcentrationProtocol.MATCH_MSG, cardMatch.getCard1().getRow(), cardMatch.getCard1().getCol(), cardMatch.getCard2().getRow(), cardMatch.getCard2().getCol());
                } else {

                    ConcentrationCard card = cardMatch.getCard2();
                    output = String.format(ConcentrationProtocol.CARD_MSG, card.getRow(), card.getCol(), card.getLetter());
                    out.println(output);
                    output = String.format(ConcentrationProtocol.MISMATCH_MSG,  cardMatch.getCard1().getRow(), cardMatch.getCard1().getCol(), cardMatch.getCard2().getRow(), cardMatch.getCard2().getCol());
                    
                }
            } else {

                ConcentrationCard card = cardMatch.getCard1();
                output = String.format(ConcentrationProtocol.CARD_MSG, card.getRow(), card.getCol(), card.getLetter());
            }
        }
        return output;
    }
}
