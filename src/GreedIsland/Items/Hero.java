package GreedIsland.Items;

import GreedIsland.Graphics.Assets;
import GreedIsland.Input.KeyManager;
import GreedIsland.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/

 // ################################################################################################# //

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y)
    {
            /// Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
            /// Seteaza imaginea de start a eroului
        image = Assets.heroRight;

            /// Stabileste pozitia relativa si dimensiunea dreptunghiului de coliziune in starea implicita (normala)
        normalBounds.x = 0;
        normalBounds.y = 0;
        normalBounds.width = 64;
        normalBounds.height = 64;
            /// Stabileste pozitia relativa si dimensiunea dreptunghiului de coliziune in starea de atac
        attackBounds.x = 0;
        attackBounds.y = 0;
        attackBounds.width = 64;
        attackBounds.height = 64;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
            /// Verifica daca a fost apasata o tasta
        GetInput();
            /// Actualizeaza  pozitia
        Move();
            /// Actualizeaza imaginea
        if(refLink.GetKeyManager().left == true)
        {
            image = Assets.heroLeft;
        }
        if(refLink.GetKeyManager().right == true)
        {
            image = Assets.heroRight;
        }
        if(refLink.GetKeyManager().up == true)
        {
            image = Assets.heroUp;
        }
        if(refLink.GetKeyManager().down == true)
        {
            image = Assets.heroDown;
        }
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
            return;
        }
        if(km.up && km.right)
        {
            xMove = (float) (speed / Math.sqrt(2));
            yMove = (float) (-speed / Math.sqrt(2));
            return;
        }
        if(km.down && km.left)
        {
            xMove = (float) (-speed / Math.sqrt(2));
            yMove = (float) (speed / Math.sqrt(2));
            return;
        }
        if(km.down && km.right)
        {
            xMove = (float) (speed / Math.sqrt(2));
            yMove = (float) (speed / Math.sqrt(2));
            return;
        }
        if(km.left)
        {
            xMove = -speed;
            yMove = 0;
            return;
        }
        if(km.right)
        {
            xMove = speed;
            yMove = 0;
            return;
        }
        if(km.up)
        {
            xMove = 0;
            yMove = -speed;
            return;
        }
        if(km.down)
        {
            xMove = 0;
            yMove = speed;
            return;
        }
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
