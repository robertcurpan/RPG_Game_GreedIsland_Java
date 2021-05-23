package GreedIsland.Maps.MapTiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// FACTORY METHOD 1 //

// clasa ConcreteProduct1 din sablonul Factory
// Prima mapa concreta (clasa Singleton) -> va fi folosita "metoda Singleton" in cadrul metodei Factory
public class Map2Scene6 extends BaseAbstractMap
{
    private static volatile Map2Scene6 mapInstance;

    private Map2Scene6() throws FileNotFoundException
    {
        // Citim layerele hartii din fisier (inauntrul functiei avem gestiunea erorilor -> verificam daca id-ul tile-urilor sunt bune)
        readMapLayersFromFile("map2scene6");

        // Stabilim id-ul acestei harti
        mapId = MapNames.map2scene6;

        // Stabilim "vecinii" acestei harti (hartile adiacente in care putem merge)
        // Daca un vecin are valoarea 0 inseamna ca nu exista o harta in acea directie.
        mapW        = MapNames.map2scene7;
        mapN        = MapNames.noMap;
        mapE        = MapNames.map2scene5;
        mapS        = MapNames.noMap;
        mapInterior = MapNames.map2scene8;
        mapExterior = MapNames.noMap;

    }

    // "Metoda Singleton"
    public static Map2Scene6 getInstance() throws FileNotFoundException
    {
        if(mapInstance == null)
        {
            synchronized (Map2Scene6.class)
            {
                if(mapInstance == null)
                    mapInstance = new Map2Scene6();
            }
        }

        return mapInstance;
    }

}

