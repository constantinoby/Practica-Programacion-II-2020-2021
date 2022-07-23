/*

AUTORIA: Constantino Byelov Serdiuk
https://www.youtube.com/watch?v=1JfyRZ38iCc
FECHA: 16.06.2021

Clase Tauler
 */
package metodos;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.Random;

public class Tauler extends JPanel {

    //Filas/Rows del laberinto
    private int DIMENSIO;
    //Columnas del laberinto
    private int DIMENSIO2;
    //Fila de la salida
    private int rowSalida;
    //Columna de la salida
    private int colSalida;

    //declaramos
    private Casella t[][];
    Jugador j;

    //constructor vacio
    public Tauler() {
    }

    //metodo setter del dimensio
    public void setDim(int DIMENSIO) {
        this.DIMENSIO = DIMENSIO;
    }

    //metodo setter del dimensio2
    public void setDim2(int DIMENSIO2) {
        this.DIMENSIO2 = DIMENSIO2;
    }

    //metodo que hace la dimension del "tablero"
    public void setVarios() {
        setLayout(new GridLayout(DIMENSIO, DIMENSIO2));
        t = new Casella[DIMENSIO][DIMENSIO2];
        j = new Jugador();

    }

    //metodo que hace que un jugador se cambie de posicion
    public void randJug() {
        //declaramos
        int r1;
        int r2;
        //metodo random que va ha generar dos numeros random con parametro DIMENSIO y DIMENSIO2
        Random r = new Random();
        r1 = r.nextInt(DIMENSIO);
        r2 = r.nextInt(DIMENSIO2);

        //si los numeros coninciden con la fila y columna reiniciamos el metodo
        if (r1 == rowSalida && r2 == colSalida) {
            randJug();
        }

        //metodo setter de fila y columna del jugador 
        j.setRow(r1);
        j.setCol(r2);

        //hacemos que el jugador se vea en la casilla, que seria los numeros generados
        t[r1][r2].setPlayer(true);

    }

    //metodo setcasilla
    public void setCasilla(int i, int j, String c) {
        Casella casella = new Casella(c);
        t[i][j] = casella;
        add(casella);
    }

    //metodo que setea la posicion de la casilla de salida
    public void setSalida(int i, int j) {
        t[i][j].setExit();
        rowSalida = i;
        colSalida = j;
        randJug();
    }

    //metodo que actualiza la posicion del jugador 
    public void actualizarPos() {
        j.getCol();
        j.getRow();
    }

    public void jugadorMovido(char direccion) {
        //getter de la posicion actual del jugador
        int rowJugador = j.getRow();
        int colJugador = j.getCol();
        switch (direccion) {
            case 'a':
                //izquierda
                //si el jugador no esta en la columna 0 ejecutamos toda la logica de movimiento 
                if (colJugador != 0) {
                    //miramos los bordes de la casilla actual
                    String bordesCasillaJugador = t[rowJugador][colJugador]
                            .getBordes();
                    //miramos los bordes de la casilla a la cual queremos movernos
                    String bordesSiguienteCasilla = t[rowJugador][colJugador - 1]
                            .getBordes();
                    //si la casilla actual no tiene borde por la izquierda y la
                    //casilla por la izquierda no tiene borde por la derecha realizamos
                    //el movimiento
                    if (bordesCasillaJugador.charAt(3) == '0'
                            && bordesSiguienteCasilla.charAt(1) == '0') {
                        //Logica de movimiento jugador
                        int colJugadorActualizada = colJugador - 1;
                        j.setCol(colJugadorActualizada);

                        //quitamos al jugador de la casilla actual 
                        //y lo pintamos en la siguiente casilla
                        t[rowJugador][colJugador].setPlayer(false);
                        t[rowJugador][colJugadorActualizada].setPlayer(true);
                    }
                }
                break;

            case 'd':

                //derecha 
                //si el jugador no esta en la columna DIMENSION2-1 ejecutamos toda la logica de movimiento 
                if (colJugador != DIMENSIO2 - 1) {
                    //miramos los bordes de la casilla actual
                    String bordesCasillaJugador = t[rowJugador][colJugador]
                            .getBordes();
                    //miramos los bordes de la casilla a la cual queremos movernos
                    String bordesSiguienteCasilla = t[rowJugador][colJugador + 1]
                            .getBordes();

                    //si la casilla actual no tiene borde por la derecha y la
                    //casilla por la derecha no tiene borde por la izquierda realizamos
                    //el movimiento
                    if (bordesCasillaJugador.charAt(1) == '0'
                            && bordesSiguienteCasilla.charAt(3) == '0') {
                        //Logica de movimiento jugador
                        int colJugadorActualizada = colJugador + 1;
                        j.setCol(colJugadorActualizada);

                        //quitamos al jugador de la casilla actual 
                        //y lo pintamos en la siguiente casilla
                        t[rowJugador][colJugador].setPlayer(false);
                        t[rowJugador][colJugadorActualizada].setPlayer(true);
                    }
                }
                break;

            case 'w':

                //arriba 
                //si el jugador no esta en la fila 0 ejecutamos toda la logica de movimiento 
                if (rowJugador != 0) {
                    //miramos los bordes de la casilla actual
                    String bordesCasillaJugador = t[rowJugador][colJugador]
                            .getBordes();
                    //miramos los bordes de la casilla a la cual queremos movernos
                    String bordesSiguienteCasilla = t[rowJugador - 1][colJugador]
                            .getBordes();

                    //si la casilla actual no tiene borde por arriba y la
                    //casilla de arriba no tiene borde por abajo realizamos
                    //el movimiento
                    if (bordesCasillaJugador.charAt(0) == '0'
                            && bordesSiguienteCasilla.charAt(2) == '0') {
                        //Logica de movimiento jugador
                        int rowJugadorActualizada = rowJugador - 1;
                        j.setRow(rowJugadorActualizada);
                        //quitamos al jugador de la casilla actual 
                        //y lo pintamos en la siguiente casilla
                        t[rowJugador][colJugador].setPlayer(false);
                        t[rowJugadorActualizada][colJugador].setPlayer(true);
                    }
                }
                break;

            case 's':
                //abajo
                //si el jugador no esta en la columna DIMENSION-1 ejecutamos toda la logica de movimiento 
                if (rowJugador != DIMENSIO - 1) {
                    //miramos los bordes de la casilla actual
                    String bordesCasillaJugador = t[rowJugador][colJugador]
                            .getBordes();
                    //miramos los bordes de la casilla a la cual queremos movernos
                    String bordesSiguienteCasilla = t[rowJugador + 1][colJugador]
                            .getBordes();

                    //si la casilla actual no tiene borde por abajo y la
                    //casilla de abajo no tiene borde por arriba realizamos
                    //el movimiento
                    if (bordesCasillaJugador.charAt(2) == '0'
                            && bordesSiguienteCasilla.charAt(0) == '0') {
                        //Logica de movimiento jugador
                        int rowJugadorActualizada = rowJugador + 1;
                        j.setRow(rowJugadorActualizada);
                        //quitamos al jugador de la casilla actual 
                        //y lo pintamos en la siguiente casilla
                        t[rowJugador][colJugador].setPlayer(false);
                        t[rowJugadorActualizada][colJugador].setPlayer(true);
                    }
                }
                break;
        }
        //actualizamos las filas y columnas 
        rowJugador = j.getRow();
        colJugador = j.getCol();
        //si la fila y la columna del jugador es igual a la columna y la fila 
        //enseñamos un texto "Has ganado", borramos al jugador y metemos otra
        //posicion aleatoria
        if (rowJugador == rowSalida && colJugador == colSalida) {
            JOptionPane.showMessageDialog(null, "Has ganado");
            t[rowJugador][colJugador].setPlayer(false);
            randJug();

        }
    }
}
