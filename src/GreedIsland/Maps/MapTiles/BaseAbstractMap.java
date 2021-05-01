package GreedIsland.Maps.MapTiles;

// clasa Product din sablonul Factory (clasa de baza pe care o vor extinde hartile concrete)
public class BaseAbstractMap
{
    protected int[][] frontLayer;           // layer-ul de suprafata al hartii (cu tile-uri)
    protected int[][] backLayer;            // layer-ul de dedesubt al hartii (cu tile-uri)
    protected int mapId;                    // id-ul hartii
    protected int mapW, mapN, mapS, mapE;   // vecinii hartii (Acestea reprezinta tot mapId-uri. Ne vor ajuta la tranzitia intre scene)

    // Dimensiunile unei harti
    public int width = 25;
    public int height = 20;

    // Gettere
    public int[][] getFrontLayer() { return frontLayer; }
    public int[][] getBackLayer() { return backLayer; }
    public int getMapId() { return mapId; }
    public int getMapW() { return mapW; }
    public int getMapN() { return mapN; }
    public int getMapE() { return mapE; }
    public int getMapS() { return mapS; }
}
