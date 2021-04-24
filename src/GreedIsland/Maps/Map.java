package GreedIsland.Maps;

import GreedIsland.RefLinks;
import GreedIsland.Tiles.Tile;

import java.awt.*;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map {
    private RefLinks refLink;           // O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program
    private int width;                  // Latimea hartii in numar de dale
    private int height;                 // Inaltimea hartii in numar de dale
    private int[][] tilesBackLayer;     // Referinta catre o matrice cu codurile dalelor ce vor construi layer-ul de dedesubt al mapei
    private int[][] tilesFrontLayer;    // Referinta catre o matrice cu codurile dalelor ce vor construi layer-ul de deasupra al mapei

 // ################################################################################################################ //

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink){
            /// Retine referinta "shortcut"
        this.refLink = refLink;
            /// Incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld();
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public void Update(){

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextul grafic in care se realizeaza desenarea.
     */
    public void Draw(Graphics g){
            /// Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < refLink.GetGame().GetHeight()/Tile.TILE_HEIGHT; y++)
        {
            for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            {
                if(tilesBackLayer[x][y] == 0)
                {
                    // Nu desenam nimic (0 in matricea cu codurile aferente dalelor inseamna ca nu desenam nimic)
                    // Practic, 0 reprezinta spatiul ocupat de o dala mai mare de 32x32
                }
                else
                {
                    GetTileBackLayer(x,y).Draw(g, (int)x * Tile.TILE_HEIGHT, (int)y * Tile.TILE_WIDTH);
                }

                if(tilesFrontLayer[x][y] == 0)
                {
                    // Nu desenam nimic (0 in matricea cu codurile aferente dalelor inseamna ca nu desenam nimic)
                    // Practic, 0 reprezinta spatiul ocupat de o dala mai mare de 32x32
                }
                else
                {
                    GetTileFrontLayer(x,y).Draw(g, (int)x * Tile.TILE_HEIGHT, (int)y * Tile.TILE_WIDTH);
                }
            }
        }
    }

    /*! \fn public Tile GetTileBackLayer(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din layer-ul din spate al matricii de dale.
     */
    public Tile GetTileBackLayer(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.dirtTile;

        Tile t = Tile.tiles[tilesBackLayer[x][y]];

        if(t == null)
            return Tile.grassTile;

        return t;
    }

    /*! \fn public Tile GetTileFrontLayer(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din layer-ul din fata al matricii de dale.
     */
    public Tile GetTileFrontLayer(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.dirtTile;

        Tile t = Tile.tiles[tilesFrontLayer[x][y]];

        if(t == null)
            return Tile.grassTile;

        return t;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld(){
            /// Atentie! Latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
            /// se poate implementa notiunea de camera/cadru de vizualizare a hartii.

            /// Se stabileste latimea hartii in numar de dale
        width = 25;
            /// Se stabileste inaltimea hartii in numar de dale
        height = 20;
            /// Se construieste matricea cu coduri de dale
        tilesBackLayer = new int[width][height];
        tilesFrontLayer = new int[width][height];
            /// Se incarca matricea cu coduri
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                tilesBackLayer[x][y] = Map1CabinSceneBackLayer(y,x);
                tilesFrontLayer[x][y] = Map1CabinSceneFrontLayer(y,x);
            }
        }
    }

    /*! \fn public int[][] GetFrontLayerMap()
        \brief Returnam layer-ul de suprafata al hartii pt a-l putea folosi in coliziuni.
     */
    public int[][] GetFrontLayerMap()
    {
        return tilesFrontLayer;
    }

    /*! \fn public int[][] GetBackLayerMap()
        \brief Returnam layer-ul de dedesubt al hartii.
     */
    public int[][] GetBackLayerMap()
    {
        return tilesBackLayer;
    }

    /*! \fn private int Map1CabinScene(int x ,int y)
        \brief O harta incarcata static.

        \param x linia pe care se afla codul dalei de interes.
        \param y coloana pe care se afla codul dalei de interes.
     */
    private int Map1CabinSceneBackLayer(int x, int y){
            /// Definire statica a layer-ului din spate al matricii de coduri de dale.
        final int map[][] = {
                {3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3},
                {3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
                {3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3},
                {3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3},
                {2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        return map[x][y];
    }

    private int Map1CabinSceneFrontLayer(int x, int y){
            /// Definire statica a layer-ului din fata al matricii de coduri de dale.
        final int map[][] = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,41, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0,35,36, 0, 0, 0, 0, 0, 0, 0,43,42,42,42,42,42,44, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0,37,38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,41, 0, 0, 0,35,36, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,35,36, 0, 0,41, 0, 0, 0,37,38, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,37,38, 0, 0,43,42,42,44, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,41, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0,35,36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,43,42,42,42},
                { 0, 0, 0, 0, 0, 0,37,38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0,35,36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0,37,38, 0, 0,50,55, 0, 0,39, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0,50,51,52,57,56,55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0,51,52,53,54,59,58,57,56, 0, 0, 0, 0, 0, 0,35,36, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0,53,54,47,47,47,47,59,58, 0, 0, 0, 0, 0, 0,37,38, 0, 0, 0, 0, 0, 0, 0},
                {35,36,48,47,62,47,47,62,47,49, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {37,38,48,47,47,47,47,47,47,49, 0,35,36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {46, 0,48,63,47,47,47,47,63,49, 0,37,38, 0, 0, 0, 0, 0, 0, 0,30, 0, 0, 0, 0},
                { 0,46,48,47,47,60,61,47,47,49, 0, 0, 0, 0, 0,40, 0, 0, 0, 0,31,45, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0,35,36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0,37,38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        return map[x][y];
    }
}

