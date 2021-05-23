package GreedIsland.Maps.MapTiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// FACTORY METHOD 1 //

// clasa ConcreteProduct1 din sablonul Factory
// Prima mapa concreta (clasa Singleton) -> va fi folosita "metoda Singleton" in cadrul metodei Factory
public class Map2Scene7 extends BaseAbstractMap
{
    private static volatile Map2Scene7 mapInstance;

    private Map2Scene7() throws FileNotFoundException
    {
        Scanner finFront = new Scanner(new FileReader("res/maps/map2scene7frontlayer.txt"));
        Scanner finBack = new Scanner(new FileReader("res/maps/map2scene7backlayer.txt"));

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
        mapId = MapNames.map2scene7;

        // Stabilim "vecinii" acestei harti (hartile adiacente in care putem merge)
        // Daca un vecin are valoarea 0 inseamna ca nu exista o harta in acea directie.
        mapW        = MapNames.noMap;
        mapN        = MapNames.noMap;
        mapE        = MapNames.map2scene6;
        mapS        = MapNames.noMap;
        mapInterior = MapNames.noMap;
        mapExterior = MapNames.noMap;

    }

    // "Metoda Singleton"
    public static Map2Scene7 getInstance() throws FileNotFoundException
    {
        if(mapInstance == null)
        {
            synchronized (Map2Scene7.class)
            {
                if(mapInstance == null)
                    mapInstance = new Map2Scene7();
            }
        }

        return mapInstance;
    }

}

