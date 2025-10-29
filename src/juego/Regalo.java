package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Regalo {
	private double x;
	private int y;
	private int ancho;
	private int alto;
	private int hp;
	private double angulo;
    private double escala;
	private Image imagenFondo;

	
	public Regalo(double x, int y, int ancho, int alto, int hp, String imagenFondo) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.angulo = 0;
		this.escala = 1;
		this.hp = hp;
		this.imagenFondo = Herramientas.cargarImagen(imagenFondo);
	}

	public double getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void dibujar(Entorno entorno){
		entorno.dibujarImagen(this.imagenFondo, this.x, this.y, this.angulo, this.escala);
	}
	
}
