package model;

import java.time.LocalDate;  
 
public class Filme {
    private int id;
    private String nome;
    private String cartaz;
    private String banner;
    private String descricao;
    private String diretor;
    private String genero;
    private int duracao;
    private boolean emCartaz;
    private boolean emBreve;
    private boolean preEstreia;
    private LocalDate dataEstreia; 

   
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

    public String getCartaz() {
        return cartaz;
    }

    public void setCartaz(String cartaz) {
        this.cartaz = cartaz;
    }
    
    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public boolean isEmCartaz() {
        return emCartaz;
    }

    public void setEmCartaz(boolean emCartaz) {
        this.emCartaz = emCartaz;
    }

    public boolean isEmBreve() {
        return emBreve;
    }

    public void setEmBreve(boolean emBreve) {
        this.emBreve = emBreve;
    }

    public boolean isPreEstreia() {
        return preEstreia;
    }

    public void setPreEstreia(boolean preEstreia) {
        this.preEstreia = preEstreia;
    }
}
