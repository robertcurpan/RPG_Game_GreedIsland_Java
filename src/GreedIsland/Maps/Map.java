package GreedIsland.Maps;

import GreedIsland.Items.Hero;
import GreedIsland.Maps.MapPopulation.Map1Scene1Population;
import GreedIsland.Maps.MapPopulation.MapPopulation;
import GreedIsland.Maps.MapTiles.BaseAbstractMap;
import GreedIsland.Maps.MapTiles.MapNames;
import GreedIsland.RefLinks;
import GreedIsland.Tiles.Tile;

import java.awt.*;
import java.io.FileNotFoundException;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map {
    private RefLinks refLink;                           // O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program

    private MapFactory mapFactory;                      // fabrica de harti
    private MapPopulationFactory mapPopulationFactory;  // fabrica de iteme (obiectele cu care putem interactiona pe fiecare harta)
    private BaseAbstractMap mapTiles;                   // mapTiles va contine tile-urile propriu-zise ale hartii(scenei) curente
    private MapPopulation mapPopulation;                // mapPopulation va contine toate elementele cu care putem interactiona in scena curenta (inamici?, items, usi etc.)

 // ################################################################################################################ //

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink) throws FileNotFoundException {
            /// Retine referinta "shortcut"
        this.refLink = refLink;
            /// Instantiem mapFactory
        mapFactory = new MapFactory();
            /// Instantiem mapPopulationFactory
        mapPopulationFactory = new MapPopulationFactory();
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
    public void Draw(Graphics g)
    {
            /// Afisam tile-urile hartii curente
        drawTiles(g);
            /// Afisam itemele corespunzatoare hartii curente
        mapPopulation.drawItems(g);
    }

    /*! \fn public void drawTiles(Graphics g)
        \brief Desenam tile-urile hartii curente
     */
    public void drawTiles(Graphics g)
    {
        /// Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < refLink.GetGame().GetHeight()/Tile.TILE_HEIGHT; y++)
        {
            for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            {
                if(mapTiles.getBackLayer()[x][y] == 0)
                {
                    // Nu desenam nimic (0 in matricea cu codurile aferente dalelor inseamna ca nu desenam nimic)
                    // Practic, 0 reprezinta spatiul ocupat de o dala mai mare de 32x32
                }
                else
                {
                    GetTileBackLayer(x,y).Draw(g, (int)x * Tile.TILE_HEIGHT, (int)y * Tile.TILE_WIDTH);
                }

                if(mapTiles.getFrontLayer()[x][y] == 0)
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


    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld() throws FileNotFoundException
    {
            /// Instantiere/returnare mapTiles (cu apel al metodei factory)
        mapTiles = mapFactory.getMap(MapNames.map1scene1);
            /// Instantiere/returnare mapPopulation (cu apel "metoda Singleton")
        mapPopulation = mapPopulationFactory.getMapPopulation(MapNames.map1scene1, refLink);
    }


    /*! \fn public BaseAbstractMap getMapTiles()
        \brief Returnam mapTiles (a hartii curente)
     */
    public BaseAbstractMap getMapTiles()
    {
        return mapTiles;
    }

    /*! \fn public MapPopulation getMapPopulation()
        \brief Returnam mapPopulation (a hartii curente)
     */
    public MapPopulation getMapPopulation()
    {
        return mapPopulation;
    }

    /*! \fn public void changeScene()
        \brief Aici vom realiza tranzitiile catre alte harti (modificarea scenei curente)
     */
    public void changeScene() throws FileNotFoundException
    {
        // Coliziunea cu un block de dirt este verificata in clasa Hero in functia de coliziune cu Tile-urile.
        // Aici stabilim daca eroul a iesit de pe harta si, daca da, incarcam scena corespunzatoare

        Hero hero = Hero.getHeroInstance(refLink, 0, 0); //x si y nu se vor lua in considerare deoarece eroul este deja creat

        if(hero.GetX() < -30.0 && mapTiles.getMapW() != MapNames.noMap)     // daca eroul a depasit harta in stanga si exista o harta in acel sens
        {
            // Actualizam mapPopulation (sa corespunda noii harti incarcate)
            mapPopulation = mapPopulationFactory.getMapPopulation(mapTiles.getMapW(), refLink);
            // Facem tranzitia catre vecinul din stanga (W)
            mapTiles = mapFactory.getMap(mapTiles.getMapW());
            // Actualizam si pozitia eroului in noua scena (am venit din dreapta spre stanga, deci il pozitionam aproape de capatul din dreapta al scenei curente)
            hero.SetX(780); // y-ul ramane acelasi
        }
        if(hero.GetX() > 780.0 && mapTiles.getMapE() != MapNames.noMap)
        {
            // Actualizam mapPopulation (sa corespunda noii harti incarcate)
            mapPopulation = mapPopulationFactory.getMapPopulation(mapTiles.getMapE(), refLink);
            // Facem tranzitia catre vecinul din dreapta (E)
            mapTiles = mapFactory.getMap(mapTiles.getMapE());
            // Actualizam si pozitia eroului in noua scena
            hero.SetX(-20);
        }
        if(hero.GetY() < -30.0 && mapTiles.getMapN() != MapNames.noMap)
        {
            // Actualizam mapPopulation (sa corespunda noii harti incarcate)
            mapPopulation = mapPopulationFactory.getMapPopulation(mapTiles.getMapN(), refLink);
            // Facem tranzitia catre vecinul de deasupra (N)
            mapTiles = mapFactory.getMap(mapTiles.getMapN());
            // Actualizam si pozitia eroului in noua scena
            hero.SetY(620);
        }
        if(hero.GetY() > 620.0 && mapTiles.getMapS() != MapNames.noMap)
        {
            // Actualizam mapPopulation (sa corespunda noii harti incarcate)
            mapPopulation = mapPopulationFactory.getMapPopulation(mapTiles.getMapS(), refLink);
            // Facem tranzitia catre vecinul de dedesubt (S)
            mapTiles = mapFactory.getMap(mapTiles.getMapS());
            // Actualizam si pozitia eroului in noua scena
            hero.SetY(-20);
        }

    }


    /*! \fn public Tile GetTileBackLayer(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din layer-ul din spate al matricii de dale.
     */
    public Tile GetTileBackLayer(int x, int y){
        if(x < 0 || y < 0 || x >= mapTiles.width || y >= mapTiles.height)
            return Tile.dirtTile;

        Tile t = Tile.tiles[mapTiles.getBackLayer()[x][y]];

        if(t == null)
            return Tile.grassTile;

        return t;
    }

    /*! \fn public Tile GetTileFrontLayer(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din layer-ul din fata al matricii de dale.
     */
    public Tile GetTileFrontLayer(int x, int y){
        if(x < 0 || y < 0 || x >= mapTiles.width || y >= mapTiles.height)
            return Tile.dirtTile;

        Tile t = Tile.tiles[mapTiles.getFrontLayer()[x][y]];

        if(t == null)
            return Tile.grassTile;

        return t;
    }


}

