package GreedIsland.Maps.MapTiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// FACTORY METHOD 1 //

// clasa ConcreteProduct1 din sablonul Factory
// Prima mapa concreta (clasa Singleton) -> va fi folosita "metoda Singleton" in cadrul metodei Factory
public class Map2Scene8 extends BaseAbstractMap
{
    private static volatile Map2Scene8 mapInstance;

    private Map2Scene8() throws FileNotFoundException
    {
        // Citim layerele hartii din fisier (inauntrul functiei avem gestiunea erorilor -> verificam daca id-ul tile-urilor sunt bune)
        readMapLayersFromFile("map2scene8");

        // Stabilim id-ul acestei harti
        mapId = MapNames.map2scene8;

        // Stabilim "vecinii" acestei harti (hartile adiacente in care putem merge)
        // Daca un vecin are valoarea 0 inseamna ca nu exista o harta in acea directie.
        mapW        = MapNames.noMap;
        mapN        = MapNames.noMap;
        mapE        = MapNames.noMap;
        mapS        = MapNames.noMap;
        mapInterior = MapNames.noMap;
        mapExterior = MapNames.map2scene6;

    }

    // "Metoda Singleton"
    public static Map2Scene8 getInstance() throws FileNotFoundException
    {
        if(mapInstance == null)
        {
            synchronized (Map2Scene8.class)
            {
                if(mapInstance == null)
                    mapInstance = new Map2Scene8();
            }
        }

        return mapInstance;
    }

}

