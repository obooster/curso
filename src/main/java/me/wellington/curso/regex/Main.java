package me.wellington.curso.regex;

import java.util.Scanner;

public final class Main {

    public void init() {
        var scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("8 digitos, uma letra maiúscula, uma letra minúscula, um número e um caractere especial.");
        System.out.print("Digite uma senha que atenda os critérios: ");
        var text = scanner.nextLine();

        var regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#%&*!])[a-zA-Z\\d@#%&*!]{8,}$";
        // ^ e $ -- inicio e fim de uma string
        // (?=.*[a-zA-Z] -- verifica se tem caracteres maiusculos e minusculos
        // (?=.*\\d) -- verifica se tem numeros
        // (?=.*[@#%&*!]) -- verifica se tem os caracteres especiais citados
        // [a-zA-Z\d@#%&*!]{8,} -- verifica se tem 8 caracteres ou mais

        System.out.println(text.matches(regex) ? "A senha está dentro de todos os critérios." : "A senha não está dentro dos critérios.");
    }

    public static void main(String[] args) {
        new Main().init();
    }

}
