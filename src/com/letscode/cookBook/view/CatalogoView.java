package com.letscode.cookBook.view;

import com.letscode.cookBook.controller.Catalogo;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.enums.Categoria;

import java.util.Scanner;

public class CatalogoView {
    private final Receita NONE_FOUND = new Receita("Nenhuma receita encontrada", Categoria.PRATO_UNICO);
    private Receita receita;
    private NovaReceitaView novaReceita = new NovaReceitaView();
    Catalogo controller = new Catalogo();
    private int curIndex = -1;

    private void showHeader() {
        ScreenUtil.printTextLine("", 80, true, '=');
        ScreenUtil.printTextLine("#### #### #### #  #  ###  #### #### #  #", 80, true, ' ');
        ScreenUtil.printTextLine("#    #  # #  # # #   #  # #  # #  # # # ", 80, true, ' ');
        ScreenUtil.printTextLine("#    #  # #  # ##    ###  #  # #  # ##  ", 80, true, ' ');
        ScreenUtil.printTextLine("#    #  # #  # # #   #  # #  # #  # # # ", 80, true, ' ');
        ScreenUtil.printTextLine("#### #### #### #  #  ###  #### #### #  #", 80, true, ' ');
        ScreenUtil.printTextLine("", 80, true, '=');
    }

    private void showReceita(Receita receita) {
        System.out.println(receita.toString());
    }

    private void showAnterior() {
        if (curIndex > 0 || controller.getReceita(curIndex - 1) != null) {
            this.receita = controller.getReceita(curIndex - 1);
            if (receita != null) curIndex--;
            show();
        } else {
            if(controller.getAllReceitas().size() == 0){
                System.out.println("Ainda não existem receitas cadastradas, utilize o comando + para adicionar sua primeira receita.");
            } else {
                if(controller.getAllReceitas().size() == 1) {
                    System.out.println("Esta é a única receite que existe! Não existem receitas antes ou depois dela.");
                    System.out.println("Utilize o comando + para adicionar uma nova receita.");
                } else {
                    System.out.println("Esta é a primeira receite! Não existem receitas antes dela.");
                    System.out.println("Se deseja ver outras, utilize o comando N para avançar para próxima receita ou S para pesquisar a receita que deseja.");
                }
            }
        }
    }

    private void showSeguinte() {
        if(controller.getReceita(curIndex + 1) != null) {
            this.receita = controller.getReceita(curIndex + 1);
            if (receita != null) curIndex++;
            show();
        } else {
            if(controller.getAllReceitas().size() == 0){
                System.out.println("Ainda não existem receitas cadastradas, utilize o comando + para adicionar sua primeira receita.");
            } else {
                if(controller.getAllReceitas().size() == 1) {
                    System.out.println("Esta é a única receite que existe! Não existem receitas antes ou depois dela.");
                    System.out.println("Utilize o comando + para adicionar uma nova receita.");
                } else {
                    System.out.println("Esta é a última receite! Não existem receitas depois dela.");
                    System.out.println("Se deseja ver outras, utilize o comando P para voltar para a receita anterior ou S para pesquisar a receita que deseja.");
                }
            }
        }
    }

    private void add() {
        this.receita = novaReceita.montarReceita();
        controller.add(this.receita);
        curIndex++;
        show();
    }

    private void del() {
        if(controller.getAllReceitas().size() == 0){
            System.out.println("Não dá para excluir o que não existe! Utilize o comando + para adicionar sua primeira receita.");
        } else {
            if (curIndex >= 0) {
                controller.del(receita.getNome());
            }

            if(controller.getReceita(curIndex - 1) != null) {
                curIndex--;
                this.receita = controller.getReceita(curIndex);
            } else if (controller.getReceita(curIndex + 1) != null) {
                if(curIndex > 0) {
                    curIndex++;
                }
                this.receita = controller.getReceita(curIndex);
            } else {
                this.receita = controller.getReceita(curIndex);
            }

            if (controller.getAllReceitas().size() == 0) {
                curIndex = -1;
                this.receita = null;
            }

            show();
        }
    }

    private void search() {
        if(controller.getAllReceitas().size() == 0){
            System.out.println("Não dá para pesquisar o que não existe! Utilize o comando + para adicionar sua primeira receita.");
        } else {
            this.receita = new PesquisaReceitaNew().pesquisa(controller);
            curIndex = controller.getAllReceitas().indexOf(this.receita);
            show();
        }
    }

    public void show() {
        showHeader();
        showReceita(receita == null ? NONE_FOUND : receita);
        ScreenUtil.printTextLine("", 80, true, '=');

        if(controller.getReceita(curIndex - 1) != null) {
            ScreenUtil.printTextLine("P: Receita anterior", 80, true);
        }

        if(controller.getReceita(curIndex + 1) != null) {
            ScreenUtil.printTextLine("N: Receita seguinte", 80, true);
        }

        ScreenUtil.printTextLine("+: Adicionar nova receita", 80, true);

        if (controller.getAllReceitas().size() != 0) {
            ScreenUtil.printTextLine("-: Remover receita", 80, true);
        }

        if (controller.getAllReceitas().size() > 1) {
            ScreenUtil.printTextLine("S: Pesquisar receita", 80, true);
        }

        ScreenUtil.printTextLine("", 80, true, '=');
        ScreenUtil.printTextLine("#: ", 80);
        String option;
        do {
            option = new Scanner(System.in).next();
            switch (option.toUpperCase()) {
                case "P":
                    showAnterior();
                    break;
                case "N":
                    showSeguinte();
                    break;
                case "+":
                    add();
                    break;
                case "-":
                    del();
                    break;
                case "S":
                    search();
                    break;
                default:
                    ScreenUtil.printTextLine("Opção inválida", 80);
                    ScreenUtil.printTextLine("#: ", 80);
            }
        } while (true);
    }
}
