package GreedIsland.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*! \class public class KeyManager implements KeyListener
    \brief Gestioneaza intrarea (input-ul) de tastatura.

    Clasa citeste daca au fost apasata o tasta, stabiliteste ce tasta a fost actionata si seteaza corespunzator un flag.
    In program trebuie sa se tina cont de flagul aferent tastei de interes. Daca flagul respectiv este true inseamna
    ca tasta respectiva a fost apasata si false nu a fost apasata.
 */
public class KeyManager implements KeyListener {
    private boolean[] keys;         // Vector de flaguri pt toate tastele. Tastele vor fi regasite dupa cod [0-255]
    public boolean up;              // Flag pentru tasta "sus" apasata.
    public boolean down;            // Flag pentru tasta "jos" apasata.
    public boolean left;            // Flag pentru tasta "stanga" apasata.
    public boolean right;           // Flag pentru tasta "dreapta" apasata.
    public boolean e;               // Flag pentru apasarea tastei "E"
    public boolean space;           // Flag pentru apasarea tastei "Space"
    public boolean key1;            // Flag pentru apasarea tastei "1"
    public boolean key2;            // Flag pentru apasarea tastei "2"
    public boolean m;               // Flag pentru apasarea tastei "m"
    public boolean l;               // Flag pentru apasarea tastei "l"
    public boolean s;               // Flag pentru apasarea tastei "s"
    public boolean a;               // Flag pentru apasarea tastei "a"
    public boolean esc;             // Flag pentru apasarea tastei "esc"
    public int lastKeyPressed;      // Ultima tasta apasata
    public int lastKeyReleased;     // Ultima tasta eliberata

    /*! \fn public KeyManager()
        \brief Constructorul clasei.
     */
    public KeyManager(){
            /// Constructie vector de flaguri aferente tastelor
        keys = new boolean[256];
    }

    public void Update(){
        up      = keys[KeyEvent.VK_W];
        down    = keys[KeyEvent.VK_S];
        left    = keys[KeyEvent.VK_A];
        right   = keys[KeyEvent.VK_D];
        e       = keys[KeyEvent.VK_E];
        space   = keys[KeyEvent.VK_SPACE];
        key1    = keys[KeyEvent.VK_1];
        key2    = keys[KeyEvent.VK_2];
        m       = keys[KeyEvent.VK_M];
        l       = keys[KeyEvent.VK_L];
        s       = keys[KeyEvent.VK_S];
        a       = keys[KeyEvent.VK_A];
        esc     = keys[KeyEvent.VK_ESCAPE];
    }

    /*! \fn public void keyPressed(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de tasta apasata este generat.

         \param e obiectul eveniment de tastatura.
     */
    @Override
    public void keyPressed(KeyEvent e){
            /// Se retine in vectorul de flaguri ca o tasta a fost apasata.
        keys[e.getKeyCode()] = true;
        if(e.getKeyCode() != KeyEvent.VK_SPACE)    // nu vom salva in lastKeyPressed apasarea tastei space
            lastKeyPressed = e.getKeyCode();
    }

    /*! \fn public void keyReleased(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de tasta eliberata este generat.

         \param e obiectul eveniment de tastatura.
     */
    @Override
    public void keyReleased(KeyEvent e){
            /// Se retine in vectorul de flaguri ca o tasta a fost eliberata.
        keys[e.getKeyCode()] = false;
        if(e.getKeyCode() != KeyEvent.VK_SPACE)
            lastKeyReleased = e.getKeyCode();

    }

    /*! \fn public void keyTyped(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand o tasta a fost apasata si eliberata.
        Momentan aceasta functie nu este utila in program.
     */
    @Override
    public void keyTyped(KeyEvent e){

    }
}
