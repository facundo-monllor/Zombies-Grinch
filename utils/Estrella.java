package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Estrella {

    // Variables de instancia
    double x;
    double y;
    double angulo;
    double escala;
    Image imagen;
    Image imagenFondo;
    boolean seleccionada;


    public Estrella(double x, double y, String archivo) {

        this.x = x;
        this.y = y;
        this.angulo=0;
        this.escala=0.5;
        this.seleccionada=false;


        imagen = Herramientas.cargarImagen(archivo);
        imagenFondo = Herramientas.cargarImagen("EstrellaF.png");

    }

    public void dibujarse(Entorno entorno)
    {

        if(seleccionada) {
            entorno.dibujarImagen(imagenFondo, this.x, this.y, this.angulo, this.escala*1.1);

        }

        entorno.dibujarImagen(imagen, this.x, this.y, this.angulo, this.escala);

    }


    public void girar(double modificador)
    {
        this.angulo = this.angulo + modificador;
    }

    public void apuntar(double x, double y)
    {
        this.angulo = Math.atan2(y-this.y, x-this.x);
    }

    public void situarse(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public double getAncho() {
        return imagen.getWidth(null)*this.escala;
    }
    public double getAlto() {
        return imagen.getHeight(null)*this.escala;
    }
    public double getTecho() {
        return this.y-this.getAlto()/2;

    }
    public double getPiso() {
        return this.y+this.getAlto()/2;
    }

    public double getIzquierda() {
        return this.x-this.getAncho()/2;

    }
    public double getDerecha() {
        return this.x+this.getAncho()/2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }




}
