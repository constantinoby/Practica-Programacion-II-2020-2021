
/*
AUTORIA: Constantino Byelov Serdiuk
https://www.youtube.com/watch?v=1JfyRZ38iCc
FECHA: 16.06.2021
*/

package tallerfinal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import metodos.*;

/**
 *
 * @author cbyelov
 * Programa que crea un laberinto el cual se puede jugar con las teclas W,A,S,D.
 *
 */
public class TallerFinal extends JFrame implements KeyListener {

    //declaramos variables necesarias
    Tauler tauler;
    JMenuBar menu;
    JMenu general;
    JMenuItem reiniciar;
    JMenuItem elegir;
    JMenuItem exit;
    JFileChooser fc;
    String fn;

    //declaramos la creacion de la ventana donde se va ha dibujar el laberinto
    public TallerFinal() throws IOException {
        //ponemos un nombre a la ventana 
        super("Laberinto");
        //ponemos un layout de casillas para poder juntar el laberinto con el menu
        this.setLayout(new BorderLayout());
        //ponemos un tamaño a la ventana
        setSize(new Dimension(600, 800));
        //si cerramos la ventana se cierra el programa
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //añadimos un keylistener que va ha mirar que teclas usamos
        this.addKeyListener(this);
        //declaramos un objeto tauler
        tauler = new Tauler();
        //asignamos a la variable String fn el primer laberinto para poder empezar
        //con un laberinto, si no se quiere usar ese laberinto podemos elegir otro con
        //la opcion "elegir" del menu
        fn = "maze1.txt";
        //llamamos al metodo creacionV, el cual va a crear el laberinto leyendolo
        //pasando el nombre del laberinto por parametro
        creacionV(fn);
        //pintamos el laberinto sobre la ventana principal
        repintado();

    }

    //metodo main principal
    public static void main(String[] args) throws IOException {
        TallerFinal tallerf = new TallerFinal();
        tallerf.setVisible(true);

    }

    //metodo repintando que agrega el laberinto a la ventana principal
    public void repintado() {

        this.getContentPane().removeAll();
        this.getContentPane().add(tauler, "Center");
        menus();
        this.revalidate();

    }

    //metodo menus que agrega el menu a la ventana principal
    public void menus() {
        //declaramos el menu y le damos nombres a cada pestaña
        menu = new JMenuBar();
        general = new JMenu("Fitxer");

        elegir = new JMenuItem("Elegir");
        //añadimos el actionlistener de elegir
        elegir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    //redireccionamos a dicho metodo del evento
                    elegirActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(TallerFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        exit = new JMenuItem("Salir");
        //añadimos el actionlistener de salir
        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                //redireccionamos a dicho metodo del evento
                salirActionPerformed(evt);
            }

        });

        reiniciar = new JMenuItem("Reiniciar Posición");
        //añadimos el actionlistener de reiniciar
        reiniciar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    //redireccionamos a dicho metodo del evento
                    reiniciarActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(TallerFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        //agregamos las opciones dentro de general para que se despliegue con el
        general.add(elegir);
        general.add(reiniciar);
        general.add(exit);

        //metemos el generald entro del menu principal
        menu.add(general);
        add(menu, "North");
    }

    //meto del evento salir
    private void salirActionPerformed(ActionEvent evt) {
        //cerramos el programa
        System.exit(0);
    }

    //metodo del evento reiniciar
    private void reiniciarActionPerformed(ActionEvent evt) throws IOException {
        //llamamos los metodos necesarios para reiniciar la posicion del jugador 
        creacionV(fn);
        repintado();
        menus();

    }

    //metodo del evento elegir
    private void elegirActionPerformed(ActionEvent evt) throws IOException {
        //declaramos el JFileChooser
        fc = new JFileChooser();
        //ponemos el parametro a solo directorios y archivos
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int resultado = fc.showOpenDialog(null);

        //obtenemos el nombre del archivo seleccionado
        fn = fc.getSelectedFile().getName();
        //nos dirijimos a el metodo creacionV del nombre obtenido con el JFileChooser
        creacionV(fn);
        //metodo repintado que pinta otra vez el tablero con la ficha cambiada de posicion
        repintado();

    }

    public void creacionV(String f) throws IOException {
        //declaramos el metodo de lectura y le pasamos por parametro el string 
        LecturaTxt lec = new LecturaTxt(f);
        //declaramos al jugador / ficha
        Jugador c;
        //con el objeto tauler leemos el archivo txt
        tauler = lec.leerTxt();
        lec.cierre();

    }

    //metodos del KeyListener
    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    //miramos que tecla ha sido presionada, obtenemos el char de la tecla y lo 
    //pasamos a nuestro metodo de movimiento
    @Override
    public void keyTyped(KeyEvent ke) {
        tauler.jugadorMovido(ke.getKeyChar());
    }

}
