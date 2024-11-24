package menus;

import java.util.Scanner;

import classes.Exemplar;
import function.CrudExemplar;
import services.ExemplarService;

public class MenuExemplar {
    public static void painelExemplar(String[] args) {
        Scanner input = new Scanner(System.in);
        CrudExemplar crud = new CrudExemplar();
        int opcao;

        do {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("1 - Cadastrar Exemplar");
            System.out.println("2 - Alterar Exemplar");
            System.out.println("3 - Excluir Exemplar");
            System.out.println("4 - Consultar Exemplar");
            System.out.println("5 - Voltar ao menu principal");
            System.out.println("╚═══════════════════════════════════════════════╝");
            System.out.print("Digite a opção desejada: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("-> Identificação do Livro: ");
                    String identificador = input.nextLine();
                    System.out.println("-> Edição: ");
                    String edicao = input.nextLine();
                    System.out.println("-> Estado de Conservação: ");
                    String estadoConservacao = input.nextLine();
                    System.out.println("-> Disponibilidade: ");
                    String disponibilidade = input.nextLine();
                    while (true) {
                        try {
                            ExemplarService.validarDisp(disponibilidade);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("-> Disponibilidade deve ser 'disp' para disponível ou 'indisp' para indisponível.");
                            disponibilidade = input.nextLine();
                        }
                    }
                    Exemplar exemplar = new Exemplar(identificador,edicao, estadoConservacao, disponibilidade);
                    crud.insert(exemplar);
                    break;
                case 2:
                    System.out.println("-> Id do exemplar: ");
                    String idExemplar = input.nextLine();

                    System.out.println("╔═══════════════════════════════════════════════╗");
                    System.out.println("1 - Edição");
                    System.out.println("2 - Estado de Conservação");
                    System.out.println("3 - Disponibilidade");
                    System.out.println("╚═══════════════════════════════════════════════╝");
                    System.out.println("O que deseja alterar? ");
                    int opcaoAlteracao = input.nextInt();
                    input.nextLine();
                    crud.update(idExemplar, opcaoAlteracao);
                    break;
                case 3:
                    System.out.println("-> Digite o id do exemplar: ");
                    String idExclusao = input.nextLine();
                    crud.delete(idExclusao);
                    break;
                case 4:
                    System.out.println("-> Digite o id do exemplar: ");
                    String idConsulta = input.nextLine();
                    System.out.println(crud.get(idConsulta));
                    break;
                case 5:
                    MenuPrincipal.menuPrincipal(args);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

            if (opcao != 5) {
                System.out.println("\nPressione Enter para voltar ao menu exemplar.");
                input.nextLine();
            }
        } while (opcao != 5);
        input.close();
    }
}
