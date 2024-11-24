package menus;

import java.util.Scanner;

import classes.Livro;
import function.CrudLivro;

public class MenuLivro {
    public static void painelLivro(String[] args) {
        Scanner input = new Scanner(System.in);
        CrudLivro crud = new CrudLivro();
        int opcao;

        do {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Alterar Livro");
            System.out.println("3 - Excluir Livro");
            System.out.println("4 - Consultar Livro");
            System.out.println("5 - Voltar ao menu principal");
            System.out.println("╚═══════════════════════════════════════════════╝");
            System.out.print("Digite a opção desejada: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Titulo do Livro: ");
                    String titulo = input.nextLine();
                    System.out.println("Autor do Livro: ");
                    String autor = input.nextLine();
                    System.out.println("Ano de Publicação do Livro: ");
                    String anoPublicacao = input.nextLine();
                    System.out.println("Digite um identificador para esse livro: ");
                    String identificador = input.nextLine();
                    Livro livro = new Livro(titulo, autor, anoPublicacao, identificador);
                    crud.insert(livro);
                    break;
                case 2:
                    System.out.println("-> Id do livro: ");
                    String idLivro = input.nextLine();

                    System.out.println("╔═══════════════════════════════════════════════╗");
                    System.out.println("1 - Titulo");
                    System.out.println("2 - Autor");
                    System.out.println("3 - Ano de Publicação");
                    System.out.println("╚═══════════════════════════════════════════════╝");
                    System.out.println("O que deseja alterar? ");
                    int opcaoAlteracao = input.nextInt();
                    input.nextLine();
                    crud.update(idLivro, opcaoAlteracao);
                    break;
                case 3:
                    System.out.println("Digite o id do livro: ");
                    String idExclusao = input.nextLine();
                    crud.delete(idExclusao);
                    break;
                case 4:
                    System.out.println("Digite o id do livro: ");
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
                System.out.println("\nPressione Enter para voltar ao menu livro.");
                input.nextLine();
            }
        } while (opcao != 5);
        input.close();
    }
}
