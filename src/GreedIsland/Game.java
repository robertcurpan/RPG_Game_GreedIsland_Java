package GreedIsland;

import GreedIsland.GameWindow.GameWindow;
import GreedIsland.Graphics.Assets;
import GreedIsland.Input.KeyManager;
import GreedIsland.Items.Hero;
import GreedIsland.States.*;
import GreedIsland.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;

/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */
public class Game implements Runnable {
    private GameWindow      wnd;        // Fereastra in care se va desena tabla jocului
    private boolean         runState;   // Flag ce retine starea firului de executie
    private Thread          gameThread; // Referinta catre thread-ul de update si draw al ferestrei
    private BufferStrategy bs;          // Referinta catre un mecanism cu care se organizeaza memoria complexa pt un canvas
    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************

    private Graphics        g;          // Referinta catre un context grafic

        /// Available states
    private State playState;        // Referinta catre joc
    private State menuState;        // Referinta catre menu
    private State settingsState;    // Referinta catre setari
    private State aboutState;       // Referinta catre about
    private State winGameState;     // Referinta catre winGame
    private State loseGameState;    // Referinta catre loseGame
    private KeyManager keyManager;  // Referinta catre obiectul care gestioneaza intrarile din partea utilizatorului
    private RefLinks refLink;       // Referinta catre un obiect a carui sarcina este doar de a retine diverse referinte pentru a fi usor accesibile

    public int level;               // Nivelul care va fi selectat
    public boolean gameWon;         // Flag care ne spune daca jocul a fost castigat
    public boolean gameLost;        // Flag care ne spune daca jocul a fost pierdut
    private Database database;      // Baza de date in care vom scrie o "statistica" a jocului (atunci cand pierdem sau castigam)

    /*! \fn public Game(String title, int width, int height)
        \brief Constructor de initializare al clasei Game.

        Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
        acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

        \param title Titlul ferestrei.
        \param width Latimea ferestrei in pixeli.
        \param height Inaltimea ferestrei in pixeli.
     */
    public Game(String title, int width, int height){
            /// Obiectul GameWindow este creat insa fereastra nu este construita
            /// Acest lucru va fi realizat in metoda init() prin apelul
            /// functiei BuildGameWindow();
        wnd = new GameWindow(title, width, height);
            /// Resetarea flagului runState ce indica starea firului de executie (started/stopped)
        runState = false;
            /// Construirea obiectului de gestiune a evenimentelor de tastatura
        keyManager = new KeyManager();
            /// Initial jocul nu este castigat
        gameWon = false;
            /// Initial jocul nu este pierdut
        gameLost = false;
    }

    /*! \fn private void InitGame()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.
     */
    private void InitGame() throws FileNotFoundException {
            /// Este construita fereastra grafica
        wnd.BuildGameWindow();
            /// Se ataseaza ferestrei managerul de tastatura pt a primi evenimentele furnizate de fereastra
        wnd.GetWndFrame().addKeyListener(keyManager);
            /// Se incarca toate elementele grafice (dale)
        Assets.Init();
            /// Se construieste obiectul de tip shortcut ce va retine o serie de referinte catre elementele importante din program.
        refLink = new RefLinks(this);
            /// Definirea starilor programului
        //playState       = new PlayState(refLink);
        menuState       = new MenuState(refLink);
        settingsState   = new SettingsState(refLink);
        aboutState      = new AboutState(refLink);
        winGameState    = new WinGameState(refLink);
        loseGameState   = new LoseGameState(refLink);

            /// Cream baza de date
        database = new Database(refLink);

            /// Seteaza starea implicita cu care va fi lansat programul in executie
        State.SetState(menuState);
    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run(){
            /// Initializeaza obiectul game
        try {
            InitGame();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long oldTime = System.nanoTime();   // Retine timpul in nanosecunde aferent frame-ului anterior
        long currentTime;                   // Retine timpul in nanosecunde aferent frame-ului curent

            /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
            /// sau mai bine spus de 60 ori pe secunda.
        final int framesPerSecond   = 60;   // Constanta intreaga initializeaza cu numarul de frame-uri pe secunda
        final double timeFrame      = 1000000000/framesPerSecond;   // Durata unui frame in nanosecunde

            /// Atat timp cat thread-ul este pornit Update() & Draw()
        while(runState == true){
                /// Se obtine timpul curent
            currentTime = System.nanoTime();
                /// Daca diferenta de timp dintre currentTime si oldTime e mai mare decat 16.6 ms
            if((currentTime - oldTime) > timeFrame){
                    /// Actualizeaza pozitiile elementelor
                try {
                    Update();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /// Deseneaza elementele grafice in fereastra
                Draw();
                oldTime = currentTime;
            }
        }
    }

    /*! \fn public synchronized void start()
        \brief Creaza si porneste firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame(){
        if(runState == false){
                /// Se actualizeaza flagul de stare al thread-ului
            runState = true;
                /// Se construieste thread-ul avand ca parametru obiectul Game. De retinut faptul ca Game class
                /// implementeaza interfata Runnable. Thread-ul creat va executa functia run() suprascrisa in clasa Game
            gameThread = new Thread(this);
                /// Thread-ul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
        else{
                /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StopGame(){
        if(runState == true){
                /// Actualizare stare thread
            runState = false;
                /// Metoda join() arunca exceptii, motiv pt care trebuie incadrata intr-un block try-catch
            try{
                    /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                    /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch (InterruptedException ex){
                    /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pt depanare
                ex.printStackTrace();
            }
        }
        else{
                /// Thread-ul este oprit deja
            return;
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Update() throws FileNotFoundException
    {
            ///Determina starea tastelor
        keyManager.Update();
            /// Trebuie obtinuta starea curenta pt care urmeaza a se actualiza starea. Atentie: trebuie sa fie diferita de null!
        if(State.GetState() != null)
        {
                /// Actualizez starea curenta a jocului, daca exista.
            State.GetState().Update();
        }
            /// Updatam baza de date (doar atunci cand pierdem sau castigam jocul)
        if(gameWon || gameLost)
            database.updateDatabase();

    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Draw()
    {
            /// Returnez bufferStrategy pentru canvas-ul existent
        bs = wnd.GetCanvas().getBufferStrategy();
            /// Verific daca buffer strategy a fost construit sau nu
        if(bs == null)
        {
                /// Se executa doar la primul apel al metodei Draw()
            try
            {
                    /// Se construieste triplul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch(Exception e)
            {
                    /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }

            /// Se obtine contextul grafic curent in care se poate desena
        g = bs.getDrawGraphics();
            /// Se sterge ce era inainte
        g.clearRect(0,0,wnd.GetWndWidth(), wnd.GetWndHeight());

        // Operatia de desenare
            /// Trebuie obtinuta starea curenta pentru care urmeaza a se actualiza starea. Atentie: starea trebuie sa fie diferita de null!
        if(State.GetState() != null)
        {
                ///Actualizez starea curenta a jocului daca exista
            State.GetState().Draw(g);
        }
        // End operatie de desenare

            /// Se afiseaza pe ecran
        bs.show();

            /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
            /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }

    /*! \fn public int GetWidth()
        \brief Returneaza latimea ferestrei
     */
    public int GetWidth()
    {
        return wnd.GetWndWidth();
    }

    /*! \fn public int GetHeight()
        \brief Returneaza inaltimea ferestrei
     */
    public int GetHeight()
    {
        return wnd.GetWndHeight();
    }

    /*! \fn public KeyManager GetKeyManager()
        \brief Returneaza obiectul care gestioneaza tastatura.
     */
    public KeyManager GetKeyManager()
    {
        return keyManager;
    }

    /*! \fn public Graphics getGraphics()
        \brief Returnam contextul grafic g
     */
    public Graphics getGraphics() { return g; }

    /*! \fn public State getPlayState()
        \brief Returnam playState.
     */
    public State getPlayState() { return playState; }

    /*! \fn public State getMenuState()
        \brief Returnam menuState.
     */
    public State getMenuState() { return menuState; }

    /*! \fn public State getSettingsState()
        \brief Returnam settingsState.
     */
    public State getSettingsState() { return settingsState; }

    /*! \fn public State getAboutState()
        \brief Returnam aboutState.
     */
    public State getAboutState() { return aboutState; }

    /*! \fn public State getWinGameState()
        \brief Returnam winGameState.
     */
    public State getWinGameState() { return winGameState; }

    /*! \fn public State getLoseGameState()
        \brief Returnam loseGameState.
     */
    public State getLoseGameState() { return loseGameState; }

    /*! \fn public void setPlayState(State newPlayState)
        \brief Atribuim lui playState referinta newPlayState
     */
    public void setPlayState(State newPlayState) { playState = newPlayState; }

    /*! \fn public Database getDatabase()
        \brief Returnam database
     */
    public Database getDatabase() { return database; }

}
