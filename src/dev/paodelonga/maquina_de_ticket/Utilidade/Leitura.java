package dev.paodelonga.maquina_de_ticket.Utilidade;

import java.util.Scanner;

public class Leitura {
    private static Scanner leitorEntrada = new Scanner(System.in);

    public static int lerInteiro() {
        String entradaLeitor = leitorEntrada.nextLine();

        while (entradaLeitor.isBlank()) {
            System.out.print("[!]: Entrada inválida, a entrada não pode ser vazia. ");
            entradaLeitor = leitorEntrada.nextLine();

            if (!entradaLeitor.isBlank()) {
                break;
            }
        }

        try {
            return Integer.parseInt(entradaLeitor);
        } catch (NumberFormatException e) {
            System.out.print("[!]: Entrada inválida, insira um número inteiro. ");
            leitorEntrada.reset();
            return lerInteiro();
        }
    }


    public static float lerFloat() {
        String entradaLeitor = leitorEntrada.nextLine();

        while (entradaLeitor.isBlank()) {
            System.out.print("[!]: Entrada inválida, a entrada não pode ser vazia. ");
            entradaLeitor = leitorEntrada.nextLine();

            if (!entradaLeitor.isBlank()) {
                break;
            }
        }

        try {
            return Float.parseFloat(entradaLeitor);
        } catch (NumberFormatException e) {
            System.out.print("[!]: Entrada inválida, insira um número delimitado por pontos. ");
            leitorEntrada.reset();
            return lerFloat();
        }
    }

    public static String lerString() {
        leitorEntrada = new Scanner(System.in);
        String entradaLeitor = leitorEntrada.nextLine();

        while (entradaLeitor.isBlank()) {
            System.out.print("[!]: Entrada inválida, a entrada não pode ser vazia. ");
            entradaLeitor = leitorEntrada.nextLine();

            if (!entradaLeitor.isBlank()) {
                break;
            }
        }

        return entradaLeitor;
    }

    public static boolean lerEnter() {
        String entradaLeitor = leitorEntrada.nextLine();

        while (!entradaLeitor.isEmpty()) {
            entradaLeitor = leitorEntrada.nextLine();

            if (entradaLeitor.isEmpty()) {
                break;
            }
        }
        return true;
    }

    public static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        return lerInteiro();
    }

    public static float lerFloat(String mensagem) {
        System.out.print(mensagem);
        return lerFloat();
    }

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return lerString();
    }

    public static boolean lerEnter(String mensagem) {
        System.out.print(mensagem);
        return lerEnter();
    }
}