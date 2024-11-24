package function;
import interfaces.Crud;
import sql.Conexao;
import classes.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class CrudEmprestimo implements Crud<Emprestimo> {
    Scanner input = new Scanner(System.in);
    @Override
    public void insert(Emprestimo e) {
        String sqlInsertEmprestimo = "INSERT INTO emprestimo (id_exemplar, id_pessoa, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        String sqlSelectExemplar = "SELECT id_livro FROM livro WHERE identificador = ?";
        String sqlSelectPessoa = "SELECT id FROM pessoa WHERE cpf = ?";
        try (Connection connection = Conexao.getConexao();
            PreparedStatement psInsertEmprestimo = connection.prepareStatement(sqlInsertEmprestimo);
            PreparedStatement psSelectExemplar = connection.prepareStatement(sqlSelectExemplar);
            PreparedStatement psSelectPessoa = connection.prepareStatement(sqlSelectPessoa)) {

            psSelectExemplar.setString(1, e.getIdentificador());
            ResultSet rsExemplar = psSelectExemplar.executeQuery();
            if (rsExemplar.next()) {
                int exemplarId = rsExemplar.getInt("id_livro");
                psSelectPessoa.setString(1, e.getCpf());
                ResultSet rsPessoa = psSelectPessoa.executeQuery();
                if (rsPessoa.next()) {
                    int pessoaId = rsPessoa.getInt("id");
                    psInsertEmprestimo.setInt(1, exemplarId);
                    psInsertEmprestimo.setInt(2, pessoaId);
                    psInsertEmprestimo.setString(3, e.getData_emprestimo());
                    psInsertEmprestimo.setString(4, e.getData_devolucao());
                    psInsertEmprestimo.executeUpdate();

                    System.out.println("-> Empréstimo inserido com sucesso! <-");
                } else {
                    System.err.println("!!!!!!!!! Erro: A pessoa com o cpf " + e.getCpf() + " não foi encontrada. !!!!!!!!!!");
                }
            } else {
                System.err.println("!!!!!!!!! Erro: O exemplar com o identificador " + e.getIdentificador() + " não foi encontrado. !!!!!!!!!!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("!!!!!!!! Erro ao inserir empréstimo: " + ex.getMessage());
        }
    }

    @Override
    public String get(String idEmprestimo) {
        String sql = "SELECT e.id_emprestimo, e.data_emprestimo, e.data_devolucao, l.titulo, l.autor, l.ano_publicacao, p.nome, p.cpf FROM emprestimo e JOIN exemplar ex ON e.id_exemplar = ex.id_exemplar JOIN livro l ON ex.id_exemplar = l.id_livro JOIN pessoa p ON e.id_pessoa = p.id WHERE e.id_emprestimo = ?";
        String resultado = "";

        try (Connection connection = Conexao.getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, idEmprestimo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                resultado = "ID: " + rs.getInt("id_emprestimo") + "\n" +
                            "Data de Empréstimo: " + rs.getString("data_emprestimo") + "\n" +
                            "Data de Devolução: " + rs.getString("data_devolucao") + "\n" +
                            "Título: " + rs.getString("titulo") + "\n" +
                            "Autor: " + rs.getString("autor") + "\n" +
                            "Ano de publicação: " + rs.getString("ano_publicacao") + "\n" +
                            "Nome: " + rs.getString("nome") + "\n" +
                            "CPF: " + rs.getString("cpf") + "\n";
            } else {
                System.err.println("!!!!!!!!! Erro: Empréstimo não encontrado. !!!!!!!!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao buscar empréstimo: " + e.getMessage());
        }

        return resultado;
    }

    @Override
    public void update(String idEmprestimo, int opcao) {
        String sql = "";
        switch (opcao) {
            case 1:
                sql = "UPDATE emprestimo SET data_emprestimo = ? WHERE id_emprestimo = ?";
                break;
            case 2:
                sql = "UPDATE emprestimo SET data_devolucao = ? WHERE id_emprestimo = ?";
                break;
        }

        try (Connection connection = Conexao.getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            System.out.println("Digite o novo valor: ");
            String novoValor = input.nextLine();

            stmt.setString(1, novoValor);
            stmt.setString(2, idEmprestimo);

            stmt.executeUpdate();

            System.out.println("-> Empréstimo atualizado com sucesso! <-");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }
    @Override
    public void delete(String idEmprestimo) {
        String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";

        try (Connection connection = Conexao.getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, idEmprestimo);
            stmt.executeUpdate();

            System.out.println("-> Empréstimo excluído com sucesso! <-");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao excluir empréstimo: " + e.getMessage());
        }
    }
}