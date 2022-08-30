package client;

/**
 * The Concentration client board class which does the functions of initializing the boards and performing the necessary
 * update function as per the server messages.
 *
 * @author Disha Revandkar dr6742@rit.edu
 * @author Shreya Pramod sp3045@rit.edu
 */
public class ConcentrationClientBoard {
    private String[][] board;
    private int DIM;
    public int matches = 0;
    public ConcentrationClientBoard(int DIM){
        this.DIM = DIM;
        board = new String[DIM][DIM];
    }

    /**
     * Board display of the Concentration Game
     *
     */
    public void display(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(" ");
                if (board[i][j] == null){
                    System.out.print(".");
                }
                else{
                    System.out.print(board[i][j]);
                }   
        }
        System.out.println();
        }
    }

    /**
     * Setting the card of the Concentration Game
     *
     */
    public void setCard(int row, int col, String letter){
        board[row][col] = letter;
    }

    /**
     * Checking if game is over or not.
     *
     */
    public boolean isGameOver(){
        return matches == DIM;
    }
}
