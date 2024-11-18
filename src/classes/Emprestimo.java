package classes;

public class Emprestimo {
    private int id;
    private String data_emprestimo;
    private String data_devolucao;
    Livro livro;

    public int getId() {
        return id;
    } 
    public void setId(int id) {
        this.id = id;
    }

    public String getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(String data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public String getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(String data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}