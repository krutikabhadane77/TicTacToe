package com.tictactoegame;

public class TicTacToe {
    public void createboard(int size) {
        char[] board = new char[size];
        for(int i = 1; i < 10; i++) {
            board[i] = ' ';
        }

    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Tic Tac Toe Game Program");
        TicTacToe tg = new TicTacToe();
        tg.createboard(10);

    }

}
