package GreedIsland.Items;

import GreedIsland.Animations.Animation;
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

    protected int radiusOfSight;      /*!< Distanta de la care inamicul incepe sa detecteze player-ul; cand eroul intra in cercul centrat pe inamic cu raza radiusOfSight, inamicul va urmari playerul*/
    protected String facingDirection; /*!< Variabila care ne indica in ce directie se "uita" inamicul -> ne va ajuta la alegerea corecta a animatiei de atac*/
    protected int attackingDistance;  /*!< Distanta de la care inamicul va ataca*/

    public boolean isStunned;         /*!< Flag care ne indica daca inamicul a fost lovit. In caz afirmativ, acesta va sta pe loc timp de jumatate de secunda (sau o secunda) -> socul loviturii (stunned)*/
    public Rectangle smallerBounds;   /*!< Dreptunghi de coliziune pt coliziunea cu player-ul (acesta va fi mai mic pt a permite totusi apropierea suficienta dintre inamic si erou pt a se efectua atacuri)*/

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

        // Stabileste pozitia relativa si dimensiunea dreptunghiului de coliziune in starea implicita (normala)
        normalBounds.x = 8;
        normalBounds.y = 8;
        normalBounds.width = 48;
        normalBounds.height = 48;
        // Stabileste pozitia relativa si dimensiunea dreptunghiului de coliziune in starea de atac
        attackBounds.x = 0;
        attackBounds.y = 0;
        attackBounds.width = 64;
        attackBounds.height = 64;

        // Un dreptunghi de coliziune f mic care sa nu ne permita sa trecem prin inamici
        smallerBounds = new Rectangle(30,30, 4, 4);

        // Movement
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

    /*! \fn public boolean WillCollideWithHero()
        \brief Verificam daca exista coliziuni intre inamic si erou
     */
    public boolean WillCollideWithHero()
    {
        Hero hero = Hero.getHeroInstance(refLink, 0, 0);
        return Collision.CollisionDetection(nextPos, hero.bounds);
    }

    /*! \fn public abstract int setAnimationID()
        \brief Metoda abstracta ce va fi descrisa in clasele concrete de inamici. Ea va selecta imaginile corespunzatoare pt ca inamicul nostru sa fie animat frumos si corect.
     */
    public abstract int setAnimationID();

    public abstract int enemyMovement(); // returneaza distanta dintre erou si inamic

    public abstract void Die();         // functie care va fi suprascrisa in clasele derivate (inamici completi) si scoate din lista de inamici ai hartii curente pe inamicul cuernt

    public Animation getAnimation() { return animation; }

}
