package interfaces;

public interface Crud<T> {
    public void insert(T p);
    public String get(String cpf);
    public void update(String cpf, int opcao);
    public void delete(String cpf);
} 
