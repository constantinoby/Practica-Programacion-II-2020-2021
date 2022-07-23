/*
AUTORIA: Constantino Byelov Serdiuk
https://www.youtube.com/watch?v=1JfyRZ38iCc
FECHA: 16.06.2021

Clase que va ha llevar la lectura y creación del tablero.
 */
package metodos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecturaTxt {

    //declramaos atributo
    private BufferedReader f = null;

    public LecturaTxt(String nom) throws FileNotFoundException {

        //leemos el txt 
        f = new BufferedReader(new FileReader(nom));
    }

    public void cierre() throws IOException {
        try {
            //ceramos el fichero
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(LecturaTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tauler leerTxt() throws IOException {

        //declaramos la apartura de un objeto tauler
        Tauler a = new Tauler();

        //leemos la primera linea que es el numero de filas 
        String lectura = f.readLine();
        int rows = Integer.parseInt(lectura);
        //asignamos la lectura a la primera dimension
        a.setDim(rows);

        String lec2 = f.readLine();
        int cols = Integer.parseInt(lec2);
        //leemos otra linea y la asignamos a las columnas
        a.setDim2(cols);
        a.setVarios();

        //bucle de toda la linea
        for (int i = 0; i < rows; i++) {
            String direcciones = f.readLine();

            //bucle de 4 donde asignaremos las direcciones a pintar
            for (int n = 0; n < cols; n++) {
                //miramos si es uno si lo es dibujamos la linea
                String datos = "";
                for (int p = 0; p < 4; p++) {
                    //leemos 4 numeros del array
                    int indice = n * 4 + p;
                    datos += direcciones.charAt(indice);
                }

                a.setCasilla(i, n, datos);
            }

        }

        //leemos la fila de salida
        String s1 = f.readLine();

        //leemos la columna de salida 
        String s2 = f.readLine();

        //metemos la fila de salida a una variable int mediante un integer
        int rowSalida = Integer.parseInt(s1);
        //metemos la columna de salida a una variable int mediante un integer
        int columnaSalida = Integer.parseInt(s2);
        //metemos las dos variables al metodo set salida del tauler
        a.setSalida(rowSalida, columnaSalida - 1);
        //hacemos un return a
        return a;
    }
}
