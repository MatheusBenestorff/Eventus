package model;

public class Assento {
    private int id;
    private int linha;
    private int coluna;
    private boolean ocupado;
    private int idSessao;

   
    public Assento() {
    }

    public Assento(int id, int linha, int coluna, boolean ocupado, int idSessao) {
        this.id = id;
        this.linha = linha;
        this.coluna = coluna;
        this.ocupado = ocupado;
        this.idSessao = idSessao;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }
}
