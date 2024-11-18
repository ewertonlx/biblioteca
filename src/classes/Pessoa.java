package classes;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private int id;
    private String cpf;
    private String nome;
    private String rua;
    private int numeroCasa;
    private String cidade;
    private String estado;
    private List<String> telefones;
    private String dt_nasc;

    public Pessoa() {
    }

    public Pessoa(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa(String cpf, String nome, String rua, int numeroCasa, String cidade, String estado, String telefones, String dt_nasc) {
        this.cpf = cpf;
        this.nome = nome;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.cidade = cidade;
        this.estado = estado;
        this.telefones = new ArrayList<String>();
        this.telefones.add(telefones);
        this.dt_nasc = dt_nasc;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getRua() {
        return rua;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public String getDt_nasc() {
        return dt_nasc;
    }

    public int getIdade() {
        return 18;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTelefones(String telefones) {
        this.telefones.add(telefones);
    }

    public void setDt_nasc(String dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public String toString() {
        return "Pessoa{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", telefones=" + telefones +
                ", dt_nasc='" + dt_nasc + '\'' +
                '}';
    }
}
