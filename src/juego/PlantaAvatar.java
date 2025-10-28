package juego;

import java.awt.*;

import entorno.Entorno;
import entorno.Herramientas;

public class PlantaAvatar {
	private double x;
	private int y;
	private int ancho;
	private int alto;
    private double angulo;
    private double escala;
    private boolean estaDisponible;
	
    private Image imagenFondo;
	
	
	public PlantaAvatar(int x, int y, int ancho, int alto, String imagenFondo) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.angulo = 0;
		this.escala = 1;
		this.estaDisponible = true;
        this.imagenFondo = Herramientas.cargarImagen(imagenFondo);
	}

	public boolean getEstaDisponible() {
		return this.estaDisponible;
	}

	public void dibujar(Entorno entorno){
		entorno.dibujarImagen(this.imagenFondo, this.x, this.y, this.angulo, this.escala);
	}

}
