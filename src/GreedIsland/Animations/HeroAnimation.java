package GreedIsland.Animations;

import GreedIsland.Graphics.Assets;
import GreedIsland.Graphics.ImageLoader;
import GreedIsland.Graphics.SpriteSheetCharacters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HeroAnimation extends Animation
{
    private int imgPozX, imgPozY;

    public HeroAnimation()
    {
        characterSheet = new SpriteSheetCharacters(ImageLoader.LoadImage("/textures/Characters_sprites.png"));
        setAnimSpeed((1.5 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
    }

    /*! \fn public BufferedImage playAnimation()
        \brief Realizeaza animatia propriu-zisa (selecteaza imaginile corespunzatoare din spritesheet la momentele de timp potrivite (dictate de
                viteza animatiei (animSpeed) si de nr de frame-uri (imagini) ale fiecarui tip de animatie)
     */
    @Override
    public BufferedImage playAnimation()
    {
        BufferedImage image = null;

        if(getAnimID() == AnimationList.walkRight.ordinal())
        {
            // Animatia de mers la dreapta
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers la dreapta
            imgPozY = 11;   // frame-urile pt animatia de mers la dreapta se afla pe linia 11
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.walkLeft.ordinal())
        {
            // Animatia de mers la stanga
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers la stanga
            imgPozY = 9;   // frame-urile pt animatia de mers la stanga se afla pe linia 9
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.walkUp.ordinal())
        {
            // Animatia de mers la in sus
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers in sus
            imgPozY = 8;   // frame-urile pt animatia de mers in sus se afla pe linia 8
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.walkDown.ordinal())
        {
            // Animatia de mers in jos
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers in jos
            imgPozY = 10;   // frame-urile pt animatia de mers in jos se afla pe linia 10
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.idleUp.ordinal())
        {
            image = Assets.heroUp;
        }

        if(getAnimID() == AnimationList.idleDown.ordinal())
        {
            image = Assets.heroDown;
        }

        if(getAnimID() == AnimationList.idleLeft.ordinal())
        {
            image = Assets.heroLeft;
        }

        if(getAnimID() == AnimationList.idleRight.ordinal())
        {
            image = Assets.heroRight;
        }

        return image;
    }
}
