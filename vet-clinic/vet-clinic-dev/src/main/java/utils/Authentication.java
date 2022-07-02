package utils;

import java.util.Scanner;

public class Authentication {
    public static final String LOGIN = "admin";
    public static final String PASSWORD = "pelmen123";

    public static boolean auth() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();

        return login.equals(LOGIN) & password.equals(PASSWORD);
    }
}
