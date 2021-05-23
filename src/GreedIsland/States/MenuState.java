package GreedIsland.States;

import GreedIsland.Items.Hero;
import GreedIsland.RefLinks;

import java.awt.*;
import java.io.FileNotFoundException;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State {

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink){

            /// Apel al constructorului clasei de baza.
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {
        if(refLink.GetKeyManager().esc)
        {
            System.exit(-1);
        }
        if(refLink.GetKeyManager().key1)
        {
            refLink.GetGame().level = 1;
            Hero.resetHero();
            try
            {
                refLink.GetGame().setPlayState(new PlayState(refLink));
                State.SetState(refLink.GetGame().getPlayState());
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("New playState - file not found!");
            }
        }
        if(refLink.GetKeyManager().key2)
        {
            refLink.GetGame().level = 2;
            Hero.resetHero();
            try
            {
                refLink.GetGame().setPlayState(new PlayState(refLink));
                State.SetState(refLink.GetGame().getPlayState());
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("New playState - file not found!");
            }
        }
        if(refLink.GetKeyManager().l)
        {
            State.SetState(refLink.GetGame().getPlayState());
        }
        if(refLink.GetKeyManager().s)
        {
            State.SetState(refLink.GetGame().getSettingsState());
        }
        if(refLink.GetKeyManager().a)
        {
            State.SetState(refLink.GetGame().getAboutState());
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g){
        g.setFont(new Font("TimesRoman", Font.BOLD, 26));
        g.setColor(Color.black);
        g.drawString("MENU", 380, 40);
        g.drawString("Start Level 1 -> press 1", 80, 200);
        g.drawString("Start Level 2 -> press 2", 80, 240);
        g.drawString("Load Game -> press L", 80, 280);
        g.drawString("Settings -> press S", 80, 360);
        g.drawString("About -> press A", 80, 400);
        g.drawString("Exit game -> press ESC", 80, 480);
    }
}
