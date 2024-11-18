package function;
import interfaces.Crud;
import sql.Conexao;
import classes.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class CrudUsuario implements Crud<Pessoa> {
    Scanner input = new Scanner(System.in);
    @Override
    public void insert(Pessoa p) {
        String sql = "INSERT INTO pessoa (cpf, nome, rua, numero, cidade, estado, dtnasc) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
        PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getRua());
            stmt.setInt(4, p.getNumeroCasa());
            stmt.setString(5, p.getCidade());
            stmt.setString(6, p.getEstado());
            stmt.setString(7, p.getDt_nasc());
            
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int pessoaId = generatedKeys.getInt(1);
                    String sqlTelefone = "INSERT INTO telefones (pessoa_id, numero) VALUES (?, ?)";
                    PreparedStatement stmtTelefone = Conexao.getConexao().prepareStatement(sqlTelefone);
                    
                    for (String telefone : p.getTelefones()) {
                        stmtTelefone.setInt(1, pessoaId); 
                        stmtTelefone.setString(2, telefone);
                        stmtTelefone.executeUpdate();
                    }
                }
            }
            stmt.close();
            System.out.println("-> Registro inserido com sucesso! <-");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get(String cpf) {
    String sql = "SELECT * FROM pessoa WHERE cpf = ?";
    String resultado = "";
    try {
        PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
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
                        "Data de Nascimento: " + rs.getString("dtnasc");
        } else {
            resultado = "Nenhuma pessoa encontrada com o cpf: " + cpf;
        }
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return resultado;
}

    @Override
    public void update(String cpf, int opcao) {
        String sql = "";
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
        String sql = "DELETE FROM pessoa WHERE cpf = ?";
        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("-> Registro deletado com sucesso! <-");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}