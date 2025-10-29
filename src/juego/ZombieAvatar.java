package juego;

import java.awt.*;

import entorno.Entorno;
import entorno.Herramientas;

public class ZombieAvatar {
    public double x;
    public double y;

	private int ancho;
	private int alto;
    private double angulo;
    private double escala;

    public Image imagen;
	
	
	public ZombieAvatar(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;

		this.ancho = ancho;
		this.alto = alto;
		this.angulo = 0;
		this.escala = 1;

		
        this.imagen = Herramientas.cargarImagen("zombiegrinch.jpg");
	}

	public void dibujar(Entorno entorno){
			entorno.dibujarImagen(this.imagen, this.x, this.y, this.angulo, this.escala);
	}


}
