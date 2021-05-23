package GreedIsland.Maps.MapTiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// FACTORY METHOD 1 //

// clasa ConcreteProduct1 din sablonul Factory
// Prima mapa concreta (clasa Singleton) -> va fi folosita "metoda Singleton" in cadrul metodei Factory
public class Map1Scene1 extends BaseAbstractMap
{
    private static volatile Map1Scene1 mapInstance;

    private Map1Scene1() throws FileNotFoundException
    {
        // Citim layerele hartii din fisier (inauntrul functiei avem gestiunea erorilor -> verificam daca id-ul tile-urilor sunt bune)
        readMapLayersFromFile("map1scene1");

        // Stabilim id-ul acestei harti
        mapId = MapNames.map1scene1;

        // Stabilim "vecinii" acestei harti (hartile adiacente in care putem merge)
        // Daca un vecin are valoarea 0 inseamna ca nu exista o harta in acea directie.
        mapW        = MapNames.map1scene4;
        mapN        = MapNames.noMap;
        mapE        = MapNames.map1scene2; //In dreapta acestei harti se afla Map1Scene2
        mapS        = MapNames.map1scene3;
        mapInterior = MapNames.map1scene8; //Interiorul casei din aceasta harta
        mapExterior = MapNames.noMap;

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