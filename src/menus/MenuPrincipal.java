package menus;

import java.util.Scanner;

public class MenuPrincipal {
    public static void menuPrincipal(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("1 - Menu Funcionário");
            System.out.println("2 - Menu Cliente");
            System.out.println("3 - Menu Livro");
            System.out.println("4 - Menu Empréstimo");
            System.out.println("5 - Excluir Cliente, Funcionário ou Livro");
            System.out.println("8 - Sair");
            System.out.println("╚═══════════════════════════════════════════════╝");
            System.out.print("Digite a opção desejada: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    MenuFuncionario.painelFuncionario(args);
                    break;
                case 2:
                    MenuCliente.painelCliente(args);
                    break;
                case 3:

                    System.out.println("Cadastrar Livro");
                    break;
                case 4:
                    System.out.println("Cadastrar Produto");
                    break;
                case 5:
                    System.out.println("Cadastrar Venda");
                    break;
                case 6:
                    System.out.println("Cadastrar Compra");
                    break;
                case 7:
                    System.out.println("Relatórios");
                    break;
                case 8:
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
            if (opcao != 8) {
                System.out.println("\nPressione Enter para voltar ao menu principal.");
                input.nextLine(); 
            }
        } while (opcao != 8);
        input.close();
    }
}
