package GreedIsland.Maps.MapTiles;

// FACTORY METHOD 1 //

import GreedIsland.CustomExceptions.InvalidTileIdException;
import GreedIsland.States.State;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// clasa Product din sablonul Factory (clasa de baza pe care o vor extinde hartile concrete)
public class BaseAbstractMap
{
    protected int[][] frontLayer;               // layer-ul de suprafata al hartii (cu tile-uri)
    protected int[][] backLayer;                // layer-ul de dedesubt al hartii (cu tile-uri)
    protected MapNames mapId;                   // id-ul hartii
    protected MapNames mapW, mapN, mapS, mapE;  // vecinii hartii (Acestea reprezinta tot mapId-uri. Ne vor ajuta la tranzitia intre scene)
    protected MapNames mapInterior;             // id-ul "hartii" care va trebui randata atunci cand intram in casa din harta curenta (daca exista)
    protected MapNames mapExterior;             // id-ul hartii care va trebui randata atunci cand iesim dintr-o casa

    // Dimensiunile unei harti
    public int width = 25;
    public int height = 20;

    // Citirea layerelor unei scene
    public void readMapLayersFromFile(String sceneName)
    {
        try
        {
            Scanner finFront = new Scanner(new FileReader("res/maps/" + sceneName + "frontlayer.txt"));
            Scanner finBack = new Scanner(new FileReader("res/maps/" + sceneName + "backlayer.txt"));

            // Citim din fisier layerele acestei harti
            frontLayer = new int[width][height];
            backLayer = new int[width][height];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    try
                    {
                        int a = finBack.nextInt();
                        int b = finFront.nextInt();
                        if((a >= 22 && a <=29) || a >= 65)
                            throw new InvalidTileIdException("Exceptie - found invalid tile id (read from file)!");
                        if((b >= 22 && b <=29) || b >= 65)
                            throw new InvalidTileIdException("Exceptie - found invalid tile id (read from file)!");

                        backLayer[x][y] = a;
                        frontLayer[x][y] = b;
                    }
                    catch(InvalidTileIdException ex)
                    {
                        System.out.println(ex.getMessage() + " in scene " + this.getClass().getName());
                        System.exit(1);
                    }
                }
            }

            finBack.close();
            finFront.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Exceptie - citirea datelor din fisier");
        }

    }

    // Gettere
    public int[][] getFrontLayer() { return frontLayer; }
    public int[][] getBackLayer() { return backLayer; }
    public MapNames getMapId() { return mapId; }
    public MapNames getMapW() { return mapW; }
    public MapNames getMapN() { return mapN; }
    public MapNames getMapE() { return mapE; }
    public MapNames getMapS() { return mapS; }
    public MapNames getMapInterior() { return mapInterior; }
    public MapNames getMapExterior() { return mapExterior; }

}
