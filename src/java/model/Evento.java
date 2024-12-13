package model;

public class Evento {
    private int id;
    private String nome;
    private String descricao;
    private String tipo;  
    private boolean isStandUp;
    private boolean isFestival;
    private boolean isExposicao;
    private String cartaz;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
     
        setTipoEvento(tipo);
    }

    public boolean isStandUp() {
        return isStandUp;
    }

    public void setStandUp(boolean isStandUp) {
        this.isStandUp = isStandUp;
    }

    public boolean isFestival() {
        return isFestival;
    }

    public void setFestival(boolean isFestival) {
        this.isFestival = isFestival;
    }

    public boolean isExposicao() {
        return isExposicao;
    }

    public void setExposicao(boolean isExposicao) {
        this.isExposicao = isExposicao;
    }

    public String getCartaz() {
        return cartaz;
    }

    public void setCartaz(String imagem) {
        this.cartaz = imagem;
    }


    private void setTipoEvento(String tipo) {
        if ("Stand-up".equalsIgnoreCase(tipo)) {
            this.isStandUp = true;
            this.isFestival = false;
            this.isExposicao = false;
        } else if ("Festival".equalsIgnoreCase(tipo)) {
            this.isStandUp = false;
            this.isFestival = true;
            this.isExposicao = false;
        } else if ("Exposição".equalsIgnoreCase(tipo)) {
            this.isStandUp = false;
            this.isFestival = false;
            this.isExposicao = true;
        } else {
            this.isStandUp = false;
            this.isFestival = false;
            this.isExposicao = false;
        }
    }
}
