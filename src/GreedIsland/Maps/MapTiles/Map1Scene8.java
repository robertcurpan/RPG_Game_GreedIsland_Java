package GreedIsland.Maps.MapTiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// FACTORY METHOD 1 //

// Clasa ConcreteProduct1 din sablonul Factory
// Clasa Singleton -> va fi folosita "metoda Singleton" in cadrul metodei Factory
public class Map1Scene8 extends BaseAbstractMap
{
    private static volatile Map1Scene8 mapInstance;

    private Map1Scene8() throws FileNotFoundException
    {
        Scanner finFront = new Scanner(new FileReader("res/maps/map1scene8frontlayer.txt"));
        Scanner finBack = new Scanner(new FileReader("res/maps/map1scene8backlayer.txt"));

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
        mapId = MapNames.map1scene8;

        // Stabilim "vecinii" acestei harti (hartile adiacente in care putem merge)
        // Daca un vecin are valoarea 0 inseamna ca nu exista o harta in acea directie.
        mapW        = MapNames.noMap;
        mapN        = MapNames.noMap;
        mapE        = MapNames.noMap;
        mapS        = MapNames.noMap;
        mapInterior = MapNames.noMap;
        mapExterior = MapNames.map1scene1;

    }

    // "Metoda Singleton"
    public static Map1Scene8 getInstance() throws FileNotFoundException
    {
        if(mapInstance == null)
        {
            synchronized (Map1Scene8.class)
            {
                if(mapInstance == null)
                    mapInstance = new Map1Scene8();
            }
        }

        return mapInstance;
    }

}
