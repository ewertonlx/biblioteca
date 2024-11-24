package classes;

public class Livro {
    private String titulo;
    private String autor;
    private String ano_publi;
    private String identificador;

    public Livro() {
    }
    public Livro(String titulo, String autor, String ano_publi, String identificador) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano_publi = ano_publi;
        this.identificador = identificador;
    }

    public Livro(String identificador) {
        this.identificador = identificador;
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

    public String getIdentificador() {
        return identificador;
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

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}