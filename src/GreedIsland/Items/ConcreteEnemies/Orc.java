package GreedIsland.Items.ConcreteEnemies;

import GreedIsland.Animations.AnimationList;
import GreedIsland.Animations.OrcAnimation;
import GreedIsland.Graphics.Assets;
import GreedIsland.Items.Enemy;
import GreedIsland.Items.Hero;
import GreedIsland.RefLinks;

import java.awt.*;
import java.io.FileNotFoundException;


/*! \class public class Orc extends Enemy
    \brief Implementeaza inamicul concret Orc
 */
public class Orc extends Enemy
{
    private int stunDuration; // cat timp inamicul va fi "ametit" dupa o lovitura din partea eroului (cand inamicul e ametit -> nu se poate misca)
    private int stunCounter;  // variabila care ne ajuta sa contorizam cat timp a trecut de cand inamicul a fost lovit

    public Orc(RefLinks refLink, float x, float y)
    {
        // Apel al constructorului clasei de baza
        super(refLink, x, y);
        // Seteaza imaginea de start a inamicului (orc)
        image = Assets.orcDown;

        // Animation
        animation = new OrcAnimation();

        // Parameters of the "Orc" enemy
        life = 4;                   // Viata initiala a inamicului
        SetSpeed(1.2f);             // Viteza inamicului de tip Orc
        radiusOfSight = 150;        // Specificam raza vizuala a inamicului de tip Orc
        attackingDistance = 42;     // Specificam attackingDistance -> distanta de la care inamicul isi va incepe animatia de atac si va rani player-ul
        stunDuration = 60 / 2;      // Cat timp inamicul va fi ametit dupa o lovitura din partea eroului (aici 0.5 secunde)

        // Auxiliary variables
        facingDirection = "Down";   // Specificam facingDirection
        isStunned = false;          // Flag care ne indica daca inamicul a fost lovit. In caz afirmativ, acesta va sta pe loc timp de jumatate de secunda (sau o secunda) -> socul loviturii (stunned)
        stunCounter = 0;            // Counter pt timpul scurs de la ultima lovitura a eroului (e updatat doar atat timp cat inamicul e ametit)

        // Health bar
        healthBar = new Rectangle(0,0,0,0);
    }

    @Override
    public int setAnimationID() {
        return 0;
    }

    public int setAttackAnimationID()
    {
        if(facingDirection == "Up")
        {
            if(animation.canAttack)
                return AnimationList.orcAttackUp.ordinal();
            else
                return AnimationList.orcIdleUp.ordinal();
        }
        if(facingDirection == "Down")
        {
            if(animation.canAttack)
                return AnimationList.orcAttackDown.ordinal();
            else
                return AnimationList.orcIdleDown.ordinal();
        }
        if(facingDirection == "Left")
        {
            if(animation.canAttack)
                return AnimationList.orcAttackLeft.ordinal();
            else
                return AnimationList.orcIdleLeft.ordinal();
        }
        if(facingDirection == "Right")
        {
            if(animation.canAttack)
                return AnimationList.orcAttackRight.ordinal();
            else
                return AnimationList.orcIdleRight.ordinal();
        }

        return 0;
    }

    @Override
    public void Update() throws FileNotFoundException
    {
        int dist = enemyMovement(); //aici se seteaza xMove si yMove; vom misca inamicul doar daca nu exista coliziuni in directia indicata de xMove si yMove determinate in aceasta functie

        // Am adaugat acest if pentru a elimina bug-ul in care inamicul nu se despawneaza daca este in coliziune cu un obiect (si viata e 0)
        if(life <= 0 && (WillCollideWithTiles() || WillCollideWithItems() || WillCollideWithHero()))
        {
            bounds.x = (int)((Math.random() + 900) * 30); bounds.y = (int)((Math.random() + 900) * 30); //ii mutam hitbox-ul in afara hartii pt a se anula conditia de coliziune si a se putea executa animatia de moarte
            bounds.width = 0;
        }

        if(!WillCollideWithTiles() && !WillCollideWithItems())
        {
            if(life > 0)
            {
                if(isStunned == false)
                {
                    if(dist < radiusOfSight && dist > attackingDistance)
                        Move();
                    if(dist <= attackingDistance)
                    {
                        animation.setAnimID(setAttackAnimationID());
                    }
                }
                else
                {
                    stunCounter++;
                    if(stunCounter >= stunDuration)
                    {
                        isStunned = false;
                        stunCounter = 0;
                    }
                }

            }
            else // Daca inamicul este mort
            {
                animation.setAnimID(AnimationList.orcDeath.ordinal());
            }

            if(isStunned == false || life <= 0)
                this.image = animation.playAnimation(refLink); //setarea id-ului animatiei de mers se va face tot in enemyMovement()

            if(animation.canInflictDamageToHero == true)
            {
                Hero.getHeroInstance(refLink, 0, 0).SetLife(Hero.getHeroInstance(refLink, 0, 0).GetLife() - 1);
                animation.canInflictDamageToHero = false;
            }

        }
        // Calculam pozitia urmatoare
        nextPos.x = (int)(x + xMove + bounds.x); //Adaugam bounds.x pt ca vrem sa aflam pozitia viitoare a dreptunghiului de coliziune,
        nextPos.y = (int)(y + yMove + bounds.y); //nu a imaginii eroului

        // Calculam pozitia health bar-ului (trebuie sa ramana mereu deasupra capului eroului)
        healthBar.x = (int)(GetX() + bounds.x);
        healthBar.y = (int)(GetY());
        healthBar.width = GetLife() * bounds.width / 4; //Fiecare viata reprezinta un sfert din healthBar
        healthBar.height = 8;

    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);

        // Desenam health bar-ul pt fiecare inamic
        switch(GetLife())
        {
            case 4: { g.setColor(new Color(0, 90, 50)); break; }
            case 3: { g.setColor(new Color(0, 200, 50)); break;}
            case 2: { g.setColor(new Color(200, 150, 0)); break; }
            case 1: { g.setColor(new Color(150, 20, 20)); break; }
            default: g.setColor(Color.white);
        }
        g.fillRect(healthBar.x, healthBar.y, healthBar.width, healthBar.height);

        //g.setColor(Color.red);
        //g.fillRect((int)(x + smallerBounds.x), (int)(y + smallerBounds.y), smallerBounds.width, smallerBounds.height);
    }

    @Override
    public void DoAction() { /* nothing here */ }

    @Override
    public void Die()
    {
        Hero hero = Hero.getHeroInstance(refLink, 0, 0);

        hero.nrEnemiesKilled++;
        refLink.GetMap().score += 20;

        refLink.GetMap().getMapPopulation().getEnemies().remove(this);
    }


    /*  \fn public int enemyMovement()
        \brief In aceasta functie realizam miscarea si animatia inamicului
               Functia returneaza distanta dintre erou si inamic
     */
    @Override
    public int enemyMovement() //returneaza distanta dintre erou si inamic
    {
        Hero hero = Hero.getHeroInstance(refLink, 0, 0);
        int xctrHero = (int)(hero.GetX() + hero.GetWidth()/2);
        int yctrHero = (int)(hero.GetY() + hero.GetHeight()/2);
        int xctrEnemy = (int)(this.GetX() + this.GetWidth()/2);
        int yctrEnemy = (int)(this.GetY() + this.GetHeight()/2);
        double angleInRadians;           // unghiul pe care il face orizontala cu dreapta ce uneste inamicul si eroul (in radiani) -> il vom folosi pt a face miscarea inamicului simetrica (la fel de rapida) in toate partile
        double sinAngle, cosAngle;
        boolean alreadyMoved = false; //acest flag ne va ajuta sa intram pe un singur if in aceeasi rulare a functiei (sa nu ne miscam de 2 ori in acelasi frame)

        int dist = (int)Math.sqrt(Math.pow(xctrEnemy - xctrHero, 2) + Math.pow(yctrEnemy - yctrHero, 2));
        if(dist < radiusOfSight)
        {
            // Caz 1)
            if(xctrEnemy < xctrHero && yctrEnemy >= yctrHero && alreadyMoved == false)
            {
                // Inamic -> cadranul 3
                // Erou -> cadranul 1
                angleInRadians = Math.atan((double)(yctrEnemy - yctrHero)/(xctrEnemy - xctrHero)); // panta dreptei m = (yB-yA)/(xB-xA); unghiul in radiani este angle = arctg(m);
                sinAngle = Math.sin(Math.abs(angleInRadians));  // ma intereseaza doar valoarea sinusului/cosinusului, nu si semnul
                cosAngle = Math.cos(Math.abs(angleInRadians));  // semnul e determinat de cazul in care ne aflam (inamic cadran 3, erou cadran 1)
                SetXMove((float)(speed * cosAngle));
                SetYMove((float)(- speed * sinAngle)); //trebuie ca inamicul sa se miste spre dreapta-sus, deci x creste si y scade

                if(Math.abs(xctrEnemy - xctrHero) > Math.abs(yctrEnemy - yctrHero))
                {
                    animation.setAnimID(AnimationList.orcWalkRight.ordinal());
                    facingDirection = "Right";
                }
                else
                {
                    animation.setAnimID(AnimationList.orcWalkUp.ordinal());
                    facingDirection = "Up";
                }

                alreadyMoved = true;
            }

            // Caz 2)
            if(xctrEnemy > xctrHero && yctrEnemy < yctrHero && alreadyMoved == false)
            {
                // Inamic -> cadranul 1
                // Erou -> cadranul 3
                angleInRadians = Math.atan((double)(yctrEnemy - yctrHero)/(xctrEnemy - xctrHero)); // panta dreptei m = (yB-yA)/(xB-xA); unghiul in radiani este angle = arctg(m);
                sinAngle = Math.sin(Math.abs(angleInRadians));  // ma intereseaza doar valoarea sinusului/cosinusului, nu si semnul
                cosAngle = Math.cos(Math.abs(angleInRadians));  // semnul e determinat de cazul in care ne aflam (inamic cadran 3, erou cadran 1)
                SetXMove((float)(- speed * cosAngle));
                SetYMove((float)(speed * sinAngle));    // trebuie ca inamicul sa se  miste spre stanga-jos, deci x scade si y creste

                if(Math.abs(xctrEnemy - xctrHero) > Math.abs(yctrEnemy - yctrHero))
                {
                    animation.setAnimID(AnimationList.orcWalkLeft.ordinal());
                    facingDirection = "Left";
                }
                else
                {
                    animation.setAnimID(AnimationList.orcWalkDown.ordinal());
                    facingDirection = "Down";
                }

                alreadyMoved = true;
            }

            // Caz 3)
            if(xctrEnemy > xctrHero && yctrEnemy >= yctrHero && alreadyMoved == false)
            {
                // Inamic -> cadranul 4
                // Erou -> cadranul 2
                angleInRadians = Math.atan((double)(yctrEnemy - yctrHero)/(xctrEnemy - xctrHero)); // panta dreptei m = (yB-yA)/(xB-xA); unghiul in radiani este angle = arctg(m);
                sinAngle = Math.sin(Math.abs(angleInRadians));  // ma intereseaza doar valoarea sinusului/cosinusului, nu si semnul
                cosAngle = Math.cos(Math.abs(angleInRadians));  // semnul e determinat de cazul in care ne aflam (inamic cadran 3, erou cadran 1)
                SetXMove((float)(- speed * cosAngle));
                SetYMove((float)(- speed * sinAngle));    // trebuie ca inamicul sa se miste spre stanga-sus, deci x scade si y scade

                if(Math.abs(xctrEnemy - xctrHero) > Math.abs(yctrEnemy - yctrHero))
                {
                    animation.setAnimID(AnimationList.orcWalkLeft.ordinal());
                    facingDirection = "Left";
                }
                else
                {
                    animation.setAnimID(AnimationList.orcWalkUp.ordinal());
                    facingDirection = "Up";
                }

                alreadyMoved = true;
            }

            // Caz 4)
            if(xctrEnemy < xctrHero && yctrEnemy < yctrHero && alreadyMoved == false)
            {
                // Inamic -> cadranul 2
                // Erou -> cadranul 4
                angleInRadians = Math.atan((double)(yctrEnemy - yctrHero)/(xctrEnemy - xctrHero)); // panta dreptei m = (yB-yA)/(xB-xA); unghiul in radiani este angle = arctg(m);
                sinAngle = Math.sin(Math.abs(angleInRadians));  // ma intereseaza doar valoarea sinusului/cosinusului, nu si semnul
                cosAngle = Math.cos(Math.abs(angleInRadians));  // semnul e determinat de cazul in care ne aflam (inamic cadran 3, erou cadran 1)
                SetXMove((float)(speed * cosAngle));
                SetYMove((float)(speed * sinAngle));    // trebuie ca inamicul sa se miste spre dreapta jos, deci x creste si y creste

                if(Math.abs(xctrEnemy - xctrHero) > Math.abs(yctrEnemy - yctrHero))
                {
                    animation.setAnimID(AnimationList.orcWalkRight.ordinal());
                    facingDirection = "Right";
                }
                else
                {
                    animation.setAnimID(AnimationList.orcWalkDown.ordinal());
                    facingDirection = "Down";
                }

                alreadyMoved = true;
            }

            // Caz 5)
            if(xctrEnemy == xctrHero) // luam acest caz separat deoarece nu putem calcula panta unei drepte verticale (ne da infinit - numitor 0 -> exceptie)
            {
                // Inamicul si eroul se afla pe aceeasi verticala -> inamicul se va misca doar pe verticala (nu avem viteza pe orizontala)
                SetXMove(0);
                SetYMove(0); //yMove va fi schimbat in urmatoarele 2 if-uri daca pozitiile eroului si a inamicului nu coincid

                if(yctrEnemy < yctrHero)
                {
                    SetYMove(speed);    // trebuie ca inamicul sa se deplaseze in jos, spre erou
                    animation.setAnimID(AnimationList.orcWalkDown.ordinal());
                    facingDirection = "Down";
                }
                if(yctrEnemy > yctrHero)
                {
                    SetYMove(-speed);   // trebuie ca inamicul sa se deplaseze in sus, spre erou
                    animation.setAnimID(AnimationList.orcWalkUp.ordinal());
                    facingDirection = "Up";
                }

                alreadyMoved = true;
            }

            // Daca inamicul se suprapune cu nou, atunci animatia lui de mers se va opri
            if(GetXMove() == 0 && GetYMove() == 0)
            {
                animation.setAnimID(AnimationList.orcIdleDown.ordinal());
                facingDirection = "None";
            }
        }
        else
        {
            // daca eroul nu se afla in raza vizuala a inamicului, acesta din urma se va opri din miscare
            SetXMove(0);
            SetYMove(0);
            animation.setAnimID(AnimationList.orcIdleDown.ordinal());
            facingDirection = "Down";
        }

        return dist;

    }
}

