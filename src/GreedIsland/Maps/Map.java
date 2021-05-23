package GreedIsland.Maps;

import GreedIsland.Items.Hero;
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
    private MapPopulation mapPopulation;                // mapPopulation va contine toate elementele cu care putem interactiona in scena curenta (inamici, items, usi etc.)

    public int NR_ENEMIES;                              // numarul de inamici din nivelul curent (va avea valori diferite pt nivelul 1, respectiv 2)
    public int score;                                   // scorul jucatorului (colectare chest -> 10 puncte; omorat inamic -> 20 puncte; -> gasit indiciu despre locatia comorii -> 50 puncte

        /// Variables for showing text on screen that indicates the event that happened
    private boolean needsToPrintOnScreen; /*!< Flag care ne indica faptul ca trebuie sa se afiseze un text pe ecran care sa afiseze actiunea realizata. */
    private int durationCounter;          /*!< Variabila care contorizeaza timpul cat textul sta afisat pe ecran. */
    private String textToBeShownOnScreen; /*!< Textul care va fi afisat pe ecran atunci cand se declanseaza o actiune. */

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
            /// Initializam scorul
        score = 0;
            /// Initializam variabilele care ne ajuta sa afisam text pe ecran
        needsToPrintOnScreen = false;
        durationCounter = 0;
        textToBeShownOnScreen = "";
            /// Initializam NR_ENEMIES
        NR_ENEMIES = 14; //21 pt lv 2
            /// Incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld();
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public void Update()
    {
        try
        {
            mapPopulation.updateEnemies();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found exception - mapPopulation.updateEnemies()");
        }
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
            /// Afisam inamicii din harta curenta
        mapPopulation.drawEnemies(g);
            /// Afisam un text pe ecran care sa indice evenimentul care tocmai s-a intamplat.
        g.setFont(new Font("TimesRoman", Font.BOLD, 18));
        g.setColor(Color.BLACK);
        showScore(score, 700, 20, g);
        g.setColor(new Color(50, 10, 0));
        showTextOnScreen(textToBeShownOnScreen, 3*60, 20, 30, g);
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
        mapTiles = mapFactory.getMap(MapNames.map2scene1);
            /// Instantiere/returnare mapPopulation (cu apel "metoda Singleton")
        mapPopulation = mapPopulationFactory.getMapPopulation(MapNames.map2scene1, refLink);
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

    public void exitHouse() throws FileNotFoundException
    {
        // coliziunea cu usa din interiorul casei si apasarea tastei e langa usa sunt verificate in clasa Hero

        Hero hero = Hero.getHeroInstance(refLink, 0, 0); //x si y nu se vor lua in considerare deoarece eroul este deja creat

        if(mapTiles.getMapExterior() != MapNames.noMap)
        {
            // Actualizam mapPopulation (sa corespunda noii harti incarcate)
            mapPopulation = mapPopulationFactory.getMapPopulation(mapTiles.getMapExterior(), refLink);
            // Facem tranzitia catre mapa din exteriorul casei
            mapTiles = mapFactory.getMap(mapTiles.getMapExterior());
            // Actualizam si pozitia eroului in noua scena (x va fi acelasi)
            hero.SetY(hero.getExitHousePosY());
        }
    }

    public void enterHouse(int idDoor)
    {
        // coliziunea cu usa din casei (exterior) si apasarea tastei e langa usa sunt verificate in clasa Hero

        Hero hero = Hero.getHeroInstance(refLink, 0, 0); //x si y nu se vor lua in considerare deoarece eroul este deja creat

        if((idDoor == 61 || (idDoor == 60 && hero.hasKey)) && mapTiles.getMapInterior() != MapNames.noMap) //daca usa e deschisa sau daca usa e inchisa si eroul are cheia
        {
            try
            {
                // Actualizam pozitia de iesire a eroului din casa (mai exact y-ul)
                hero.setExitHousePosY((int)hero.GetY() + 35); //un pic mai jos decat locul unde are loc coliziunea cu usa
                // Actualizam mapPopulation (sa corespunda noii harti incarcate)
                mapPopulation = mapPopulationFactory.getMapPopulation(mapTiles.getMapInterior(), refLink);
                // Facem tranzitia catre interiorul casei
                mapTiles = mapFactory.getMap(mapTiles.getMapInterior());
                // Actualizam si pozitia eroului in noua scena (x va fi acelasi)
                hero.SetY(540);
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("File not found exception!");
            }
        }
        else
        {
            if(idDoor == 60 && !hero.hasKey)
            {
                setVariablesForPrintingOnScreen(true, "YOU NEED A KEY TO ENTER!");
            }
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

    public void setVariablesForPrintingOnScreen(boolean needsToPrintOnScreen, String textToBeShownOnScreen)
    {
        this.needsToPrintOnScreen = needsToPrintOnScreen;
        this.textToBeShownOnScreen = textToBeShownOnScreen;
    }

    public void showTextOnScreen(String text, int duration, int x, int y, Graphics g)
    {
        if(needsToPrintOnScreen)
        {
            if(durationCounter < duration)
            {
                durationCounter++;
                g.drawString(text, x, y);
            }
            else
            {
                durationCounter = 0;
                needsToPrintOnScreen = false;
            }

        }
    }

    public void showScore(int scor, int x, int y, Graphics g)
    {
        String text = "Score: " + scor;
        g.drawString(text, x, y);
    }

}

