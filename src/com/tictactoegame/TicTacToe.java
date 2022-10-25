package com.tictactoegame;
import java.util.Scanner;
public class TicTacToe {
    public static final int HEAD = 0;
    public static final int TAIL = 1;

    public static enum Player {
        player1, player2
    };

    public static char[] createBoard() {
        char[] board = new char[10];
        for (int position = 1; position < 10; position++) {
            board[position] = ' ';
        }
        return board;
    }

    public static char getInput(char givenInput) {
        return Character.toUpperCase(givenInput);
    }

    public static void showBoard(char[] board){
        System.out.println(board[1] + " | " + board[2] + " |" + " " + board[3]);
        System.out.println("---------");
        System.out.println(board[4] + " | " + board[5] + " |" + " " + board[6]);
        System.out.println("---------");
        System.out.println(board[7] + " | " + board[8] + " |" + " " + board[9]);
    }

    public static boolean isBoardEmpty(char[] board, int index) {
        return board[index] == ' ';
    }

    public static int getUserMove(char[] board, Scanner getInput) {
        while (true) {
            System.out.println("Enter the index you want to move");
            int index = getInput.nextInt();
            if ((index > 0 && index < 10) && isBoardEmpty(board, index)) {
                return index;
            } else if ((index <= 0 && index >= 10)) {
                System.out.println("Invalid Index Try Again!!!");
            } else {
                System.out.println("Already Occupied");
            }
        }
    }

    // make move to the given index
    public static void makeMove(char[] board, int index, char letterInput) {
        if (isBoardEmpty(board, index)) {
            board[index] = letterInput;
        }
    }public static boolean isWinner(char[] board, char c) {
        return ((board[1] == c && board[2] == c && board[3] == c)
                || (board[4] == c && board[5] == c && board[6] == c)
                || (board[7] == c && board[8] == c && board[9] == c)
                || (board[1] == c && board[5] == c && board[9] == c)
                || (board[3] == c && board[5] == c && board[7] == c)
                || (board[1] == c && board[4] == c && board[7] == c)
                || (board[2] == c && board[5] == c && board[8] == c)
                || (board[3] == c && board[6] == c && board[9] == c));
    }
    private static Player tossWhoStartsFirst() {
        int tossResult = (int) (Math.floor(Math.random() * 10)) % 2;
        if (tossResult == HEAD) {
            System.out.println("player will start");
            return Player.player1;
        } else {
            System.out.println("computer will start");
            return Player.player2;
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to the Tic Tac Toe Game Program");
        Scanner userInput = new Scanner(System.in);
        char givenInput = userInput.next().charAt(0);
        char[] board = createBoard();
        char getLetterInput = getInput((givenInput) == 'X' ? 'O' : 'X');
        showBoard(board);
        int userMove = getUserMove(board, userInput);
        showBoard(board);
        makeMove(board, userMove, getLetterInput);
       showBoard(board);
        Player player = tossWhoStartsFirst();
        isWinner(board, getLetterInput);
}
}




















