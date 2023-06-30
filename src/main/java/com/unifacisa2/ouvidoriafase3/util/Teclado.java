package com.unifacisa2.ouvidoriafase3.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Teclado {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static int tInt() {

        System.out.print("\nDigite a opcao: ");
        try {
            int tInt = Integer.parseInt(scanner.nextLine());
            return tInt;
        } catch (NumberFormatException e) {
            System.err.println("[Erro] Digite um numero\n");
        } catch (NoSuchElementException e) {
            System.err.println("[Erro] Digite um numero\n");
        }
        return -1;
    }

    public static String tString() {
        try {
            String tString = scanner.nextLine();
            return tString;
        } catch (NoSuchElementException e) {
            System.err.println("Tipo invalido");
        }
        return "";
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void closeScanner() {
        scanner.close();
    }
    
}

