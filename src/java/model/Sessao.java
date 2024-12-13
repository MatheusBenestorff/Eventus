package model;

public class Sessao {
    private int id;
    private int idFilme;
    private int idCinema;
    private String horario;
    private String tipo;
    private String data; 
    private Cinema cinema; 
    private Filme filme; 

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // Getters e Setters para o cinema associado
    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    // Getters e Setters para o filme associado
    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "id=" + id +
                ", idFilme=" + idFilme +
                ", idCinema=" + idCinema +
                ", horario='" + horario + '\'' +
                ", tipo='" + tipo + '\'' +
                ", data='" + data + '\'' +
                ", cinema=" + (cinema != null ? cinema.getNome() : "null") +
                ", filme=" + (filme != null ? filme.getNome() : "null") +
                '}';
    }
}
