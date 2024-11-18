package classes;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String ano_publi;

    public Livro() {
    }
    public Livro(int id, String titulo, String autor, String ano_publi) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano_publi = ano_publi;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getAno_publi() {
        return ano_publi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAno_publi(String ano_publi) {
        this.ano_publi = ano_publi;
    }
}