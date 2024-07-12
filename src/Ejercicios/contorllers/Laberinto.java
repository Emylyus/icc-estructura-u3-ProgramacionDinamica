package Ejercicios.contorllers;

import Ejercicios.models.Celda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Un jugador está en la esquina superior izquierda (0,0) de un tablero m x n. En el tablero hay celdas
 * transitables (true) y no transitables (false). Encuentra un camino válido para ir a la esquina
 * inferior izquierda con el jugador, sabiendo que solo se puede mover hacia abajo y hacia la derecha.
 *
 * Ejemplo 1:
 *  Input:
 *    [
 *      [true,true,true,true]
 *      [false,false,false,true]
 *      [true,true,false,true]
 *      [true,true,false,true]
 *    ]
 *
 *  Output: [(0,0), (0,1), (0,2), (0,3), (1,3), (2,3), (3,3)]
 *
 * Ejemplo 2:
 *  Input:
 *    [
 *      [true,true,true,true]
 *      [false,true,true,true]
 *      [true,true,false,false]
 *      [true,true,true,true]
 *    ]
 *
 *  Output: [(0,0), (0,1), (1,1), (2,1), (3,1), (3,2), (3,3)]
 *
 */
public class Laberinto {

    public List<Celda> getPath(boolean[][] grid) {
        List<Celda> path = new ArrayList<>();

        if(grid== null || grid.length == 0|| grid[0].length==0){
            return path;
        }

        //Map para almacenar si ya visitamos una celda y si es parte del camino
        Map<Celda,Boolean> cache = new HashMap<>();
        if(getPath(grid, 0, 0 , path, cache)){
            return path;
        }

        //sale error si no hay if por que esta fuera de los limites
        return new ArrayList<>();
    }


    private boolean getPath(boolean[][] grid, int row, int col, List<Celda> path, Map<Celda, Boolean> cache) {
        //caso breve: Si estamos fuera de los limites o de la celda no es transitable
        if(row>= grid.length||col>=grid[0].length||!grid[row][col]){
            return false;
        }

        Celda point = new Celda(row, col);
        //Parte de caching si ya visitamos esta celda, devolvemos su valor en cache
        if(cache.containsKey(point)){
            return  cache.get(point);
        }
        //caso BASE: Si estamos en la celda de destino
        boolean isAtEnd = (row == grid.length-1 && col == grid[0].length-1);
        boolean success=false;
        

        //Parte recursiva : Intentamos movernos hacia abajo o hacia la dercha
        if (isAtEnd || getPath(grid,row+1,col,path,cache)|| getPath(grid, row, col+1, path, cache)){
            path.add(point);
            success=true;
        }

        //Parte de caching : Guardamos el resultado del cache
        cache.put(point, success);
        return success;
    }

}
