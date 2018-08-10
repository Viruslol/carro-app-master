package cl.frabarz.carro;

/**
 * Created by Viruslol on 09/08/2018.
 */

public class Disco {

    private String titulo;
    private String anyo;
    private String descripcion;
    private String genero;
    private String consola;
    private String pegi;
    private String precio;


    public Disco(){

    }

    public Disco(String titulo, String anyo, String descripcion, String genero, String consola, String pegi, String precio) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.descripcion = descripcion;
        this.genero = genero;
        this.consola = consola;
        this.pegi = pegi;
        this.precio = precio;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public String getPegi() {
        return pegi;
    }

    public void setPegi(String pegi) {
        this.pegi = pegi;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }





    @Override
    public String toString() {
        return "Disco{" +
                "titulo='" + titulo + '\'' +
                ", anyo='" + anyo + '\'' +
                '}';
    }
}

