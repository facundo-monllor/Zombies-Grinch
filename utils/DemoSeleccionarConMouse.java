package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class DemoSeleccionarConMouse extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	Estrella estrella;
	Estrella estrellas[];
	
	DemoSeleccionarConMouse()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Seleccionar con mouse", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		
		this.estrella =new Estrella(100,200,"Estrella"+(1)+".png");
		
		this.estrellas= new Estrella[6];
		
		for (int i=0; i<estrellas.length; i++) {
				
			this.estrellas[i]= new Estrella(100+90*i,300+30*i,"Estrella"+(i+1)+".png");
		}
		
		
		
		
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...



		for (int i=0;i<estrellas.length;i++) {

			if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && 
					this.cursorDentro(estrellas[i], entorno.mouseX(),entorno.mouseY(),20))
			{
				estrellas[i].seleccionada=true;;
			}
		}

		for (int i=0;i<estrellas.length;i++) {
			if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) &&
					!this.cursorDentro(estrellas[i], entorno.mouseX(),entorno.mouseY(),20)){
				estrellas[i].seleccionada=false;
			}
		}


		for (int i=0;i<estrellas.length;i++) {
			if(entorno.estaPresionado(entorno.BOTON_IZQUIERDO) &&
					 estrellas[i].seleccionada)
			{
				estrellas[i].situarse(entorno.mouseX(), entorno.mouseY());
			}
		}

		for (int i=0;i<estrellas.length;i++) {
			if(entorno.estaPresionado(entorno.BOTON_DERECHO) &&
					estrellas[i].seleccionada){
				estrellas[i].apuntar(entorno.mouseX(), entorno.mouseY());
			}
		} 
				

		for (int i=0; i<estrellas.length; i++) {
			estrellas[i].dibujarse(entorno);
			
		}



		Color textoColor = new Color (213, 231, 247);
		entorno.cambiarFont("Tahoma", 22, textoColor, entorno.NEGRITA);
		entorno.escribirTexto("Seleccionar una estrella con el boton izquierdo", 50, 50);
		entorno.escribirTexto("La estrella seleccionada se puede mover arrastrando con el mouse ", 5, 75);
		entorno.escribirTexto("La estrella seleccionada se puede rotar apretando coboton derecho ", 5, 100);
	}

	public boolean cursorDentro(Estrella a, int mx,int my, int d) {

		return( (a.x-mx)* (a.x-mx)+(a.y-my)*(a.y-my))<d*d;
	}



	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		DemoSeleccionarConMouse juego = new DemoSeleccionarConMouse();
	}
}
