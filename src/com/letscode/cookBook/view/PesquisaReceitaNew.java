package com.letscode.cookBook.view;

import com.letscode.cookBook.controller.Catalogo;
import com.letscode.cookBook.domain.Receita;

import java.util.Scanner;

public class PesquisaReceitaNew {
    Scanner scanner;
    String pesquisa;

    public PesquisaReceitaNew() {
        this.scanner = new Scanner(System.in);
    }

    public Receita pesquisa(Catalogo controller) {
        System.out.println("Digite o nome da receita que deseja encontrar:");
        pesquisa = scanner.nextLine();
        if (pesquisa.isBlank()) {
            System.out.println("A pesquisa não pode ser em branco!");
            pesquisa(controller);
        }

        Receita receita = controller.getReceita(pesquisa);

        return receita;
    }
}
