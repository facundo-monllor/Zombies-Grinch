package juego;

import java.awt.Color;

import entorno.Entorno;

public class BolaDeFuego {
	private double x;
	private int y;
	private int ancho;
	private int alto;
	private double velocidad;
	
	public BolaDeFuego(double x, int y, int ancho, int alto, double velocidad) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;
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
	
	public void dibujar(Entorno entorno){
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.RED);
	}
	
	public void mover(){
		this.x += this.velocidad;
	}
}
