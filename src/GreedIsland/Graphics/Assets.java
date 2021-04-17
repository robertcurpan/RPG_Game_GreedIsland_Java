package GreedIsland.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets {
        /// Referinte catre elemente grafice (sprite-uri caractere) utilizate in joc.
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;
    public static BufferedImage heroUp;
    public static BufferedImage heroDown;
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage dirt;
    public static BufferedImage grass;
    public static BufferedImage grassTall;
    public static BufferedImage treeTall1;
    public static BufferedImage treeTall2;
    public static BufferedImage treeTall3;
    public static BufferedImage treeRound1;
    public static BufferedImage treeRound2;
    public static BufferedImage treeRound3;
    public static BufferedImage treeRound4;
    public static BufferedImage treeCut;
    public static BufferedImage treeFallen;
    public static BufferedImage fountainTop;
    public static BufferedImage fountainBottom;
    public static BufferedImage waterSplash;
    public static BufferedImage riverVertical;
    public static BufferedImage riverHorizontal;
    public static BufferedImage riverNtoE;
    public static BufferedImage riverWtoS;
    public static BufferedImage barrel;
    public static BufferedImage roofSmallLight;
    public static BufferedImage roofMediumLight;
    public static BufferedImage roofBigLight;
    public static BufferedImage roofCenterSmallLight;
    public static BufferedImage roofCenterBigLight;
    public static BufferedImage roofSmallDark;
    public static BufferedImage roofMediumDark;
    public static BufferedImage roofBigDark;
    public static BufferedImage roofCenterSmallDark;
    public static BufferedImage roofCenterBigDark;
    public static BufferedImage houseStone;
    public static BufferedImage houseStoneLeftMargin;
    public static BufferedImage houseStoneRightMargin;
    public static BufferedImage doorClosed;
    public static BufferedImage doorOpened;
    public static BufferedImage torch;
    public static BufferedImage windowClosed;
    public static BufferedImage windowOpened;

 // ################################################################################################## //

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init(){
            /// Se creeaza temporar obiecte de tip SpriteSheetCharacters initializate prin intermediul clasei ImageLoader
        SpriteSheetCharacters sheetMainCharacters = new SpriteSheetCharacters(ImageLoader.LoadImage("/textures/Characters_sprites.png"));
            /// Se creeaza temporar obiecte de tip SpriteSheet initializate prin intermediul clasei ImageLoader
        SpriteSheet sheetMap1 = new SpriteSheet(ImageLoader.LoadImage("/textures/Forest+House_Tileset.png"));

            /// Se obtin subimaginile corespunzatoare caracterelor necesare
        heroLeft = sheetMainCharacters.crop(0,9);
        heroRight = sheetMainCharacters.crop(0, 11);
        heroUp = sheetMainCharacters.crop(0,8);
        heroDown = sheetMainCharacters.crop(0,10);
            /// Se obtin subimaginile corespunzatoare tile-urilor necesare
        dirt = sheetMap1.crop(8,9);
        grass = sheetMap1.crop(4,1);
        grassTall = sheetMap1.crop(5,1);
        treeTall1 = sheetMap1.crop(8,6);
        treeTall2 = sheetMap1.crop(8,7);
        treeTall3 = sheetMap1.crop(8,8);
        treeRound1 = sheetMap1.crop(4,6);
        treeRound2 = sheetMap1.crop(5,6);
        treeRound3 = sheetMap1.crop(4,7);
        treeRound4 = sheetMap1.crop(5,7);
        treeCut = sheetMap1.crop(6,7);
        treeFallen = sheetMap1.crop(6,6);
        fountainTop = sheetMap1.crop(8,1);
        fountainBottom = sheetMap1.crop(9,1);
        waterSplash = sheetMap1.crop(13,1);
        riverVertical = sheetMap1.crop(6,8);
        riverHorizontal = sheetMap1.crop(7,8);
        riverNtoE = sheetMap1.crop(6,9);
        riverWtoS = sheetMap1.crop(5,9);
        barrel = sheetMap1.crop(6,1);
        roofSmallLight = sheetMap1.crop(4,2);
        roofMediumLight = sheetMap1.crop(5,2);
        roofBigLight = sheetMap1.crop(6,2);
        roofCenterSmallLight = sheetMap1.crop(7,2);
        roofCenterBigLight = sheetMap1.crop(8,2);
        roofSmallDark = sheetMap1.crop(13,2);
        roofMediumDark = sheetMap1.crop(12,2);
        roofBigDark = sheetMap1.crop(11,2);
        roofCenterSmallDark = sheetMap1.crop(10,2);
        roofCenterBigDark = sheetMap1.crop(9,2);
        houseStone = sheetMap1.crop(4,0);
        houseStoneLeftMargin = sheetMap1.crop(9,0);
        houseStoneRightMargin = sheetMap1.crop(10,0);
        doorClosed = sheetMap1.crop(6,0);
        doorOpened = sheetMap1.crop(5,0);
        torch = sheetMap1.crop(6,16);
        windowClosed = sheetMap1.crop(8,0);
        windowOpened = sheetMap1.crop(7,0);
    }
}
