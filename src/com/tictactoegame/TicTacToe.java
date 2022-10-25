package com.tictactoegame;
import java.util.Scanner;
public class TicTacToe {
    public static final int HEAD = 0;
    public static final int TAIL = 1;


    public static char[] createBoard() {
        char[] board = new char[10];
        for (int position = 1; position < 10; position++) {
            board[position] = ' ';
        }
        return board;
    }

    // get input from the user
    public static char getInput(Scanner givenInput) {
        System.out.println("choose X or O");
        String letter = givenInput.next();
        while (!letter.equalsIgnoreCase("x") && !letter.equalsIgnoreCase("o")) {
            System.out.println("please enter correct letter : x or o");
            letter = givenInput.next();
        }
        return letter.toUpperCase().charAt(0);
    }

    // display the board
    public static void showBoard(char[] board) {
        System.out.println(board[1] + " | " + board[2] + " |" + " " + board[3]);
        System.out.println("---------");
        System.out.println(board[4] + " | " + board[5] + " |" + " " + board[6]);
        System.out.println("---------");
        System.out.println(board[7] + " | " + board[8] + " |" + " " + board[9]);
    }


    public static boolean isBoardEmpty(char[] board, int index) {
        return board[index] == ' ';
    }

    public static char[] userInputMove(char[] board, Scanner userInput, char playerLetter) {
        System.out.println("Enter the index from 1-9 to make a move");
        int position = userInput.nextInt();
        while (position >= 10 || position <= 0) {
            System.out.println("enter correct position between 1 and 9");
            position = userInput.nextInt();
        }
        while (!isBoardEmpty(board, position)) {
            System.out.println("the position is already occupied");
            position = userInput.nextInt();
        }
        board[position] = playerLetter;
        return board;
    }


    public static void makeMove(char[] board, int userInput, char letterInput) {
        if (isBoardEmpty(board, userInput)) {
            board[userInput] = letterInput;
        }
    }


    private static String tossWhoStartsFirst() {
        System.out.println("Toss the coin");
        int tossResult = (int) (Math.floor(Math.random() * 10)) % 2;
        if (tossResult == HEAD) {
            System.out.println("player will start");
            return "Player";
        } else {
            System.out.println("computer will start");
            return "Computer";
        }
    }


    public static boolean isWinner(char[] board, char c) {
        return ((board[1] == c && board[2] == c && board[3] == c) || (board[4] == c && board[5] == c && board[6] == c)
                || (board[7] == c && board[8] == c && board[9] == c)
                || (board[1] == c && board[5] == c && board[9] == c)
                || (board[3] == c && board[5] == c && board[7] == c)
                || (board[1] == c && board[4] == c && board[7] == c)
                || (board[2] == c && board[5] == c && board[8] == c)
                || (board[3] == c && board[6] == c && board[9] == c));
    }


    public static boolean checkTie(char[] board) {
        for (int position = 0; position < 10; position++) {
            if (board[position] == ' ') {
                return false;
            }
        }
        return true;
    }


    public static int winningPosition(char[] board, char letter) {
        for (int position = 1; position < 10; position++) {
            if (isBoardEmpty(board, position)) {
                board[position] = letter;
                if (isWinner(board, letter)) {
                    board[position] = ' ';
                    return position;
                }
                board[position] = ' ';
            }
        }
        return 0;
    }


    public static boolean computerPlay(char[] board, char computerInput, char playerInput) {
        int position = 0;
        position = winningPosition(board, computerInput);
        if (position != 0) {
            board[position] = computerInput;
            return true;
        }
        int blockIndex = denyWinOpponent(board, playerInput);
        if (blockIndex != 0) {
            board[blockIndex] = computerInput;
            return false;
        }
        position = chooseCornerPosition(board);
        if (position == 0) {
            position = centerOrSides(board);
        }
        board[position] = computerInput;
        return false;
    }


    public static int denyWinOpponent(char[] board, char playerInput) {
        int index = 0;
        index = winningPosition(board, playerInput);
        return index;
    }


    public static int chooseCornerPosition(char[] board) {
        int[] boardCorners = { 1, 3, 7, 9 };
        for (int position = 0; position < boardCorners.length; position++) {
            if (isBoardEmpty(board, boardCorners[position]))
                return position;
        }
        return 0;
    }


    public static int centerOrSides(char[] board) {
        if (isBoardEmpty(board, 5)) // center index
            return 5;
        int[] sides = { 2, 4, 6, 8 }; // side indexes
        for (int index = 0; index <= 3; index++)
            if (isBoardEmpty(board, sides[index])) // checking board empty or not
                return index;
        return 0;

    }


    public static void playGameUntillItEnd(char[] board, char playerInput, char computerInput, Scanner userInputScanner,
                                           String firstPlayer) {
        int toss = firstPlayer.equalsIgnoreCase("Computer") ? 1 : 0;
        while (!checkTie(board)) {
            if (toss == 0) {
                board = userInputMove(board, userInputScanner, playerInput);
                showBoard(board);
                if (isWinner(board, playerInput)) {
                    System.out.println("player is the winner");
                    return;
                }
                toss = 1;
            } else {
                System.out.println("changing turn");
                if (computerPlay(board, computerInput, playerInput)) {
                    showBoard(board);
                    System.out.println("computer is the winner");
                    return;
                }
                toss = 0;
            }
            showBoard(board);
        }
        System.out.println("its a tie");
    }

    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        int flag = 1;
        while (flag == 1) {
            System.out.println("Welcome to the Tic Tac Toe Game Program");
            char[] board = createBoard();
            char playerInput = getInput(userInputScanner);
            System.out.println("Player 1 choose : " + playerInput);
            char computerInput = (playerInput == 'X') ? 'O' : 'X';
            String playStarter = tossWhoStartsFirst();
            System.out.println("Choose X or O");
            showBoard(board);
            playGameUntillItEnd(board, playerInput, computerInput, userInputScanner, playStarter);
            flag = userInputScanner.nextInt();
        }

    }
}