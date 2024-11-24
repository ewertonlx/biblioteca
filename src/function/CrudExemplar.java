package function;
import interfaces.Crud;
import sql.Conexao;
import classes.Exemplar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class CrudExemplar implements Crud<Exemplar> {
    Scanner input = new Scanner(System.in);
    @Override
    public void insert(Exemplar e) {
        String sqlInsertExemplar = "INSERT INTO exemplar (id_exemplar, edicao, estado_conservacao, disponibilidade) VALUES (?, ?, ?, ?)";
        String sqlSelectLivro = "SELECT id_livro FROM livro WHERE identificador = ?";
        try (Connection connection = Conexao.getConexao();
            PreparedStatement psInsertExemplar = connection.prepareStatement(sqlInsertExemplar);
            PreparedStatement psSelectLivro = connection.prepareStatement(sqlSelectLivro)) {

            psSelectLivro.setString(1, e.getIdentificador());
            ResultSet rs = psSelectLivro.executeQuery();
            if (rs.next()) {
                int livroId = rs.getInt("id_livro");
                psInsertExemplar.setInt(1, livroId);
                psInsertExemplar.setString(2, e.getEdicao());
                psInsertExemplar.setString(3, e.getEstado_conservacao());
                psInsertExemplar.setString(4, e.getDisponibilidade());
                psInsertExemplar.executeUpdate();

                System.out.println("-> Exemplar inserido com sucesso! <-");
            } else {
                System.err.println("!!!!!!!!! Erro: O livro com o autor " + e.getAutor() + " não foi encontrado. !!!!!!!!!!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("!!!!!!!! Erro ao inserir exemplar: " + ex.getMessage());
        }
    }

    @Override
    public String get(String idExemplar) {
        String sql = "SELECT e.id_exemplar, e.edicao, e.estado_conservacao, e.disponibilidade, l.titulo, l.autor, l.ano_publicacao FROM exemplar e JOIN livro l ON e.id_exemplar = l.id_livro WHERE e.id_exemplar = ?";
        String resultado = "";

        try (Connection connection = Conexao.getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, idExemplar);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                resultado = "ID: " + rs.getInt("id_exemplar") + "\n" +
                            "Edição: " + rs.getString("edicao") + "\n" +
                            "Estado de Conservação: " + rs.getString("estado_conservacao") + "\n" +
                            "Disponibilidade: " + rs.getString("disponibilidade") + "\n"
                            + "Título: " + rs.getString("titulo") + "\n"
                            + "Autor: " + rs.getString("autor") + "\n"
                            + "Ano de Publicação: " + rs.getInt("ano_publicacao") + "\n";

            } else {
                System.err.println("!!!!!!!!! Erro: Exemplar não encontrado. !!!!!!!!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao buscar exemplar: " + e.getMessage());
        }

        return resultado;
    }

    @Override
    public void update(String idExemplar, int opcao) {
        String sql = "";
        switch (opcao) {
            case 1:
                sql = "UPDATE exemplar SET edicao = ? WHERE id_exemplar = ?";
                break;
            case 2:
                sql = "UPDATE exemplar SET estado_conservacao = ? WHERE id_exemplar = ?";
                break;
            case 3:
                sql = "UPDATE exemplar SET disponibilidade = ? WHERE id_exemplar = ?";
                break;
        }

        try (Connection connection = Conexao.getConexao();
            PreparedStatement psUpdateExemplar = connection.prepareStatement(sql)) {

            System.out.println("Novo valor: ");
            String novoValor = input.nextLine();

            psUpdateExemplar.setString(1, novoValor);
            psUpdateExemplar.setString(2, idExemplar);
            psUpdateExemplar.executeUpdate();

            System.out.println("-> Exemplar atualizado com sucesso! <-");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao atualizar exemplar: " + e.getMessage());
        }
    }
    @Override
    public void delete(String idExemplar) {
        String sql = "DELETE FROM exemplar WHERE id_exemplar = ?";
        try (Connection connection = Conexao.getConexao();
            PreparedStatement psDeleteExemplar = connection.prepareStatement(sql)) {

            psDeleteExemplar.setString(1, idExemplar);
            psDeleteExemplar.executeUpdate();

            System.out.println("-> Exemplar excluído com sucesso! <-");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("!!!!!!!! Erro ao excluir exemplar: " + e.getMessage());
        }
    }
}