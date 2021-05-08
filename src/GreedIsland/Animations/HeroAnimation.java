package GreedIsland.Animations;

import GreedIsland.Graphics.Assets;
import GreedIsland.Graphics.ImageLoader;
import GreedIsland.Graphics.SpriteSheetCharacters;
import GreedIsland.RefLinks;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class HeroAnimation extends Animation
{
    private int imgPozX, imgPozY;

    public HeroAnimation()
    {
        characterSheet = new SpriteSheetCharacters(ImageLoader.LoadImage("/textures/Characters_sprites.png"));
        setAnimSpeed((1.5 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
        ongoingAttackAnimation = false;
        attackAnimationDuration = 0;
    }

    /*! \fn public BufferedImage playAnimation()
        \brief Realizeaza animatia propriu-zisa (selecteaza imaginile corespunzatoare din spritesheet la momentele de timp potrivite (dictate de
                viteza animatiei (animSpeed) si de nr de frame-uri (imagini) ale fiecarui tip de animatie)
     */
    @Override
    public BufferedImage playAnimation()
    {
        BufferedImage image = null;

        if(getAnimID() == AnimationList.heroWalkRight.ordinal())
        {
            // Animatia de mers la dreapta
            setAnimSpeed((1.5 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers la dreapta
            imgPozY = 11;   // frame-urile pt animatia de mers la dreapta se afla pe linia 11
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.heroWalkLeft.ordinal())
        {
            // Animatia de mers la stanga
            setAnimSpeed((1.5 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers la stanga
            imgPozY = 9;   // frame-urile pt animatia de mers la stanga se afla pe linia 9
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.heroWalkUp.ordinal())
        {
            // Animatia de mers la in sus
            setAnimSpeed((1.5 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers in sus
            imgPozY = 8;   // frame-urile pt animatia de mers in sus se afla pe linia 8
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.heroWalkDown.ordinal())
        {
            // Animatia de mers in jos
            setAnimSpeed((1.5 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers in jos
            imgPozY = 10;   // frame-urile pt animatia de mers in jos se afla pe linia 10
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.heroIdleUp.ordinal())
        {
            image = Assets.heroUp;
        }

        if(getAnimID() == AnimationList.heroIdleDown.ordinal())
        {
            image = Assets.heroDown;
        }

        if(getAnimID() == AnimationList.heroIdleLeft.ordinal())
        {
            image = Assets.heroLeft;
        }

        if(getAnimID() == AnimationList.heroIdleRight.ordinal())
        {
            image = Assets.heroRight;
        }

        if(getAnimID() == AnimationList.heroAttackUp.ordinal())
        {
            setAnimSpeed((0.75 * Math.pow(10,8)));
            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                attackAnimationDuration++;
                setNrFrames(6);
                imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());
                imgPozY = 12;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
            else
            {
                attackAnimationDuration = 0;
                ongoingAttackAnimation = false;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
        }

        if(getAnimID() == AnimationList.heroAttackDown.ordinal())
        {
            setAnimSpeed((0.75 * Math.pow(10,8)));
            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                attackAnimationDuration++;
                setNrFrames(6);
                imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());
                imgPozY = 14;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
            else
            {
                attackAnimationDuration = 0;
                ongoingAttackAnimation = false;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
        }

        if(getAnimID() == AnimationList.heroAttackLeft.ordinal())
        {
            setAnimSpeed((0.75 * Math.pow(10,8)));
            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                attackAnimationDuration++;
                setNrFrames(6);
                imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());
                imgPozY = 13;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
            else
            {
                attackAnimationDuration = 0;
                ongoingAttackAnimation = false;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
        }

        if(getAnimID() == AnimationList.heroAttackRight.ordinal())
        {
            setAnimSpeed((0.75 * Math.pow(10,8)));
            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                attackAnimationDuration++;
                setNrFrames(6);
                imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());
                imgPozY = 15;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
            else
            {
                attackAnimationDuration = 0;
                ongoingAttackAnimation = false;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
        }

        return image;
    }
}
