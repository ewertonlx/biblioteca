package function;
import interfaces.Crud;
import sql.Conexao;
import classes.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class CrudFuncionario implements Crud<Funcionario> {
    Scanner input = new Scanner(System.in);
    @Override
    public void insert(Funcionario f) {
        String sqlCheckPessoa = "SELECT id FROM pessoa WHERE cpf = ?";
        String sqlInsertFuncionario = "INSERT INTO funcionario (id_funcionario, cargo) VALUES (?, ?)";

        try (Connection connection = Conexao.getConexao();
            PreparedStatement psCheckPessoa = connection.prepareStatement(sqlCheckPessoa);
            PreparedStatement psInsertFuncionario = connection.prepareStatement(sqlInsertFuncionario)) {

            psCheckPessoa.setString(1, f.getCpf());
            ResultSet rs = psCheckPessoa.executeQuery();
            if (rs.next()) {
                int pessoaId = rs.getInt("id");
                psInsertFuncionario.setInt(1, pessoaId);
                psInsertFuncionario.setString(2, f.getCargo());
                psInsertFuncionario.executeUpdate();

                System.out.println("-> Funcionário inserido com sucesso! <-");
            } else {
                System.err.println("!!!!!!!!! Erro: A pessoa com o CPF " + f.getCpf() + " não foi encontrada. !!!!!!!!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao inserir funcionário: " + e.getMessage());
        }
    }


    @Override
    public String get(String cpf) {
        String sql = "SELECT p.id, p.cpf, p.nome, p.rua, p.numero, p.cidade, p.estado, p.dtnasc, " +
                    "f.cargo " +
                    "FROM pessoa p " +
                    "JOIN funcionario f ON p.id = f.id_funcionario " +
                    "WHERE p.cpf = ?";
        String resultado = "";

        try (Connection connection = Conexao.getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                resultado = "ID: " + rs.getInt("id") + "\n" +
                            "CPF: " + rs.getString("cpf") + "\n" +
                            "Nome: " + rs.getString("nome") + "\n" +
                            "Rua: " + rs.getString("rua") + "\n" +
                            "Número: " + rs.getInt("numero") + "\n" +
                            "Cidade: " + rs.getString("cidade") + "\n" +
                            "Estado: " + rs.getString("estado") + "\n" +
                            "Data de Nascimento: " + rs.getString("dtnasc") + "\n" +
                            "Cargo: " + rs.getString("cargo");
            } else {
                resultado = "Nenhuma pessoa encontrada com o CPF: " + cpf;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public void update(String cpf, int opcao) {
        String sql = "UPDATE pessoa SET nome = ? WHERE cpf = ?";
        switch (opcao) {
            case 1:
                sql = "UPDATE pessoa SET nome = ? WHERE cpf = ?";
                break;
            case 2:
                sql = "UPDATE pessoa SET rua = ? WHERE cpf = ?";
                break;
            case 3:
                sql = "UPDATE pessoa SET numero = ? WHERE cpf = ?";
                break;
            case 4:
                sql = "UPDATE pessoa SET cidade = ? WHERE cpf = ?";
                break;
            case 5:
                sql = "UPDATE pessoa SET estado = ? WHERE cpf = ?";
                break;
            case 6:
                sql = "UPDATE pessoa SET telefone = ? WHERE cpf = ?";
                break;
            case 7:
                sql = "UPDATE pessoa SET dtnasc = ? WHERE cpf = ?";
                break;
            case 8:
                sql = "UPDATE funcionario SET cargo = ? WHERE id_funcionario = (SELECT id FROM pessoa WHERE cpf = ?)";
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            System.out.println("Novo valor: ");
            String novoValor = input.nextLine();
            stmt.setString(1, novoValor);
            stmt.setString(2, cpf);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("-> Registro atualizado com sucesso! <-");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String cpf) {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = (SELECT id FROM pessoa WHERE cpf = ?)";
        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("-> Registro excluído com sucesso! <-");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}