package GreedIsland.Items.ConcreteEnemies;

import GreedIsland.Animations.AnimationList;
import GreedIsland.Animations.WolfAnimation;
import GreedIsland.Graphics.Assets;
import GreedIsland.Items.Enemy;
import GreedIsland.RefLinks;

import java.awt.*;
import java.io.FileNotFoundException;


/*! \class public class Wolf extends Enemy
    \brief Implementeaza inamicul concret Wolf
 */
public class Wolf extends Enemy
{
    public Wolf(RefLinks refLink, float x, float y)
    {
        // Apel al constructorului clasei de baza
        super(refLink, x, y);
        // Seteaza imaginea de start a inamicului (wolf)
        image = Assets.wolfDown;
        // Instantiaza campul animation
        animation = new WolfAnimation();
        // Dam viteza acestui inamic
        SetSpeed(1.8f);
    }

    @Override
    public void Update() throws FileNotFoundException
    {
        SetXMove(speed);
        Move();
        animation.setAnimID(setAnimationID());
        this.image = animation.playAnimation();
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }


    /*! \fn public int setAnimationID()
        \brief Metoda ce selecteaza imaginile corespunzatoare pt ca inamicul nostru sa fie animat frumos si corect.
     */
    @Override
    public int setAnimationID()
    {
        return AnimationList.wolfWalkRight.ordinal();
    }

    @Override
    public void DoAction() { /* nothing here */ }

}
