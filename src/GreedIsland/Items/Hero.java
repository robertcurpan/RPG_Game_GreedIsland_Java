package GreedIsland.Items;

import GreedIsland.Animations.AnimationList;
import GreedIsland.Animations.HeroAnimation;
import GreedIsland.Collision.Collision;
import GreedIsland.Graphics.Assets;
import GreedIsland.Input.KeyManager;
import GreedIsland.RefLinks;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character
{
    private static Hero heroInstance;   // Vom face aceasta clasa sa fie Singleton
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/

 // ################################################################################################# //

    /*! \fn private Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    private Hero(RefLinks refLink, float x, float y)
    {
            /// Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
            /// Seteaza imaginea de start a eroului
        image = Assets.heroRight;

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

        animation = new HeroAnimation();

        nextPos = new Rectangle(0, 0, normalBounds.width, normalBounds.height);
    }

    /*! \fn public static Hero getHeroInstance()
        \brief Vom returna instanta UNICA a eroului.
     */
    public static Hero getHeroInstance(RefLinks refLink, float x, float y)
    {
        if(heroInstance == null)
        {
            heroInstance = new Hero(refLink, x, y);
        }

        return heroInstance;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update() throws FileNotFoundException
    {
            /// Verifica daca a fost apasata o tasta
        GetInput();
            /// Daca nu exista coliziuni cu tile-uri si nici cu iteme putem sa updatam pozitia si animatia caracterului
        if(!WillCollideWithTiles() && !WillCollideWithItems())
        {
            /// Actualizeaza  pozitia
            Move();
            /// Actualizeaza id-ul animatiei pe care urmeaza sa o "desenam" pe ecran
            animation.setAnimID(setAnimationID());
            /// Actualizeaza imaginile corespunzatoare animatiilor
            this.image = animation.playAnimation();
       }
        DoAction();
    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            /// Implicit eroul  nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

            /// Facem aceste if-uri pentru ca eroul nostru sa aiba aceeasi viteza si pe diagonala (fara if-urile suplimentare, viteza pe diagonala ar fi speed * sqrt(2))
        KeyManager km = refLink.GetKeyManager();
        if(km.up && km.left)
        {
            xMove = (float) (-speed / Math.sqrt(2));
            yMove = (float) (-speed / Math.sqrt(2));
            nextPos.x = (int)(x + xMove + bounds.x); //Adaugam bounds.x pt ca vrem sa aflam pozitia viitoare a dreptunghiului de coliziune,
            nextPos.y = (int)(y + yMove + bounds.y); //nu a imaginii eroului
            return;
        }
        if(km.up && km.right)
        {
            xMove = (float) (speed / Math.sqrt(2));
            yMove = (float) (-speed / Math.sqrt(2));
            nextPos.x = (int)(x + xMove + bounds.x);
            nextPos.y = (int)(y + yMove + bounds.y);
            return;
        }
        if(km.down && km.left)
        {
            xMove = (float) (-speed / Math.sqrt(2));
            yMove = (float) (speed / Math.sqrt(2));
            nextPos.x = (int)(x + xMove + bounds.x);
            nextPos.y = (int)(y + yMove + bounds.y);
            return;
        }
        if(km.down && km.right)
        {
            xMove = (float) (speed / Math.sqrt(2));
            yMove = (float) (speed / Math.sqrt(2));
            nextPos.x = (int)(x + xMove + bounds.x);
            nextPos.y = (int)(y + yMove + bounds.y);
            return;
        }
        if(km.left)
        {
            xMove = -speed;
            yMove = 0;
            nextPos.x = (int)(x + xMove + bounds.x);
            nextPos.y = (int)(y + yMove + bounds.y);
            return;
        }
        if(km.right)
        {
            xMove = speed;
            yMove = 0;
            nextPos.x = (int)(x + xMove + bounds.x);
            nextPos.y = (int)(y + yMove + bounds.y);
            return;
        }
        if(km.up)
        {
            xMove = 0;
            yMove = -speed;
            nextPos.x = (int)(x + xMove + bounds.x);
            nextPos.y = (int)(y + yMove + bounds.y);
            return;
        }
        if(km.down)
        {
            xMove = 0;
            yMove = speed;
            nextPos.x = (int)(x + xMove + bounds.x);
            nextPos.y = (int)(y + yMove + bounds.y);
            return;
        }
        if(km.space && km.spaceReleased == true) //km.spaceReleased == true ne indica faptul ca putem ataca din nou
        {
            animation.ongoingAttackAnimation = true;
            km.spaceReleased = false;
        }
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
                // Verificam daca trebuie sa facem tranzitia catre o alta scena (daca exista coliziune cu pietris, apelam changeScene() unde se va verifica daca eroul a iesit de pe harta)
                if((refLink.GetMap().GetTileBackLayer(i,j).GetId() == 1 && Collision.CollisionDetection(nextPos, new Rectangle(i*32, j*32, 32, 32))))
                {
                    refLink.GetMap().changeScene();
                }

                // Verificam coliziunea intre urmatoarea pozitie a dreptunghiului de coliziune al eroului si dreptunghiul de coliziune al tile-urilor.
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
        \brief Verificam daca exista coliziuni intre erou si obiectele de tip Item. Vom folosi harta din RefLinks
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

    /*! \fn public void DoAction()
        \brief Verificam apasarea unei taste in apropierea unui Item. In cazul in care s-a intamplat acest lucru, executam DoAction()
                pt item-ul respectiv. Fiecare tip de Item concret isi defineste propria actiune.
     */
    @Override
    public void DoAction()
    {
        for(Item i : refLink.GetMap().getMapPopulation().getItems())
        {
            if(refLink.GetKeyManager().e && Collision.CollisionDetection(nextPos, new Rectangle((int)i.GetX() + i.bounds.x, (int)i.GetY() + i.bounds.y, i.bounds.width, i.bounds.height)))
            {
                i.DoAction();
                return;
            }
        }
    }

    /*! \fn public int setAnimationID()
        \brief Seteaza id-ul animatiei (pt a putea fi selectata animatia corecta) in functie de apasarea/eliberarea tastelor pe/de pe tastatura
     */
    public int setAnimationID()
    {
        int animID = AnimationList.heroIdleDown.ordinal(); // implicit, la pornirea jocului, "animatia" selectata va fi idle-down.

        if(animation.ongoingAttackAnimation == false)
        {
            if(GetXMove() == 0 && GetYMove() == 0) // daca eroul e stationat, trebuie sa-i atribuim o imagine idle corespunzatoare directiei in care s-a oprit
            {
                // trebuie sa aflam care este ultima tasta eliberata (released) pt a sti care imagine idle trebuie sa o atribuim eroului.
                switch(refLink.GetKeyManager().lastKeyReleased)
                {
                    case KeyEvent.VK_W: { animID = AnimationList.heroIdleUp.ordinal(); break; }
                    case KeyEvent.VK_A: { animID = AnimationList.heroIdleLeft.ordinal(); break; }
                    case KeyEvent.VK_S: { animID = AnimationList.heroIdleDown.ordinal(); break; }
                    case KeyEvent.VK_D: { animID = AnimationList.heroIdleRight.ordinal(); break; }
                    default: break;
                }
            }
            else
            {
                if (refLink.GetKeyManager().left == true) {
                    animID = AnimationList.heroWalkLeft.ordinal();
                }
                if (refLink.GetKeyManager().right == true) {
                    animID = AnimationList.heroWalkRight.ordinal();
                }
                if (refLink.GetKeyManager().up == true) {
                    animID = AnimationList.heroWalkUp.ordinal();
                }
                if (refLink.GetKeyManager().down == true) {
                    animID = AnimationList.heroWalkDown.ordinal();
                }
            }
        }
        else
        {
            // Indiferent daca eroul e stationat sau nu, apasarea tastei Space declanseaza atacul
            // Selectam in ce directie se realizeaza atacul in functie de ultima tasta apasata
            switch(refLink.GetKeyManager().lastKeyPressed)
            {
                case KeyEvent.VK_W: { animID = AnimationList.heroAttackUp.ordinal(); break; }
                case KeyEvent.VK_A: { animID = AnimationList.heroAttackLeft.ordinal(); break; }
                case KeyEvent.VK_S: { animID = AnimationList.heroAttackDown.ordinal(); break; }
                case KeyEvent.VK_D: { animID = AnimationList.heroAttackRight.ordinal(); break; }
                default: break;
            }
        }

        return animID;
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);

            ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune. Altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}
