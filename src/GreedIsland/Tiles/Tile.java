package GreedIsland.Tiles;

import GreedIsland.Tiles.HouseInteriorTiles.*;
import GreedIsland.Tiles.HouseTiles.*;
import GreedIsland.Tiles.RiverTiles.*;
import GreedIsland.Tiles.RoofTiles.*;
import GreedIsland.Tiles.TreeTiles.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile {
    private static final int NO_TILES = 70;
    public static Tile[] tiles = new Tile[NO_TILES];    // Vector de referinte de tipuri de dala

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie
    public static Tile dirtTile                 = new DirtTile(1);
    public static Tile grassTile                = new GrassTile(2);
    public static Tile grassTallTile            = new GrassTallTile(3);
    public static Tile grassStrandsTile         = new GrassStrandsTile(6);
    public static Tile flowersTile              = new FlowersTile(7);
    public static Tile fountainTop              = new FountainTopTile(30);
    public static Tile fountainBottom           = new FountainBottomTile(31);
    public static Tile treeRoundTile1           = new TreeRoundTile1(35);
    public static Tile treeRoundTile2           = new TreeRoundTile2(36);
    public static Tile treeRoundTile3           = new TreeRoundTile3(37);
    public static Tile treeRoundTile4           = new TreeRoundTile4(38);
    public static Tile treeCut                  = new TreeCutTile(39);
    public static Tile treeFallen               = new TreeFallenTile(40);
    public static Tile riverVertical            = new RiverVerticalTile(41);
    public static Tile riverHorizontal          = new RiverHorizontalTile(42);
    public static Tile riverNtoE                = new RiverNtoETile(43);
    public static Tile riverWtoS                = new RiverWtoSTile(44);
    public static Tile riverStoE                = new RiverStoETile(8);
    public static Tile riverWtoN                = new RiverWtoNTile(9);
    public static Tile waterSplash              = new WaterSplashTile(45);
    public static Tile barrel                   = new BarrelTile(46);
    public static Tile houseStone               = new HouseStoneTile(47);
    public static Tile houseStoneLeftMargin     = new HouseStoneLeftMarginTile(48);
    public static Tile houseStoneRightMargin    = new HouseStoneRightMarginTile(49);
    public static Tile roofSmallLight           = new RoofSmallLightTile(50);
    public static Tile roofMediumLight          = new RoofMediumLightTile(51);
    public static Tile roofBigLight             = new RoofBigLightTile(52);
    public static Tile roofCenterSmallLight     = new RoofCenterSmallLightTile(53);
    public static Tile roofCenterBigLight       = new RoofCenterBigLightTile(54);
    public static Tile roofSmallDark            = new RoofSmallDarkTile(55);
    public static Tile roofMediumDark           = new RoofMediumDarkTile(56);
    public static Tile roofBigDark              = new RoofBigDarkTile(57);
    public static Tile roofCenterSmallDark      = new RoofCenterSmallDarkTile(58);
    public static Tile roofCenterBigDark        = new RoofCenterBigDarkTile(59);
    public static Tile doorClosed               = new DoorClosedTile(60);
    public static Tile doorOpened               = new DoorOpenedTile(61);
    public static Tile windowClosed             = new WindowClosedTile(62);
    public static Tile windowOpened             = new WindowOpenedTile(63);
    public static Tile torch                    = new TorchTile(64);
        ///Tile-uri din interiorul casei
    public static Tile empty                    = new EmptyTile(5);
    public static Tile floor                    = new FloorTile(10);
    public static Tile smallDoor                = new SmallDoorTile(11);
    public static Tile wallBack                 = new WallBackTile(12);
    public static Tile wallBackTop              = new WallBackTopTile(13);
    public static Tile wallFront                = new WallFrontTile(14);
    public static Tile wallFrontTop             = new WallFrontTopTile(15);
    public static Tile wallLeftCorner           = new WallLeftCornerTile(16);
    public static Tile wallLeftCornerTop        = new WallLeftCornerTopTile(17);
    public static Tile wallLeft                 = new WallLeftTile(18);
    public static Tile wallRight                = new WallRightTile(19);
    public static Tile wallRightCorner          = new WallRightCornerTile(20);
    public static Tile wallRightCornerTop       = new WallRightCornerTopTile(21);

    public static final int TILE_WIDTH = 32;    // Latimea unei dale in Sprite Sheet
    public static final int TILE_HEIGHT = 32;   // Inaltimea unei dale in Sprite Sheet

        /// Adaugam acest camp deoarece "vederea" jocului nu este "complet" de sus. De exemplu, o coliziune intre capul eroului
        /// si radacina unui copac nu ar fi realista. Adaugam acest camp pt a corecta astfel de probleme.
        /// (vom schimba parametrii acestui Rectangle in clasele derivate (tile-uri concrete)).
    public Rectangle tileBounds;

    protected BufferedImage img;        // Imaginea aferenta tipului de dala.
    protected final int id;             // Id-ul unic aferent tipului de dala.

 // ################################################################################################ //

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei. (pt tile-uri 32x32)
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd){
        img = image;
        id = idd;
        tileBounds = new Rectangle(0,0,0,0);

        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update(){

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y){
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
               Implicit, daca nu suprascriem functia in clasa derivata, obiectul NU va permite coliziune (nu va fi solid)
     */
    public boolean IsSolid() { return false; }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId() { return id; }

}
