package menus;

import java.util.Scanner;
import classes.Emprestimo;
import function.CrudEmprestimo;

public class MenuEmprestimo {
    public static void painelEmprestimo(String[] args) {
        Scanner input = new Scanner(System.in);
        CrudEmprestimo crud = new CrudEmprestimo();
        int opcao;

        do {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("1 - Registrar Empréstimo");
            System.out.println("2 - Alterar Empréstimo");
            System.out.println("3 - Excluir Empréstimo");
            System.out.println("4 - Consultar Empréstimo");
            System.out.println("5 - Voltar ao menu principal");
            System.out.println("╚═══════════════════════════════════════════════╝");
            System.out.print("Digite a opção desejada: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("CPF do Cliente: ");
                    String cpf = input.nextLine();
                    System.out.println("Identificação do Exemplar: ");
                    String identificador = input.nextLine();
                    System.out.println("Data de Empréstimo: ");
                    String dataEmprestimo = input.nextLine();
                    System.out.println("Data de Devolução: ");
                    String dataDevolucao = input.nextLine();
                    Emprestimo emprestimo = new Emprestimo(cpf, identificador, dataEmprestimo, dataDevolucao);
                    crud.insert(emprestimo);
                    break;
                case 2:
                    System.out.println("-> Id do empréstimo: ");
                    String idEmprestimo = input.nextLine();
                    System.out.println("╔═══════════════════════════════════════════════╗");
                    System.out.println("1 - Data de Empréstimo");
                    System.out.println("2 - Data de Devolução");
                    System.out.println("╚═══════════════════════════════════════════════╝");
                    System.out.println("O que deseja alterar? ");
                    int opcaoAlteracao = input.nextInt();
                    input.nextLine();
                    crud.update(idEmprestimo, opcaoAlteracao);
                    break;
                case 3:
                    System.out.println("Digite o id do empréstimo: ");
                    String idExclusao = input.nextLine();
                    crud.delete(idExclusao);
                    break;
                case 4:
                    System.out.println("Digite o id do empréstimo: ");
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
                System.out.println("\nPressione Enter para voltar ao menu empréstimo.");
                input.nextLine();
            }
        } while (opcao != 5);
        input.close();
    }
}
