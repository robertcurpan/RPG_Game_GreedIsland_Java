package GreedIsland.Maps.MapTiles;

// FACTORY METHOD 1 //

// clasa Product din sablonul Factory (clasa de baza pe care o vor extinde hartile concrete)
public class BaseAbstractMap
{
    protected int[][] frontLayer;               // layer-ul de suprafata al hartii (cu tile-uri)
    protected int[][] backLayer;                // layer-ul de dedesubt al hartii (cu tile-uri)
    protected MapNames mapId;                   // id-ul hartii
    protected MapNames mapW, mapN, mapS, mapE;  // vecinii hartii (Acestea reprezinta tot mapId-uri. Ne vor ajuta la tranzitia intre scene)

    // Dimensiunile unei harti
    public int width = 25;
    public int height = 20;

    // Gettere
    public int[][] getFrontLayer() { return frontLayer; }
    public int[][] getBackLayer() { return backLayer; }
    public MapNames getMapId() { return mapId; }
    public MapNames getMapW() { return mapW; }
    public MapNames getMapN() { return mapN; }
    public MapNames getMapE() { return mapE; }
    public MapNames getMapS() { return mapS; }
}
