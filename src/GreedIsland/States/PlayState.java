package GreedIsland.States;

import GreedIsland.Items.ConcreteEnemies.Wolf;
import GreedIsland.Items.Enemy;
import GreedIsland.Items.Hero;
import GreedIsland.Maps.Map;
import GreedIsland.RefLinks;

import java.awt.*;
import java.io.FileNotFoundException;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Hero hero;  // Referinta catre obiectul animat erou (controlat de utilizator)
    private Map map;    // Referinta catre harta curenta

 // ############################################################### //

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink) throws FileNotFoundException
    {
            /// Apel al constructorului clasei de baza
        super(refLink);
            /// Construieste harta jocului
        map = new Map(refLink);
            /// Referinta catre harta construita este setata si in obiectul shortcut pt a fi accesibila si in alte clase ale programului
        refLink.SetMap(map);
            /// Construieste eroul (folosing o functie specifica Singleton)
        if(refLink.GetGame().level == 1)
            hero = Hero.getHeroInstance(refLink, 600, 100);
        if(refLink.GetGame().level == 2)
            hero = Hero.getHeroInstance(refLink, 12*32, 18*32);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update() throws FileNotFoundException
    {
        map.Update();
        hero.Update();

        // Daca eroul a murit intram in menuState
        if(Hero.getHeroInstance(refLink, 0, 0).isDead())
            State.SetState(refLink.GetGame().getLoseGameState());

        // Daca am castigat jocul ne intoarcem in menuState
        if(refLink.GetGame().gameWon)
            State.SetState(refLink.GetGame().getWinGameState());

        if(refLink.GetKeyManager().m)
            State.SetState(refLink.GetGame().getMenuState());
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);
        hero.Draw(g);
    }
}
