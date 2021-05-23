package GreedIsland.Items;

import GreedIsland.Animations.AnimationList;
import GreedIsland.Animations.HeroAnimation;
import GreedIsland.Collision.Collision;
import GreedIsland.Graphics.Assets;
import GreedIsland.Input.KeyManager;
import GreedIsland.Maps.Map;
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
    private static Hero heroInstance;   /*!< Vom face aceasta clasa sa fie Singleton. */
    private BufferedImage image;        /*!< Referinta catre imaginea curenta a eroului. */

    private int attackRange;            /*!< Range-ul atacului eroului. */
    private boolean canAttack;          /*!< Flag care ne indica daca eroul poate ataca din nou (nu am vrea ca eroul sa spammeze atacul. */
    private int restingTimeAfterAttack; /*!< Cat timp trebuie sa se "odihneasca" eroul dupa ce ataca o data. */
    private int restingTimeCounter;     /*!< Contorizam cat timp a trecut de la ultimul atac al eroului. */
    private int exitHousePosY;          /*!< Pozitia pe axa y unde vom plasa eroul la iesirea dintr-o casa; poz X va fi aceeasi. */
    private int MAX_LIFE;               /*!< Viata maxima a eroului. Va fi diferita in functie de nivel. */

    public boolean hasKey;              /*!< Flag care ne indica daca eroul detine sau nu cheia pentru a deschide casa cu usi inchise. */
    public boolean hasTreasureLocation; /*!< Flag care ne indica daca eroul detine indiciul despre locatia comorii. Aceasta este necesara pt incheierea nivelului. */
    public boolean hasTreasure;         /*!< Flag care ne indica daca eroul a gasit comoara sau nu. */
    public int nrEnemiesKilled;         /*!< Numarul de inamici omorati de erou. Cand nrEnemiesKilled == NR_ENEMIES, jucatorul va avea posibilitatea de a termina nivelul. */
    public int nrChestsCollected;       /*!< Numarul de chesturi colectate de erou. */


 // ################################################################################################# //

    /*! \fn private Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    private Hero(RefLinks refLink, float x, float y)
    {
        // Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        // Seteaza imaginea de start a eroului
        image = Assets.heroRight;

        // Stabileste pozitia relativa si dimensiunea dreptunghiului de coliziune in starea implicita (normala)
        normalBounds.x = 8;
        normalBounds.y = 8;
        normalBounds.width = 48;
        normalBounds.height = 48;
        // Stabileste pozitia relativa si dimensiunea dreptunghiului de coliziune in starea de atac
        attackBounds.x = 8;
        attackBounds.y = 8;
        attackBounds.width = 48;
        attackBounds.height = 48;

        // Movement and animation
        animation = new HeroAnimation();
        nextPos = new Rectangle(0, 0, normalBounds.width, normalBounds.height);

        // Parameters of the hero
        life = 5;                           // Viata initiala a inamicului
        attackRange = 15;                   // Range-ul atacului
        restingTimeAfterAttack = 2 * 60;    // Cat timp trebuie sa se odihneasca eroul dupa ce ataca
        nrChestsCollected = 0;              // Numarul de chesturi colectate de erou
        nrEnemiesKilled = 0;                // Numarul curent de inamici omorati de erou
        MAX_LIFE = 5;                       // Nr maxim de puncte de viata pe care le poate avea eroul.

        // Auxiliary variables
        restingTimeCounter = 0;
        canAttack = true;
        exitHousePosY = 14*16; //avand in vedere ca nivelul 1 va incepe in interiorul unei case, trebuie sa stim care e pozitia eroului cand iesim din acea casa
        hasKey = false;
        hasTreasureLocation = false;
        hasTreasure = false;

        // Health bar
        healthBar = new Rectangle(0,0,0,0);

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
        if(!WillCollideWithTiles() && !WillCollideWithItems() && !WillCollideWithEnemies())
        {
            /// Actualizeaza  pozitia
            Move();
            /// Actualizeaza id-ul animatiei pe care urmeaza sa o "desenam" pe ecran
            animation.setAnimID(setAnimationID());
            /// Actualizeaza imaginile corespunzatoare animatiilor
            this.image = animation.playAnimation(refLink);

            /// Daca suntem la mijlocul animatiei de atac si exista coliziune intre atacul eroului si vreun inamic, atunci inamicul e lovit.
            if(animation.canInflictDamageToEnemies)
            {
                checkAndAttackEnemies();
                animation.canInflictDamageToEnemies = false;
            }

       }

        // Calculam pozitia health bar-ului (trebuie sa ramana mereu deasupra capului eroului)
        healthBar.x = (int)(GetX() + bounds.x);
        healthBar.y = (int)(GetY());
        healthBar.width = GetLife() * bounds.width / 5; //Fiecare viata reprezinta o treime din healthBar
        healthBar.height = 8;

        DoAction();
        checkDeadEnemies();

    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            /// Implicit eroul  nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

        if(canAttack == false) // Daca eroul inca nu poate ataca
        {
            restingTimeCounter++;
            if(restingTimeCounter >= restingTimeAfterAttack)
            {
                restingTimeCounter = 0;
                canAttack = true;
            }
        }

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

        if(km.space && canAttack)
        {
            animation.ongoingAttackAnimation = true;
            canAttack = false;
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

                // Verificam daca trebuie sa facem tranzitia catre interiorul casei (daca exista coliziune cu usa casei din exterior si e apasata tasta e)
                if(refLink.GetKeyManager().e && (refLink.GetMap().GetTileFrontLayer(i,j).GetId() == 61 || refLink.GetMap().GetTileFrontLayer(i,j).GetId() == 60) && Collision.CollisionDetection(nextPos, new Rectangle(i*32, j*32, 32, 32)))
                {
                    refLink.GetMap().enterHouse(refLink.GetMap().GetTileFrontLayer(i,j).GetId());
                }

                // Verificam daca trebuie sa facem tranzitia catre exteriorul casei (daca exista coliziune cu usa casei din interior si e apasata tasta e)
                if(refLink.GetKeyManager().e && refLink.GetMap().GetTileFrontLayer(i,j).GetId() == 11 && Collision.CollisionDetection(nextPos, new Rectangle(i*32, j*32, 32, 32)))
                {
                    refLink.GetMap().exitHouse();
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

    /*! \fn public boolean WillCollideWithEnemies()
        \brief Verificam daca exista coliziune intre erou si inamici
     */
    public boolean WillCollideWithEnemies()
    {
        for(Enemy enemy : refLink.GetMap().getMapPopulation().getEnemies())
        {
            if(Collision.CollisionDetection(nextPos, new Rectangle((int)enemy.GetX() + enemy.smallerBounds.x, (int)enemy.GetY() + enemy.smallerBounds.y, enemy.smallerBounds.width, enemy.smallerBounds.height)))
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

        // Desenam health bar-ul pt fiecare inamic
        switch(GetLife())
        {
            case 5: { g.setColor(new Color(0, 90, 50)); break; }
            case 4: { g.setColor(new Color(0,250,0)); break; }
            case 3: { g.setColor(new Color(200, 90, 0)); break; }
            case 2: { g.setColor(new Color(200,150,0)); break; }
            case 1: { g.setColor(new Color(150, 20, 20)); break; }
            default: g.setColor(Color.white);
        }
        g.fillRect(healthBar.x, healthBar.y, healthBar.width, healthBar.height);

            ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune. Altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }

    /*! \fn public void setAttackBoundsForHero(Rectangle attackBoundsForHero)
        \brief In aceasta functie setam attackBounds pt hero. O vom folosi in clasa HeroAnimation pt a "mari" dreptunghiul de coliziune
        in directia in care ataca eroul.

        \param attackBoundsForHero Dreptunghiul de coliziune al eroului
     */
    public void setAttackBoundsForHero(Rectangle attackBoundsForHero)
    {
        this.attackBounds.x = attackBoundsForHero.x;
        this.attackBounds.y = attackBoundsForHero.y;
        this.attackBounds.width = attackBoundsForHero.width;
        this.attackBounds.height = attackBoundsForHero.height;
    }

    /*! \fn public Rectangle getNormalBoundsForHero()
        \brief Returnam variabila "normalBounds" a eroului
     */
    public Rectangle getNormalBoundsForHero() { return normalBounds; }

    /*! \fn public int getAttackRange()
        \brief Returnam variabila "attackRange" a eroului
     */
    public int getAttackRange() { return attackRange; }

    /*! \fn public void checkAndAttackEnemies()
        \brief In aceasta functie vom verifica daca exista coliziune cu vreun inamic in timpul atacului si, daca da, vom scadea punctele
        de viata ale inamicului
     */
    public void checkAndAttackEnemies()
    {
        for(Enemy enemy : refLink.GetMap().getMapPopulation().getEnemies())
        {
            if(Collision.CollisionDetection(new Rectangle((int)(this.GetX() + this.attackBounds.x), (int)(this.GetY() + this.attackBounds.y), this.attackBounds.width, this.attackBounds.height), new Rectangle((int)enemy.GetX() + enemy.bounds.x, (int)enemy.GetY() + enemy.bounds.y, enemy.bounds.width, enemy.bounds.height)))
            {
                enemy.life--;
                enemy.isStunned = true;
            }

        }
    }

    /*! \fn public void checkDeadEnemies()
        \brief Aceasta functie verifica daca exista inamici morti si, in caz afirmativ, acestia vor fi scosi din lista cu inamici ai scenei curente
     */
    public void checkDeadEnemies()
    {
        for(Enemy e : refLink.GetMap().getMapPopulation().getEnemies())
        {
            if(e.getAnimation().isDead)
            {
                e.Die();
                return;
            }
        }
    }

    /*! \fn public int getExitHousePosY()
        \brief Returnam exitHousePosY.
     */
    public int getExitHousePosY() { return exitHousePosY; }

    /*! \fn public void setExitHousePosY(int y)
        \brief Setam exitHousePosY la valoarea parametrului y
     */
    public void setExitHousePosY(int y) { exitHousePosY = y; }

    /*! \fn public int getMaxLife()
        \brief Returnam MAX_LIFE.
     */
    public int getMaxLife() { return MAX_LIFE; }

}
