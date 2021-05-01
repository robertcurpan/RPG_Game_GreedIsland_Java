package GreedIsland.Maps.MapTiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// clasa ConcreteProduct1 din sablonul Factory
// Prima mapa concreta (clasa Singleton) -> va fi folosita "metoda Singleton" in cadrul metodei Factory
public class Map1Scene1 extends BaseAbstractMap
{
    private static volatile Map1Scene1 mapInstance;

    private Map1Scene1() throws FileNotFoundException
    {
        Scanner finFront = new Scanner(new FileReader("res/maps/map1scene1frontlayer.txt"));
        Scanner finBack = new Scanner(new FileReader("res/maps/map1scene1backlayer.txt"));

        // Citim din fisier layerele acestei harti
        frontLayer = new int[width][height];
        backLayer = new int[width][height];

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                backLayer[x][y] = finBack.nextInt();
                frontLayer[x][y] = finFront.nextInt();
            }
        }

        finBack.close();
        finFront.close();

        // Stabilim id-ul acestei harti
        mapId = 11;

        // Stabilim "vecinii" acestei harti (hartile adiacente in care putem merge)
        // Daca un vecin are valoarea 0 inseamna ca nu exista o harta in acea directie.
        mapW = 0;
        mapN = 0;
        mapE = 12; //In dreapta acestei harti se afla Map1Scene2
        mapS = 0;

    }

    // "Metoda Singleton"
    public static Map1Scene1 getInstance() throws FileNotFoundException
    {
        if(mapInstance == null)
        {
            synchronized (Map1Scene1.class)
            {
                if(mapInstance == null)
                    mapInstance = new Map1Scene1();
            }
        }

        return mapInstance;
    }

}