package GreedIsland.Animations;

import GreedIsland.Graphics.Assets;
import GreedIsland.Graphics.ImageLoader;
import GreedIsland.Graphics.SpriteSheetCharacters;
import GreedIsland.RefLinks;

import java.awt.image.BufferedImage;


public class WolfAnimation extends Animation
{
    private int imgPozX, imgPozY;

    public WolfAnimation()
    {
        characterSheet = new SpriteSheetCharacters(ImageLoader.LoadImage("/textures/Enemies_sprites.png"));
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

        if(getAnimID() == AnimationList.wolfWalkRight.ordinal())
        {
            // Animatia de mers la dreapta
            setNrFrames(9);
            imgPozX = 26 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers la dreapta
            imgPozY = 11;   // frame-urile pt animatia de mers la dreapta se afla pe linia 11
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.wolfWalkLeft.ordinal())
        {
            // Animatia de mers la stanga
            setNrFrames(9);
            imgPozX = 26 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers la stanga
            imgPozY = 9;   // frame-urile pt animatia de mers la stanga se afla pe linia 9
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.wolfWalkUp.ordinal())
        {
            // Animatia de mers la in sus
            setNrFrames(9);
            imgPozX = 26 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers in sus
            imgPozY = 8;   // frame-urile pt animatia de mers in sus se afla pe linia 8
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.wolfWalkDown.ordinal())
        {
            // Animatia de mers in jos
            setNrFrames(9);
            imgPozX = 26 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers in jos
            imgPozY = 10;   // frame-urile pt animatia de mers in jos se afla pe linia 10
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.wolfIdleUp.ordinal())
        {
            image = characterSheet.crop(26,8);
        }

        if(getAnimID() == AnimationList.wolfIdleDown.ordinal())
        {
            image = characterSheet.crop(26,10);
        }

        if(getAnimID() == AnimationList.wolfIdleLeft.ordinal())
        {
            image = characterSheet.crop(26,9);
        }

        if(getAnimID() == AnimationList.wolfIdleRight.ordinal())
        {
            image = characterSheet.crop(26,11);
        }

        return image;
    }
}
