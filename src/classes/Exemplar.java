package classes;

public class Exemplar extends Livro {
    private String edicao;
    private String estado_conservacao;
    private String disponibilidade;

    public Exemplar() {
    }

    public Exemplar(String titulo, String autor, String ano_publi, String identificador, String edicao, String estado_conservacao, String disponibilidade) {
        super(titulo, autor, ano_publi, identificador);
        this.edicao = edicao;
        this.estado_conservacao = estado_conservacao;
        this.disponibilidade = disponibilidade;
    }

    public Exemplar(String identificador, String edicao, String estado_conservacao, String disponibilidade) {
        super(identificador);
        this.edicao = edicao;
        this.estado_conservacao = estado_conservacao;
        this.disponibilidade = disponibilidade;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getEstado_conservacao() {
        return estado_conservacao;
    }

    public void setEstado_conservacao(String estado_conservacao) {
        this.estado_conservacao = estado_conservacao;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
