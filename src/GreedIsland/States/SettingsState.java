package GreedIsland.States;

import GreedIsland.RefLinks;

import java.awt.*;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State {

    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SettingsState(RefLinks refLink){

            /// Apel al constructorului clasei de baza.
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update(){
        if(refLink.GetKeyManager().m)
        {
            State.SetState(refLink.GetGame().getMenuState());
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g){
        g.setFont(new Font("TimesRoman", Font.BOLD, 22));
        g.setColor(Color.BLACK);
        g.drawString("SETTINGS", 340, 40);
        g.drawString("W/A/S/D   -> Move up/left/down/right", 80, 200);
        g.drawString("E              -> Interact with objects (chests, doors)", 80, 230);
        g.drawString("SPACE     -> Attack", 80, 260);
        g.drawString("M             -> Return to menu", 80, 290);
        g.drawString("Return to menu -> press M", 80, 380);
    }
}
