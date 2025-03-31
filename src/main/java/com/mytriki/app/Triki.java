package com.mytriki.app;

import java.util.Scanner;

public class Triki {

    private static char[][] tablero = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    private static Scanner scanner = new Scanner(System.in);
    private static char jugadorActual = 'X';

    public static void main(String[] args) {
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            imprimirTablero();
            hacerMovimiento();
            juegoTerminado = verificarGanador() || verificarEmpate();
            cambiarJugador();
        }

        imprimirTablero();
        if (verificarGanador()) {
            cambiarJugador(); // Para mostrar el jugador ganador correcto
            System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
        } else {
            System.out.println("¡Empate!");
        }
        scanner.close();
    }

    // Imprime el tablero actual
    public static void imprimirTablero() {
        int contador = 1;
        System.out.println("-------------");
        
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    System.out.print(contador);
                } else {
                    System.out.print(tablero[i][j]);
                }

                if (j < 2) System.out.print(" | ");
                contador++;
            }
            System.out.print(" |");

            System.out.println();
            if (i < 2) {
                System.out.println("-------------");
            }
        }

        System.out.println("-------------");
    }

    // Permite al jugador actual hacer su movimiento
    public static void hacerMovimiento() {
        int posicion;
        while (true) {
            System.out.println("Jugador " + jugadorActual + ", introduce un número del 1 al 9 para hacer tu movimiento:");
            posicion = scanner.nextInt();
            if (posicion >= 1 && posicion <= 9 && esCasillaLibre(posicion)) {
                break;
            } else {
                System.out.println("¡Posición inválida o ya ocupada! Intenta de nuevo.");
            }
        }
        colocarSimbolo(posicion);
    }

    // Verifica si la casilla seleccionada está libre
    public static boolean esCasillaLibre(int posicion) {
        int fila = (posicion - 1) / 3;
        int columna = (posicion - 1) % 3;
        return tablero[fila][columna] == ' ';
    }

    // Coloca el símbolo del jugador en el tablero
    public static void colocarSimbolo(int posicion) {
        char simbolo = jugadorActual;
        int fila = (posicion - 1) / 3;
        int columna = (posicion - 1) % 3;
        tablero[fila][columna] = simbolo;
    }

    // Cambia el jugador actual
    public static void cambiarJugador() {
        jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
    }

    // Verifica si hay un ganador
    public static boolean verificarGanador() {
        // Comprobar filas
        if (tablero[0][0] == jugadorActual && tablero[0][1] == jugadorActual && tablero[0][2] == jugadorActual) return true;
        if (tablero[1][0] == jugadorActual && tablero[1][1] == jugadorActual && tablero[1][2] == jugadorActual) return true;
        if (tablero[2][0] == jugadorActual && tablero[2][1] == jugadorActual && tablero[2][2] == jugadorActual) return true;

        // Comprobar columnas
        if (tablero[0][0] == jugadorActual && tablero[1][0] == jugadorActual && tablero[2][0] == jugadorActual) return true;
        if (tablero[0][1] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][1] == jugadorActual) return true;
        if (tablero[0][2] == jugadorActual && tablero[1][2] == jugadorActual && tablero[2][2] == jugadorActual) return true;

        // Comprobar diagonales
        if (tablero[0][0] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][2] == jugadorActual) return true;
        if (tablero[0][2] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][0] == jugadorActual) return true;

        return false;
    }

    // Verifica si hay un empate
    public static boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false; // Todavía hay espacios vacíos
                }
            }
        }
        return true; // No hay espacios vacíos, es un empate
    }
}