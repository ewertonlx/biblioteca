package classes;

public class Funcionario extends Pessoa {
    private String cargo;

    public Funcionario(String cargo, String cpf) {
        super(cpf);
        this.cargo = cargo;
    }
    public Funcionario(String cargo, String cpf, String nome, String rua, int numeroCasa, String cidade, String estado, String telefones, String dt_nasc) {
        super(cpf, nome, rua, numeroCasa, cidade, estado, telefones, dt_nasc);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
