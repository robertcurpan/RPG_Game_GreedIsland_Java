package GreedIsland.Maps.MapTiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// FACTORY METHOD 1 //

// clasa ConcreteProduct2 din sablonul Factory
// A doua mapa concreta (clasa Singleton) -> va fi folosita "metoda Singleton" in cadrul metodei Factory
public class Map1Scene2 extends BaseAbstractMap
{
    private static volatile Map1Scene2 mapInstance;

    private Map1Scene2() throws FileNotFoundException
    {
        // Citim layerele hartii din fisier (inauntrul functiei avem gestiunea erorilor -> verificam daca id-ul tile-urilor sunt bune)
        readMapLayersFromFile("map1scene2");

        // Stabilim id-ul acestei harti
        mapId = MapNames.map1scene2;

        // Stabilim "vecinii" acestei harti (hartile adiacente in care putem merge)
        // Daca un vecin are valoarea "noMap" inseamna ca nu exista o harta in acea directie.
        mapW        = MapNames.map1scene1;
        mapN        = MapNames.noMap;
        mapE        = MapNames.noMap;
        mapS        = MapNames.noMap;
        mapInterior = MapNames.noMap;
        mapExterior = MapNames.noMap;

    }

    // "Metoda Singleton"
    public static Map1Scene2 getInstance() throws FileNotFoundException
    {
        if(mapInstance == null)
        {
            synchronized (Map1Scene2.class)
            {
                if(mapInstance == null)
                    mapInstance = new Map1Scene2();
            }
        }

        return mapInstance;
    }

}