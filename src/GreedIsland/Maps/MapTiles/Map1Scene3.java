package GreedIsland.Maps.MapTiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// FACTORY METHOD 1 //

// Clasa ConcreteProduct1 din sablonul Factory
// Clasa Singleton -> va fi folosita "metoda Singleton" in cadrul metodei Factory
public class Map1Scene3 extends BaseAbstractMap
{
    private static volatile Map1Scene3 mapInstance;

    private Map1Scene3() throws FileNotFoundException
    {
        // Citim layerele hartii din fisier (inauntrul functiei avem gestiunea erorilor -> verificam daca id-ul tile-urilor sunt bune)
        readMapLayersFromFile("map1scene3");

        // Stabilim id-ul acestei harti
        mapId = MapNames.map1scene3;

        // Stabilim "vecinii" acestei harti (hartile adiacente in care putem merge)
        // Daca un vecin are valoarea 0 inseamna ca nu exista o harta in acea directie.
        mapW        = MapNames.noMap;
        mapN        = MapNames.map1scene1;
        mapE        = MapNames.noMap;
        mapS        = MapNames.noMap;
        mapInterior = MapNames.noMap;
        mapExterior = MapNames.noMap;

    }

    // "Metoda Singleton"
    public static Map1Scene3 getInstance() throws FileNotFoundException
    {
        if(mapInstance == null)
        {
            synchronized (Map1Scene3.class)
            {
                if(mapInstance == null)
                    mapInstance = new Map1Scene3();
            }
        }

        return mapInstance;
    }

}