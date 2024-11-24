package function;
import interfaces.Crud;
import sql.Conexao;
import classes.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class CrudLivro implements Crud<Livro> {
    Scanner input = new Scanner(System.in);
    @Override
    public void insert(Livro l) {
        String sqlInsertLivro = "INSERT INTO livro (titulo, autor, ano_publicacao, identificador) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexao.getConexao();
            PreparedStatement psInsertLivro = connection.prepareStatement(sqlInsertLivro)) {

            psInsertLivro.setString(1, l.getTitulo());
            psInsertLivro.setString(2, l.getAutor());
            psInsertLivro.setString(3, l.getAno_publi());
            psInsertLivro.setString(4, l.getIdentificador());
            psInsertLivro.executeUpdate();

            System.out.println("-> Livro inserido com sucesso! <-");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao inserir livro: " + e.getMessage());
        }
    }

    @Override
    public String get(String idLivro) {
        String sql = "SELECT * FROM livro WHERE id_livro = ?";
        String resultado = "";

        try (Connection connection = Conexao.getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, idLivro);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                resultado = "ID: " + rs.getInt("id_livro") + "\n" +
                            "Título: " + rs.getString("titulo") + "\n" +
                            "Autor: " + rs.getString("autor") + "\n" +
                            "Ano de publicação: " + rs.getString("ano_publicacao") + "\n" +
                            "Identificador: " + rs.getString("identificador") + "\n";
            } else {
                System.err.println("!!!!!!!!! Erro: Livro não encontrado. !!!!!!!!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao buscar livro: " + e.getMessage());
        }

        return resultado;
    }

    @Override
    public void update(String idLivro, int opcao) {
        String sql = "";
        switch (opcao) {
            case 1:
                sql = "UPDATE livro SET titulo = ? WHERE id_livro = ?";
                break;
            case 2:
                sql = "UPDATE livro SET autor = ? WHERE id_livro = ?";
                break;
            case 3:
                sql = "UPDATE livro SET ano_publicacao = ? WHERE id_livro = ?";
                break;
            case 4:
                sql = "UPDATE livro SET identificador = ? WHERE id_livro = ?";
                break;
            default:
                System.err.println("!!!!!!!!! Erro: Opção inválida. !!!!!!!!!!");
                return;
        }

        try (Connection connection = Conexao.getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            System.out.print("Digite o novo valor: ");
            String novoValor = input.nextLine();

            stmt.setString(1, novoValor);
            stmt.setString(2, idLivro);
            stmt.executeUpdate();

            System.out.println("-> Livro atualizado com sucesso! <-");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao atualizar livro: " + e.getMessage());
        }
    }
    @Override
    public void delete(String idLivro) {
        String sql = "DELETE FROM livro WHERE id_livro = ?";

        try (Connection connection = Conexao.getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, idLivro);
            stmt.executeUpdate();

            System.out.println("-> Livro deletado com sucesso! <-");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao deletar livro: " + e.getMessage());
        }
    }
}