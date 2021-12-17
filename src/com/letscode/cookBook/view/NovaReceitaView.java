package com.letscode.cookBook.view;

import com.letscode.cookBook.domain.Ingrediente;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.Categoria;
import com.letscode.cookBook.enums.TipoMedida;
import com.letscode.cookBook.enums.TipoRendimento;

import java.util.Scanner;

public class NovaReceitaView {
    Scanner scanner;
    Receita receita;
    String nome;

    public NovaReceitaView() {
        this.scanner = new Scanner(System.in);
    }

    public Receita montarReceita() {
        String nome = askNome();
        Categoria categoria = askCategoria();

        receita = new Receita(nome, categoria);
        receita.setTempoPreparo(askTempoPreparo());
        receita.setRendimento(montarRendimento());

        System.out.println("Quantos ingredientes sua receita possui?");
        int quantidadeDeIngredientes = scanner.nextInt();
        for (int i = 0; i < quantidadeDeIngredientes; i++) {
            receita.setIngredientes(new Ingrediente[]{montarIngrediente(i+1)});
        }

        System.out.println("Quantos passos possui o modo de preparo da sua receita?");
        int quantidadePassosModoPreparo = scanner.nextInt();
        String[] modoPreparo = new String[quantidadePassosModoPreparo];

        for (int i = 0; i < quantidadePassosModoPreparo; i++) {
           modoPreparo[i] = askModoPreparo(i+1);
        }

        receita.setModoPreparo(modoPreparo);

        return receita;
    }

    private Rendimento montarRendimento () {
        int quantidade = askRendimento();
        TipoRendimento tipo = askTipoRendimento();

        return new Rendimento(quantidade, tipo);
    }

    private Ingrediente montarIngrediente (int index) {
        String nome = askIngrediente(index);
        double quantidade = askQuantidadeIngrediente(index);
        TipoMedida medida = askMedidaIngrediente(index);

        return new Ingrediente(nome, quantidade, medida);
    }

    private String askNome() {
        System.out.println("Qual o nome da receita?");
        nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Nome inválido!");
            askNome();
        }

        return nome;
    }

    private Categoria askCategoria() {
        System.out.println("Qual a categoria da receita?");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d - %s %n", cat.ordinal(), cat.name());
        }
        int categoria = scanner.nextInt();
        if (categoria < 0 || categoria >= Categoria.values().length) {
            System.out.println("Categoria inválida!");
            askCategoria();
        }

        return Categoria.values()[categoria];
    }

    private int askTempoPreparo() {
        System.out.println("Qual o tempo de preparo da receita (insira em segundos)?");
        int tempoPreparo = scanner.nextInt();
        if (tempoPreparo < 0) {
            System.out.println("Tempo de preparo inválido!");
            askTempoPreparo();
        }

        return tempoPreparo;
    }

    private int askRendimento() {
        System.out.println("Qual o rendimento da receita?");
        int rendimento = scanner.nextInt();
        if (rendimento < 0) {
            System.out.println("Rendimento inválido!");
            askRendimento();
        }

        return rendimento;
    }

    private TipoRendimento askTipoRendimento() {
        System.out.println("Qual o tipo do rendimento da receita?");
        for (TipoRendimento tr : TipoRendimento.values()) {
            System.out.printf("%d - %s %n", tr.ordinal(), tr.name());
        }
        int tipoRendimento = scanner.nextInt();
        if (tipoRendimento < 0 || tipoRendimento >= TipoRendimento.values().length) {
            System.out.println("Tipo de rendimento inválido!");
            askTipoRendimento();
        }

        return TipoRendimento.values()[tipoRendimento];
    }

    private String askIngrediente(int index) {
        this.scanner = new Scanner(System.in);

        System.out.println("Qual o " + index + "º ingrediente da receita?");
        String ingrediente = scanner.nextLine();
        if (ingrediente.isBlank()) {
            System.out.println("Ingrediente inválido!");
            askIngrediente(index);
        }

        return ingrediente;
    }

    private double askQuantidadeIngrediente(int index) {
        System.out.println("Qual a quantidade do " + index + "º ingrediente da receita?");
        double quantidade = scanner.nextDouble();
        if (quantidade <= 0) {
            System.out.println("Quantidade do ingrediente inválida!");
            askQuantidadeIngrediente(index);
        }

        return quantidade;
    }

    private TipoMedida askMedidaIngrediente(int index) {
        System.out.println("Qual o tipo da medida do " + index + "º ingrediente da receita?");
        for (TipoMedida tmi : TipoMedida.values()) {
            System.out.printf("%d - %s %n", tmi.ordinal(), tmi.name());
        }
        int tipoMedida = scanner.nextInt();
        if (tipoMedida < 0 || tipoMedida >= TipoMedida.values().length) {
            System.out.println("Tipo de medida inválido!");
            askMedidaIngrediente(index);
        }

        return TipoMedida.values()[tipoMedida];
    }

    private String askModoPreparo(int index) {
        this.scanner = new Scanner(System.in);

        System.out.println("Qual o " + index + "º passo do modo de preparo da receita?");
        String modoPreparo = scanner.nextLine();
        if (modoPreparo.isBlank()) {
            System.out.println("Modo de preparo inválido!");
            askModoPreparo(index);
        }

        return modoPreparo;
    }
}
