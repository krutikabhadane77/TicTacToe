package com.tictactoegame;
import java.util.Scanner;
public class TicTacToe {
    public static char[] createBoard() {
        char[] board = new char[10];
        for (int position = 1; position < 10; position++) {
            board[position] = ' ';
        }
        return board;
    }

    public static char chooseLetter(char givenInput) {
       return Character.toUpperCase(givenInput);
    }

    public static void showBoard(char[] board) {
        System.out.println(board[1] + " | " + board[2] + " |" + " " + board[3]);
        System.out.println("---------");
        System.out.println(board[4] + " | " + board[5] + " |" + " " + board[6]);
        System.out.println("---------");
        System.out.println(board[7] + " | " + board[8] + " |" + " " + board[9]);
    }

    public static boolean checkPosition(char[] board, Scanner input) {
        System.out.println("Enter the user input ");
        int userInput = input.nextInt();
        boolean flag = true;
        while (flag) {
            if (board[userInput] == ' ') {
                return flag;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Tic Tac Toe Game Program");
        Scanner input = new Scanner(System.in);
        char givenInput = input.next().charAt(0);
        char[] board = createBoard();
        char ch = chooseLetter((chooseLetter(givenInput) == 'X' ? 'O' : 'X'));
        showBoard(board);
        boolean isEmpty = checkPosition(board, input);
        if (isEmpty) {
            showBoard(board);
        }
    }
}

