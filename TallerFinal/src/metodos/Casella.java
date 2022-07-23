/*
AUTORIA: Constantino Byelov Serdiuk
https://www.youtube.com/watch?v=1JfyRZ38iCc
FECHA: 16.06.2021

 Classe casella, que dibuja la casilla y hace todo lo necesario para usarla.
 */
package metodos;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.border.MatteBorder;

class Casella extends JComponent {

//declaramos atributos
    private boolean esLaSalida;
    private boolean elJugadorEstaAqui;
    private String bordes;

    //constructor vacio
    public Casella() {

    }

    //constructor que recibe por parametro un string(grupo de 4 valores que define los bordes)
    public Casella(String n) {
        //declaramos
        bordes = n;
        esLaSalida = false;
        elJugadorEstaAqui = false;
        //ponemos el borde con el MatteBorder y le pasamos los valores del borde
        setBorder(new MatteBorder(Character.getNumericValue(n.charAt(0)),
                Character.getNumericValue(n.charAt(3)),
                Character.getNumericValue(n.charAt(2)),
                Character.getNumericValue(n.charAt(1)), Color.black));

    }

    //metodo que recibe los bordes
    public String getBordes() {
        return bordes;
    }

    //metodo paintComponent que dibuja las casillas del laberinto 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (esLaSalida) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.orange);
        }

        g.fillRect(0, 0, getWidth(), getHeight());

        //if que mira si el jugador esta en una casilla y dibuja el jugador
        if (elJugadorEstaAqui) {
            g.setColor(Color.black);
            g.fillOval(0, 0, getWidth() - 5, getHeight() - 5);
        }
    }

    //metodo que pone la casilla a true o false si esta en la salida
    public void setExit() {
        esLaSalida = true;
        repaint();
    }

    //metodo que mira si el jugador esta en la casilla si esta ahi pues lo pinta 
    //en dicha casilla
    public void setPlayer(boolean aqui) {
        elJugadorEstaAqui = aqui;
        repaint();
    }

}
