package com.spyrostsat;

public class Main {
    public static void main(String[] args) {
        int user_choice;

        while (true) {
            user_choice = Utils.Menu();
            Utils.choosePath(user_choice);
        }
    }
}