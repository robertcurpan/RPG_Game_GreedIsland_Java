package GreedIsland.Items;

import GreedIsland.Animations.AnimationList;
import GreedIsland.Collision.Collision;
import GreedIsland.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/*! \class public abstract class Enemy extends Character
    \brief Implementeaza notiunea abstracta de inamic. Aceasta clasa va fi extinsa de clasele de tip inamic concret.
 */
public abstract class Enemy extends Character
{
    protected BufferedImage image;    /*!< Referinta catre imaginea curenta a inamicului.*/

    // ################################################################################################# //

    /*! \fn public Enemy(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Enemy.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a inamicului.
        \param y Pozitia initiala pe axa Y a inamicului.
     */
    public Enemy(RefLinks refLink, float x, float y)
    {
        /// Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        /// Stabileste pozitia relativa si dimensiunea dreptunghiului de coliziune in starea implicita (normala)
        normalBounds.x = 8;
        normalBounds.y = 8;
        normalBounds.width = 48;
        normalBounds.height = 48;
        /// Stabileste pozitia relativa si dimensiunea dreptunghiului de coliziune in starea de atac
        attackBounds.x = 0;
        attackBounds.y = 0;
        attackBounds.width = 64;
        attackBounds.height = 64;

        nextPos = new Rectangle(0, 0, normalBounds.width, normalBounds.height);
    }

    /*! \fn public boolean WillCollideWithTiles()
        \brief Verificam daca exista coliziuni intre erou si elementele "solide" ale hartii. Vom folosi harta din RefLinks
     */
    public boolean WillCollideWithTiles() throws FileNotFoundException
    {
        int[][] frontLayerMap = refLink.GetMap().getMapTiles().getFrontLayer();
        for(int i=0; i<frontLayerMap.length; i++)
            for(int j=0; j<frontLayerMap[i].length; j++)
            {
                // Verificam coliziunea intre urmatoarea pozitie a dreptunghiului de coliziune al inamicului si dreptunghiul de coliziune al tile-urilor.
                if(frontLayerMap[i][j] != 0 && refLink.GetMap().GetTileFrontLayer(i,j).IsSolid() &&
                        Collision.CollisionDetection(nextPos, new Rectangle(i*32 + refLink.GetMap().GetTileFrontLayer(i,j).tileBounds.x, j*32 + refLink.GetMap().GetTileFrontLayer(i,j).tileBounds.y,
                                refLink.GetMap().GetTileFrontLayer(i,j).tileBounds.width, refLink.GetMap().GetTileFrontLayer(i,j).tileBounds.height)))
                {
                    return true;
                }
            }

        return false;
    }

    /*! \fn public boolean WillCollideWithItems()
        \brief Verificam daca exista coliziuni intre inamic si obiectele de tip Item. Vom folosi harta din RefLinks
     */
    public boolean WillCollideWithItems()
    {
        for(Item i : refLink.GetMap().getMapPopulation().getItems())
        {
            if(Collision.CollisionDetection(nextPos, new Rectangle((int)i.GetX() + i.bounds.x, (int)i.GetY() + i.bounds.y, i.bounds.width, i.bounds.height)))
                return true;
        }
        return false;
    }

    /*! \fn public abstract int setAnimationID()
        \brief Metoda abstracta ce va fi descrisa in clasele concrete de inamici. Ea va selecta imaginile corespunzatoare pt ca inamicul nostru sa fie animat frumos si corect.
     */
    public abstract int setAnimationID();

}
