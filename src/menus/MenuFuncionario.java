package menus;

import java.util.Scanner;

import classes.Funcionario;
import function.CrudFuncionario;

public class MenuFuncionario {
    public static void painelFuncionario(String[] args) {
        Scanner input = new Scanner(System.in);
        CrudFuncionario crud = new CrudFuncionario();
        int opcao;

        do {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Alterar Funcionário");
            System.out.println("3 - Excluir Funcionário");
            System.out.println("4 - Consultar Funcionário");
            System.out.println("5 - Voltar ao menu principal");
            System.out.println("╚═══════════════════════════════════════════════╝");
            System.out.print("Digite a opção desejada: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("CPF do Funcionário: ");
                    String cpf = input.nextLine();
                    System.out.println("Cargo do Funcionário: ");
                    String cargo = input.nextLine();
                    Funcionario funcionario = new Funcionario(cargo, cpf);
                    crud.insert(funcionario);

                    break;
                case 2:
                    System.out.println("-> CPF do funcionário: ");
                    String cpfFuncionario = input.nextLine();

                    System.out.println("╔═══════════════════════════════════════════════╗");
                    System.out.println("1 - Nome");
                    System.out.println("2 - Rua");
                    System.out.println("3 - Número da casa");
                    System.out.println("4 - Cidade");
                    System.out.println("5 - Estado");
                    System.out.println("6 - Telefone");
                    System.out.println("7 - Data de Nascimento");
                    System.out.println("8 - Cargo");
                    System.out.println("╚═══════════════════════════════════════════════╝");
                    System.out.println("O que deseja alterar? ");
                    int opcaoAlteracao = input.nextInt();
                    input.nextLine();
                    crud.update(cpfFuncionario, opcaoAlteracao);
                    break;
                case 3:
                    System.out.println("Digite o CPF do Funcionário: ");
                    String cpfExclusao = input.nextLine();
                    crud.delete(cpfExclusao);
                    break;
                case 4:
                    System.out.println("Digite o CPF do Funcionário: ");
                    String cpfConsulta = input.nextLine();
                    System.out.println(crud.get(cpfConsulta));
                    break;
                case 5:
                    MenuPrincipal.menuPrincipal(args);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

            if (opcao != 5) {
                System.out.println("\nPressione Enter para voltar ao menu funcionario.");
                input.nextLine();
            }
        } while (opcao != 5);
        input.close();
    }
}
