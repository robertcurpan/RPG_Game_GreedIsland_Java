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

    private Enemy wolf1; // TODO (de scos de aici dupa terminarea testarii)

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
        hero = Hero.getHeroInstance(refLink, 100, 100);

        // Construim un inamic concret (wolf) pt testare
        wolf1 = new Wolf(refLink, 400, 400); // TODO (de scos de aici dupa terminarea testarii)
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update() throws FileNotFoundException
    {
        map.Update();
        hero.Update();

        wolf1.Update(); // TODO (de scos de aici dupa terminarea testarii)
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

        wolf1.Draw(g); // TODO (de scos de aici dupa terminarea testarii)
    }
}
