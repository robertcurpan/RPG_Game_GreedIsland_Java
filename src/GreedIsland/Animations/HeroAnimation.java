package GreedIsland.Animations;

import GreedIsland.Graphics.Assets;
import GreedIsland.Graphics.ImageLoader;
import GreedIsland.Graphics.SpriteSheetCharacters;
import GreedIsland.Items.Hero;
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
        setAnimSpeed((1 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
        ongoingAttackAnimation = false;
        attackAnimationDuration = 0;
        canInflictDamageToEnemies = false;
    }

    /*! \fn public BufferedImage playAnimation()
        \brief Realizeaza animatia propriu-zisa (selecteaza imaginile corespunzatoare din spritesheet la momentele de timp potrivite (dictate de
                viteza animatiei (animSpeed) si de nr de frame-uri (imagini) ale fiecarui tip de animatie)
     */
    @Override
    public BufferedImage playAnimation(RefLinks refLink)
    {
        BufferedImage image = null;

        if(getAnimID() == AnimationList.heroWalkRight.ordinal())
        {
            // Animatia de mers la dreapta
            setAnimSpeed((1 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers la dreapta
            imgPozY = 11;   // frame-urile pt animatia de mers la dreapta se afla pe linia 11
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.heroWalkLeft.ordinal())
        {
            // Animatia de mers la stanga
            setAnimSpeed((1 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers la stanga
            imgPozY = 9;   // frame-urile pt animatia de mers la stanga se afla pe linia 9
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.heroWalkUp.ordinal())
        {
            // Animatia de mers la in sus
            setAnimSpeed((1 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers in sus
            imgPozY = 8;   // frame-urile pt animatia de mers in sus se afla pe linia 8
            image = characterSheet.crop(imgPozX, imgPozY);
        }

        if(getAnimID() == AnimationList.heroWalkDown.ordinal())
        {
            // Animatia de mers in jos
            setAnimSpeed((1 * Math.pow(10,8)));    // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
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
            // undeva pe la inceputul animatiei de atac vom seta bound-urile asociate attackMode-ului (facem acest if pt a nu se seta la fiecare frame)
            if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 6))
            {
                Hero hero = Hero.getHeroInstance(refLink, 0, 0);
                hero.setAttackBoundsForHero(new Rectangle(hero.getNormalBoundsForHero().x + 8, hero.getNormalBoundsForHero().y - hero.getAttackRange()/2, hero.getNormalBoundsForHero().width - 12, hero.getAttackRange()));
            }

            // la jumatatea animatiei de atac se vor produce daunele in randul inamicilor loviti
            if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 2))
            {
                canInflictDamageToEnemies = true;
            }

            setAnimSpeed((0.75 * Math.pow(10,8)));
            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                //ongoingAttackAnimation = true;
                attackAnimationDuration++;
                setNrFrames(6);
                //imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());
                // Facem in felul acesta pt ca, la fiecare atac, animatia sa inceapa de la inceput
                if(attackAnimationDuration < 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 0;
                if(attackAnimationDuration >= 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 2 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 1;
                if(attackAnimationDuration >= 2 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 3 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 2;
                if(attackAnimationDuration >= 3 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 4 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 3;
                if(attackAnimationDuration >= 4 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 5 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 4;
                if(attackAnimationDuration >= 5 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 6 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 5;
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
            // undeva pe la inceputul animatiei de atac vom seta bound-urile asociate attackMode-ului (facem acest if pt a nu se seta la fiecare frame)
            if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 6))
            {
                Hero hero = Hero.getHeroInstance(refLink, 0, 0);
                hero.setAttackBoundsForHero(new Rectangle(hero.getNormalBoundsForHero().x + 8, hero.getNormalBoundsForHero().y + hero.getNormalBoundsForHero().height, hero.getNormalBoundsForHero().width - 12, hero.getAttackRange()));
            }

            // la jumatatea animatiei de atac se vor produce daunele in randul inamicilor loviti
            if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 2))
            {
                canInflictDamageToEnemies = true;
            }

            setAnimSpeed((0.75 * Math.pow(10,8)));
            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                //ongoingAttackAnimation = true;
                attackAnimationDuration++;
                setNrFrames(6);
                //imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());
                // Facem in felul acesta pt ca, la fiecare atac, animatia sa inceapa de la inceput
                if(attackAnimationDuration < 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 0;
                if(attackAnimationDuration >= 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 2 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 1;
                if(attackAnimationDuration >= 2 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 3 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 2;
                if(attackAnimationDuration >= 3 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 4 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 3;
                if(attackAnimationDuration >= 4 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 5 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 4;
                if(attackAnimationDuration >= 5 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 6 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 5;
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
            // undeva pe la inceputul animatiei de atac vom seta bound-urile asociate attackMode-ului (facem acest if pt a nu se seta la fiecare frame)
            if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 6))
            {
                Hero hero = Hero.getHeroInstance(refLink, 0, 0);
                hero.setAttackBoundsForHero(new Rectangle(hero.getNormalBoundsForHero().x - hero.getAttackRange()/2, hero.getNormalBoundsForHero().y + 8, hero.getAttackRange(), hero.getNormalBoundsForHero().height - 8));
            }

            // la jumatatea animatiei de atac se vor produce daunele in randul inamicilor loviti
            if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 2))
            {
                canInflictDamageToEnemies = true;
            }

            setAnimSpeed((0.75 * Math.pow(10,8)));
            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                //ongoingAttackAnimation = true;
                attackAnimationDuration++;
                setNrFrames(6);
                //imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());
                // Facem in felul acesta pt ca, la fiecare atac, animatia sa inceapa de la inceput
                if(attackAnimationDuration < 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 0;
                if(attackAnimationDuration >= 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 2 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 1;
                if(attackAnimationDuration >= 2 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 3 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 2;
                if(attackAnimationDuration >= 3 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 4 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 3;
                if(attackAnimationDuration >= 4 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 5 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 4;
                if(attackAnimationDuration >= 5 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 6 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 5;
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
            // undeva pe la inceputul animatiei de atac vom seta bound-urile asociate attackMode-ului (facem acest if pt a nu se seta la fiecare frame)
            if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 6))
            {
                Hero hero = Hero.getHeroInstance(refLink, 0, 0);
                hero.setAttackBoundsForHero(new Rectangle(hero.getNormalBoundsForHero().x + 3*hero.getNormalBoundsForHero().width/4, hero.getNormalBoundsForHero().y + 8, hero.getAttackRange(), hero.getNormalBoundsForHero().height - 8));
            }

            // la jumatatea animatiei de atac se vor produce daunele in randul inamicilor loviti
            if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 2))
            {
                canInflictDamageToEnemies = true;
            }

            setAnimSpeed((0.75 * Math.pow(10,8)));
            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                //ongoingAttackAnimation = true;
                attackAnimationDuration++;
                setNrFrames(6);
                // Facem in felul acesta pt ca, la fiecare atac, animatia sa inceapa de la inceput
                if(attackAnimationDuration < 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 0;
                if(attackAnimationDuration >= 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 2 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 1;
                if(attackAnimationDuration >= 2 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 3 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 2;
                if(attackAnimationDuration >= 3 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 4 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 3;
                if(attackAnimationDuration >= 4 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 5 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 4;
                if(attackAnimationDuration >= 5 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 6 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 5;
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
