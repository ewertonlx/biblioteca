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
            System.out.println("4 - Menu Exemplar");
            System.out.println("5 - Menu Empréstimo");
            System.out.println("6 - Sair");
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
                    MenuLivro.painelLivro(args);
                    break;
                case 4:
                    MenuExemplar.painelExemplar(args);
                    break;
                case 5:
                    MenuEmprestimo.painelEmprestimo(args);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
            if (opcao != 6) {
                System.out.println("\nPressione Enter para voltar ao menu principal.");
                input.nextLine(); 
            }
        } while (opcao != 6);
        input.close();
    }
}
