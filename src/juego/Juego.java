package juego;


import java.awt.*;
import java.util.Arrays;

import entorno.Entorno;
import entorno.InterfaceJuego;
import entorno.Herramientas;

public class Juego extends InterfaceJuego
{
    // El objeto Entorno que controla el tiempo y otros
    private Entorno entorno;

    // Variables
    Zombie[] zombies;
    Planta planta;
    BolaDeFuego bolaDeFuego;
    Casilla[] casillasTablero;
	PlantaAvatar[] avataresPlantas;
	ZombieAvatar[] avataresZombies;
    private int ContadorPlantas = 0;
    private int ZombiesEliminados = 0;
    private int ZombiesRestantes = 100;
    private int TiempoDeJuego = 0;
	private Image backgroundImage = Herramientas.cargarImagen("Frontyard.jpg");

    Juego()
    {
        // Inicializa el objeto entorno
        this.entorno = new Entorno(this, "La Invasión de los Zombies Grinch", 1260, 900);

		// Creamos Avatares de plantas
		this.avataresPlantas = new PlantaAvatar[100];
		for(int i = 0; i < 2; i++){
            boolean isRoseBlade = i == 0;
            this.avataresPlantas[i] = new PlantaAvatar(isRoseBlade ? "RoseBlade" : "WallNut", 60 + 120*i, 60, 100, 100);
            ContadorPlantas++;
        }

        // Creamos Avatares de zombies
		this.avataresZombies = new ZombieAvatar[1];
		for(int i = 0; i < 1; i++){
            this.avataresZombies[i] = new ZombieAvatar(1200, 60, 100, 100);
        }

        // Creamos zombies
        this.zombies = new Zombie[5];
        for(int i = 0; i < this.zombies.length; i++){
            this.zombies[i] = new Zombie(1360, 60 + 120*i, 60, 60, Math.random()+0.1);
        }

        // Creamos casillas
        this.casillasTablero = new Casilla[45];
        int contador = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                boolean imparFila = i%2 == 1;
                boolean imparColumna = j%2 == 1;
                boolean isRegalo = j == 0;
                this.casillasTablero[contador] = new Casilla(contador, 220 + 100*j, 270 + 100*i, 100, 100, false, isRegalo, "casilla"+(imparFila ? "clara" : "oscura")+(imparColumna ? "2" : "1")+".png");
                contador++;
            }
        }

        // Creamos plantas
        this.planta = new Planta(50, 300, 40, 60);

        // Iniciamos el juego
        this.entorno.iniciar();

    }

    public void tick()
    {
        // Procesamiento de un instante de tiempo
        this.planta.dibujar(entorno);

		//Tiempo
		int sec= entorno.tiempo()/1000;
		TiempoDeJuego= sec;

		//Dibujamos imagen de fondo (imagen, x, y, angulo, escala)
        this.entorno.dibujarImagen(backgroundImage, 840,530, 0, 1.25);

        // Dibujamos seccion superior (x, y, ancho, alto, angulo, color)
        this.entorno.dibujarRectangulo(0,0,3200,350,0,Color.LIGHT_GRAY);

		// Seteamos y escribimos textos
		Color miColor = new Color (255, 204, 0);
        this.entorno.cambiarFont("Constantia", 18, miColor,entorno.NEGRITA);
        this.entorno.escribirTexto("ELIMINADOS: " + ZombiesEliminados , 600 , 30);
        this.entorno.escribirTexto("RESTANTES: " + ZombiesRestantes, 600 , 60);
        this.entorno.escribirTexto("TIEMPO DE JUEGO: " + TiempoDeJuego + " S.", 600 , 90);

		// Dibujamos casillas
        for(int i = 0; i < this.casillasTablero.length; i++){
            this.casillasTablero[i].dibujar(this.entorno);
        }

        // MOVIMIENTO
        for (int i=0; i < this.avataresPlantas.length; i++) {
            if(avataresPlantas[i] != null){
                if(entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && this.cursorDentro(avataresPlantas[i], entorno.mouseX(),entorno.mouseY(),20)){
                    System.out.print("entre" + avataresPlantas[i].imagen);
                    avataresPlantas[i].seleccionada=true;
                }
            }
		}
		for (int i=0; i < this.avataresPlantas.length; i++) {
            if(avataresPlantas[i] != null){
                if(entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO) && avataresPlantas[i].seleccionada){

                    boolean sobreCasilla = false;
                    for (Casilla casilla : casillasTablero) {
                    
                        if (cursorDentroDeCasilla(casilla, entorno.mouseX(), entorno.mouseY()) && !casilla.estaOcupada && !casilla.tieneRegalo) {
                            sobreCasilla = true;
                            avataresPlantas[i].x = casilla.x;
                            avataresPlantas[i].y = casilla.y;
                            avataresPlantas[i].lastX = casilla.x;
                            avataresPlantas[i].lastY = casilla.y;
                            casilla.estaOcupada = true;

                            if(avataresPlantas[i].estaEnJuego == false){
                                this.avataresPlantas[ContadorPlantas] = new PlantaAvatar(avataresPlantas[i].tipoPlanta == "RoseBlade" ? "RoseBlade" : "WallNut", avataresPlantas[i].tipoPlanta == "RoseBlade" ? 60 : 180, 60, 100, 100);
                                ContadorPlantas++;
                            }
                            
                            // Busco la casilla anterior y la establezco en desocupada
                            for (Casilla casillaAnterior : casillasTablero) {
                                if(avataresPlantas[i].casillaId == casillaAnterior.id){
                                    casillaAnterior.estaOcupada = false;
                                }
                            }

                            avataresPlantas[i].estaEnJuego = true;
                            avataresPlantas[i].casillaId = casilla.id;
                            break;
                        }
                    }

                    if (!sobreCasilla && !avataresPlantas[i].estaEnJuego) {
                        // Vuelve al inicio del avatar
                        avataresPlantas[i].x = avataresPlantas[i].initialX;
                        avataresPlantas[i].y = avataresPlantas[i].initialY;
                    }
                    if (!sobreCasilla && avataresPlantas[i].estaEnJuego) {
                        // Vuelve a la ultima casilla
                        avataresPlantas[i].x = avataresPlantas[i].lastX;
                        avataresPlantas[i].y = avataresPlantas[i].lastY;
                    }

                    avataresPlantas[i].seleccionada = false;
                }
            }
		}
        

		for (int i=0; i < this.avataresPlantas.length; i++) {
            if(avataresPlantas[i] != null){
                if(entorno.estaPresionado(entorno.BOTON_IZQUIERDO) && avataresPlantas[i].seleccionada && avataresPlantas[i].estaDisponible){
                    avataresPlantas[i].moverse(entorno.mouseX(), entorno.mouseY());
                }
            }
		}

        for (int i=0; i < this.avataresPlantas.length; i++) {
            if(avataresPlantas[i] != null){
                avataresPlantas[i].counterTime += 1;
                System.out.print("avataresPlantas[i].counterTime" + avataresPlantas[i].counterTime);
                System.out.print("avataresPlantas[i].estaDisponible" + avataresPlantas[i].estaDisponible);
                if(avataresPlantas[i].counterTime > 200 && !avataresPlantas[i].estaDisponible){
                    avataresPlantas[i].estaDisponible = true;
                }
            }
		}


		// Dibujamos avatares de plantas
        for(int i = 0; i < this.avataresPlantas.length; i++){
            if(avataresPlantas[i] != null){
                this.avataresPlantas[i].dibujar(this.entorno);
            }
        }

        // Dibujamos avatares de zombies
        for(int i = 0; i < this.avataresZombies.length; i++){
            this.avataresZombies[i].dibujar(this.entorno);
        }

		// Dibujamos y movemos zombies
        for(int i = 0; i < this.zombies.length; i++){
            this.zombies[i].mover();
            this.zombies[i].dibujar(this.entorno);
        }

        if(this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)){
            this.bolaDeFuego = this.planta.disparar();
        }

        if(this.bolaDeFuego != null) {
            this.bolaDeFuego.dibujar(entorno);
            this.bolaDeFuego.mover();
        }

    }


    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        Juego juego = new Juego();
    }


    // Funciones extras
    public boolean cursorDentro(PlantaAvatar a, int mx,int my, int d) {
		return( (a.x-mx)* (a.x-mx)+(a.y-my)*(a.y-my))<d*d;
	}

    public boolean cursorDentroDeCasilla(Casilla casilla, int mx, int my) {
        return (mx > casilla.x - casilla.ancho/2 && mx < casilla.x + casilla.ancho/2 && my > casilla.y - casilla.alto/2 && my < casilla.y + casilla.alto/2);
    }


}
