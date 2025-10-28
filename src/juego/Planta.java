package juego;

import java.awt.Color;

import entorno.Entorno;

public class Planta {
	private double x;
	private int y;
	private int ancho;
	private int alto;

	
	public Planta(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
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
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.PINK);
	}
	
	public BolaDeFuego disparar(){
		return new BolaDeFuego(this.x, this.y, 10, 10, 3);
	}
	
	
}
