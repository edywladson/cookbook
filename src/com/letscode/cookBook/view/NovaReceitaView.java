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
        scanner = new Scanner(System.in);

        receita = new Receita(askNome(), askCategoria());
        receita.setTempoPreparo(askTempoPreparo());
        receita.setRendimento(askRendimento());
        askIngredientes();
        askModoDePreparo();

        return receita;
    }

    private String askNome() {
        System.out.println("Qual o nome da receita?");
        nome = isBlank("O nome da receita é inválido! Ele não pode estar em branco.");

        return nome;
    }

    private Categoria askCategoria() {
        System.out.println("Qual a categoria da receita?");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d - %s %n", cat.ordinal(), cat.name());
        }

        int categoria = isInteiro("Categoria inválida! Escolha um das opções acima.",
                "Categoria inválida! Escolha um das opções acima.");

        return Categoria.values()[categoria];
    }

    private int askTempoPreparo() {
        System.out.println("Qual o tempo de preparo da receita em segundos?");

        int tempoPreparo = isInteiro("Tempo de preparo inválido! O tempo de preparo deve ser em segundos.",
                "Tempo de preparo inválido!");

        return tempoPreparo;
    }

    private Rendimento askRendimento() {
        this.scanner = new Scanner(System.in);

        int rendimento;
        int tipo;

        System.out.println("Qual o rendimento da receita?");

        rendimento = isInteiro("Redimento inválido! O redimento deve ser numérico.",
                "Rendimento inválido!");

        System.out.println("Qual o tipo do rendimento da receita?");
        for (TipoRendimento tr : TipoRendimento.values()) {
            System.out.printf("%d - %s %n", tr.ordinal(), tr.name());
        }

        tipo = isInteiro("Tipo de rendimento inválido! Escolha um das opções acima.",
                "Tipo de rendimento inválido! Escolha um das opções acima.");

        return new Rendimento(rendimento, TipoRendimento.values()[tipo]);
    }

    private void askIngredientes() {
        this.scanner = new Scanner(System.in);

        System.out.println("\n##### INSIRA OS INGREDIENTES DE SUA RECEITA #####");

        int count = 1;
        String ingrediente;
        double quantidade;
        int medida;
        String verificacao;

        do {
            System.out.println("Qual o nome do " + count + "º ingrediente da receita?");
            ingrediente = isBlank("O nome do ingrediente está inválido! Ele não pode estar em branco.");

            System.out.println("Qual a quantidade do " + count + "º ingrediente da receita?");
            quantidade = isDouble("Quantidade do ingrediente inválida! A quantidade deve ser numérica.",
                    "Quantidade do ingrediente inválida!");

            System.out.println("Qual o tipo da medida do " + count + "º ingrediente da receita?");
            for (TipoMedida tmi : TipoMedida.values()) {
                System.out.printf("%d - %s %n", tmi.ordinal(), tmi.name());
            }

            medida = isInteiro("Tipo de medida inválido! Escolha um das opções acima.",
                    "Tipo de medida inválido! Escolha um das opções acima.");

            receita.setIngredientes(new Ingrediente[]{new Ingrediente(ingrediente, quantidade, TipoMedida.values()[medida])});
            count++;

            do {
                System.out.print("Deseja inserir um novo ingrediente? (S/N) ");
                verificacao = scanner.nextLine().toUpperCase();
            } while (!verificacao.equals("S") && !verificacao.equals("N"));
        } while (!verificacao.equals("N"));
    }

    private void askModoDePreparo() {
        this.scanner = new Scanner(System.in);

        System.out.println("\n##### INSIRA OS PASSOS DO MODE DE PREPARO DE SUA RECEITA #####");

        int count = 1;
        String modoDePreparo;
        String verificacao;

        do {
            System.out.println("Qual o " + count + "º passo do modo de preparo da receita?");
            modoDePreparo = isBlank("O modo de preparo está inválido! Ele não pode estar em branco.");

            receita.setModoPreparo(new String[]{modoDePreparo});
            count++;

            do {
                System.out.print("Deseja inserir mais um passo ao modo de preparo? (S/N) ");
                verificacao = scanner.nextLine().toUpperCase();
            } while (!verificacao.equals("S") && !verificacao.equals("N"));
        } while (!verificacao.equals("N"));
    }

    private int isInteiro(String mensagemUm, String mensagemDois) {
        int valor;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println(mensagemUm);
                scanner.next();
            }

            valor = scanner.nextInt();
            if (valor < 0) {
                System.out.println(mensagemDois);
            }
        } while (valor < 0);

        return valor;
    }

    private double isDouble(String mensagemUm, String mensagemDois) {
        double valor;
        do {
            while (!scanner.hasNextDouble()) {
                System.out.println(mensagemUm);
                scanner.next();
            }

            valor = scanner.nextDouble();
            if (valor < 0) {
                System.out.println(mensagemDois);
            }
        } while (valor < 0);

        return valor;
    }

    private String isBlank(String mensagem) {
        String nome = scanner.nextLine();

        do {
            if (nome.isBlank()) {
                System.out.println(mensagem);
                askNome();
            }
        } while (nome.isBlank());

        return nome;
    }
}
