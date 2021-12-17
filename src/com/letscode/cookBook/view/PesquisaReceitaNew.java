package com.letscode.cookBook.view;

import java.util.Scanner;

public class PesquisaReceitaNew {
    Scanner scanner;
    String nome;

    public PesquisaReceitaNew() {
        this.scanner = new Scanner(System.in);
    }

    public String askNome() {
        System.out.println("Digite o nome da receita que deseja encontrar:");
        nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("A pesquisa não pode ser em branco!");
            askNome();
        }

        return nome;
    }
}
