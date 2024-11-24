package menus;

import java.util.Scanner;

import classes.Pessoa;
import function.CrudUsuario;
import services.PessoaService;

public class MenuCliente {
    public static void painelCliente(String[] args) {
        Scanner input = new Scanner(System.in);
        CrudUsuario crud = new CrudUsuario();
        int opcao;

        do {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Alterar Cliente");
            System.out.println("3 - Excluir Cliente");
            System.out.println("4 - Consultar Cliente");
            System.out.println("5 - Voltar ao menu principal");
            System.out.println("╚═══════════════════════════════════════════════╝");
            System.out.print("Digite a opção desejada: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("-> Nome do cliente: ");
                    String nome = input.nextLine();
                    System.out.println("-> CPF do cliente: ");
                    String cpf = input.nextLine();
                    while (true) {
                        try {
                            PessoaService.validarCpf(cpf);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("-> CPF do cliente precisa ter 11 caracteres, por favor faça a correção: ");
                            cpf = input.nextLine();
                        }
                        
                    }
                    System.out.println("-> Rua do cliente: ");
                    String rua = input.nextLine();
                    System.out.println("-> Número da casa do cliente: ");
                    int numeroCasa = input.nextInt();
                    input.nextLine();
                    System.out.println("-> Cidade do cliente: ");
                    String cidade = input.nextLine();
                    System.out.println("-> Estado do cliente: ");
                    String estado = input.nextLine();
                    System.out.println("-> Telefone do cliente: ");
                    String telefone = input.nextLine();
                    while (true) {
                        try {
                            PessoaService.validarNumero(telefone);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("-> Telefone do cliente precisa ter 8 caracteres, por favor faça a correção: ");
                            telefone = input.nextLine();
                        }   
                        
                    }
                    System.out.println("-> Data de Nascimento do cliente (yyyy/MM/dd): ");
                    String dt_nasc = input.nextLine();
                    while (true) {
                        if (PessoaService.validarDataNascimento(dt_nasc)) {
                            break;
                        } else {
                            System.out.println("-> Data de Nascimento do cliente, lembre-se o formato é o yyyy/MM/dd: ");
                            dt_nasc = input.nextLine();
                        }
                    }
                    Pessoa cliente = new Pessoa(cpf, nome, rua, numeroCasa ,cidade, estado, telefone, dt_nasc);
                    crud.insert(cliente);
                    
                    break;
                case 2:
                    System.out.println("-> CPF do cliente: ");
                    String cpfCliente = input.nextLine();

                    System.out.println("╔═══════════════════════════════════════════════╗");
                    System.out.println("1 - Nome");
                    System.out.println("2 - Rua");
                    System.out.println("3 - Número da casa");
                    System.out.println("4 - Cidade");
                    System.out.println("5 - Estado");
                    System.out.println("6 - Telefone");
                    System.out.println("7 - Data de Nascimento");
                    System.out.println("╚═══════════════════════════════════════════════╝");
                    System.out.println("O que deseja alterar? ");
                    int opcaoAlteracao = input.nextInt();
                    input.nextLine();
                    crud.update(cpfCliente, opcaoAlteracao);

                    break;
                case 3:
                    System.out.println("-> CPF do cliente: ");
                    String cpfExclusao = input.nextLine();
                    crud.delete(cpfExclusao);
                    break;
                case 4:
                    System.out.println("-> CPF do cliente: ");
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
                System.out.println("\nPressione Enter para voltar ao menu cliente.");
                input.nextLine();
            }
        } while (opcao != 5);
        input.close();
    }
}
