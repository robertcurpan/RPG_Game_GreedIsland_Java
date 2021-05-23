package GreedIsland.States;

import GreedIsland.RefLinks;

import java.awt.*;

/*! \class public class WinGameState extends State
    \brief Implementeaza notiunea de settings pentru joc.

 */
public class WinGameState extends State {

    /*! \fn public WinGameState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public WinGameState(RefLinks refLink){

        /// Apel al constructorului clasei de baza.
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea.
     */
    @Override
    public void Update(){
        if(refLink.GetKeyManager().m)
        {
            State.SetState(refLink.GetGame().getMenuState());
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g){
        g.setFont(new Font("TimesRoman", Font.BOLD, 32));
        g.setColor(Color.GREEN);
        g.drawString("CONGRATULATIONS !!!", 300, 300);
        g.setFont(new Font("TimesRoman", Font.BOLD, 24));
        g.drawString("You won!", 300, 350);

        g.setColor(Color.BLACK);
        g.drawString("Return to menu -> press M", 80, 580);
    }
}
