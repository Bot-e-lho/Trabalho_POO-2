package anotacoes;

public class Anotacoes {
    private Data data;
    private String titulo;
    private String conteudo;

    public Anotacoes(Data data, String titulo, String conteudo) {
        this.data = data;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public Data getData() {
        return data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + "\nData: " + data + "\nConteúdo:\n" + conteudo;
    }
}