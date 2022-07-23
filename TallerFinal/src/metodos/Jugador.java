/*
AUTORIA: Constantino Byelov Serdiuk
https://www.youtube.com/watch?v=1JfyRZ38iCc
FECHA: 16.06.2021

    Clase jugador
 */
package metodos;

/**
 *
 * @author cbyelov
 */

public class Jugador {
    
    //declaramos los atributos columna y filas
    private int col;
    private int row;

    //constructor vacio
    public Jugador() {  
    }

    //getter de columna
    public int getCol() {
        return col;
    }
    //setter de columna
    public void setCol(int col) {
        this.col = col;
    }
    //getter de fila
    public int getRow() {
        return row;
    }
    //setter de fila
    public void setRow(int row) {
        this.row = row;
    }
   
}
