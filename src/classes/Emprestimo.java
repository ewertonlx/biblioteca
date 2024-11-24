package classes;

public class Emprestimo {
    private String data_emprestimo;
    private String data_devolucao;
    String identificador;
    String cpf;

    public Emprestimo() {
    }

    public Emprestimo(String cpf, String exemplar, String data_emprestimo, String data_devolucao) {
        this.cpf = cpf;
        this.identificador = exemplar;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;

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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}