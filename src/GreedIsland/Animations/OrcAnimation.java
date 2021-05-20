package GreedIsland.Animations;

import GreedIsland.Graphics.ImageLoader;
import GreedIsland.Graphics.SpriteSheetCharacters;
import GreedIsland.RefLinks;

import java.awt.image.BufferedImage;


public class OrcAnimation extends Animation
{
    private int imgPozX, imgPozY;
    private int restingTimeAfterAttack;     // Cat timp trebuie sa se odihneasca inamicul dupa ce ataca
    private int waitForNextAttack;          // Contor pt perioada de timp pe care inamicul trebuie sa o astepte pt a realiza urmatorul atac. Inamicul va putea ataca din nou cand aceasta variabila atinge o anumite valoare (de ex 3*60 = 3 secunde)

    public OrcAnimation()
    {
        // Spritesheet-ul inamicului de tip Orc
        characterSheet = new SpriteSheetCharacters(ImageLoader.LoadImage("/textures/Enemies_sprites.png"));

        // Paramaters of Orc's animation
        setAnimSpeed((1.5 * Math.pow(10,8)));   // 1.5 * 10^8 nanosecunde = 150 milisecunde (animatia va schimba imaginea la fiecare 150 ms)
        restingTimeAfterAttack = 3 * 60 / 2;    // dupa ce ataca, inamicul trebuie sa se odihneasca pt 1.5 secunde pt a putea ataca din nou

        // Auxiliary variables
        ongoingAttackAnimation = false;
        attackAnimationDuration = 0;
        canAttack = true;
        waitForNextAttack = 0;
        canInflictDamageToHero = false;
        deathAnimationCounter = 0;
        isDead = false;
    }

    /*! \fn public BufferedImage playAnimation()
        \brief Realizeaza animatia propriu-zisa (selecteaza imaginile corespunzatoare din spritesheet la momentele de timp potrivite (dictate de
                viteza animatiei (animSpeed) si de nr de frame-uri (imagini) ale fiecarui tip de animatie)
     */
    @Override
    public BufferedImage playAnimation(RefLinks refLink)
    {
        BufferedImage image = null;

        if(getAnimID() == AnimationList.orcWalkRight.ordinal())
        {
            // Animatia de mers la dreapta
            setAnimSpeed((1.5 * Math.pow(10,8)));
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers la dreapta
            imgPozY = 11;   // frame-urile pt animatia de mers la dreapta se afla pe linia 11
            image = characterSheet.crop(imgPozX, imgPozY);

            //canAttack = true; // daca eroul s-a indepartat de inamic (din range-ul de atac al inamicului), inamicul nu mai trebuie sa astepte cele 3 secunde; la urmatoarea ocazie va putea ataca din nou.
        }

        if(getAnimID() == AnimationList.orcWalkLeft.ordinal())
        {
            // Animatia de mers la stanga
            setAnimSpeed((1.5 * Math.pow(10,8)));
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers la stanga
            imgPozY = 9;   // frame-urile pt animatia de mers la stanga se afla pe linia 9
            image = characterSheet.crop(imgPozX, imgPozY);

            //canAttack = true; // daca eroul s-a indepartat de inamic (din range-ul de atac al inamicului), inamicul nu mai trebuie sa astepte cele 3 secunde; la urmatoarea ocazie va putea ataca din nou.
        }

        if(getAnimID() == AnimationList.orcWalkUp.ordinal())
        {
            // Animatia de mers la in sus
            setAnimSpeed((1.5 * Math.pow(10,8)));
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers in sus
            imgPozY = 8;   // frame-urile pt animatia de mers in sus se afla pe linia 8
            image = characterSheet.crop(imgPozX, imgPozY);

            //canAttack = true; // daca eroul s-a indepartat de inamic (din range-ul de atac al inamicului), inamicul nu mai trebuie sa astepte cele 3 secunde; la urmatoarea ocazie va putea ataca din nou.
        }

        if(getAnimID() == AnimationList.orcWalkDown.ordinal())
        {
            // Animatia de mers in jos
            setAnimSpeed((1.5 * Math.pow(10,8)));
            setNrFrames(9);
            imgPozX = 0 + ((int)(System.nanoTime() / getAnimSpeed()) % getNrFrames());  // ciclam printre cele 9 imagini ale animatiei de mers in jos
            imgPozY = 10;   // frame-urile pt animatia de mers in jos se afla pe linia 10
            image = characterSheet.crop(imgPozX, imgPozY);

            //canAttack = true; // daca eroul s-a indepartat de inamic (din range-ul de atac al inamicului), inamicul nu mai trebuie sa astepte cele 3 secunde; la urmatoarea ocazie va putea ataca din nou.
        }

        if(getAnimID() == AnimationList.orcIdleUp.ordinal())
        {
            image = characterSheet.crop(0,8);
        }

        if(getAnimID() == AnimationList.orcIdleDown.ordinal())
        {
            image = characterSheet.crop(0,10);
        }

        if(getAnimID() == AnimationList.orcIdleLeft.ordinal())
        {
            image = characterSheet.crop(0,9);
        }

        if(getAnimID() == AnimationList.orcIdleRight.ordinal())
        {
            image = characterSheet.crop(0,11);
        }

        // pt animatia de atac a acestui inamic vom proceda la fel ca in clasa HeroAnimation.

        if(getAnimID() == AnimationList.orcAttackUp.ordinal())
        {
            setAnimSpeed((0.4 * Math.pow(10,8)));
            setNrFrames(8);

            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                ongoingAttackAnimation = true;
                attackAnimationDuration++;

                // la jumatatea animatiei de atac eroul va lua damage
                if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 2))
                {
                    canInflictDamageToHero = true;
                }

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
                if(attackAnimationDuration >= 6 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 7 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 6;
                if(attackAnimationDuration >= 7 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 8 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 7;
                imgPozY = 4;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
            else
            {
                attackAnimationDuration = 0;
                ongoingAttackAnimation = false;
                canAttack = false;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
        }

        if(getAnimID() == AnimationList.orcAttackDown.ordinal())
        {
            setAnimSpeed((0.4 * Math.pow(10,8)));
            setNrFrames(8);

            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                ongoingAttackAnimation = true;
                attackAnimationDuration++;

                // la jumatatea animatiei de atac eroul va lua damage
                if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 2))
                {
                    canInflictDamageToHero = true;
                }

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
                if(attackAnimationDuration >= 6 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 7 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 6;
                if(attackAnimationDuration >= 7 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 8 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 7;
                imgPozY = 6;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
            else
            {
                attackAnimationDuration = 0;
                ongoingAttackAnimation = false;
                canAttack = false;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
        }

        if(getAnimID() == AnimationList.orcAttackLeft.ordinal())
        {
            setAnimSpeed((0.4 * Math.pow(10,8)));
            setNrFrames(8);

            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                ongoingAttackAnimation = true;
                attackAnimationDuration++;

                // la jumatatea animatiei de atac eroul va lua damage
                if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 2))
                {
                    canInflictDamageToHero = true;
                }

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
                if(attackAnimationDuration >= 6 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 7 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 6;
                if(attackAnimationDuration >= 7 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 8 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 7;
                imgPozY = 5;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
            else
            {
                attackAnimationDuration = 0;
                ongoingAttackAnimation = false;
                canAttack = false;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
        }

        if(getAnimID() == AnimationList.orcAttackRight.ordinal())
        {
            setAnimSpeed((0.4 * Math.pow(10,8)));
            setNrFrames(8);

            if(attackAnimationDuration < (60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9))) //am pus durata (in frame-uri) a unui atac
            {
                ongoingAttackAnimation = true;
                attackAnimationDuration++;

                // la jumatatea animatiei de atac eroul va lua damage
                if(attackAnimationDuration == (int)(60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9) / 2))
                {
                    canInflictDamageToHero = true;
                }

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
                if(attackAnimationDuration >= 6 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 7 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 6;
                if(attackAnimationDuration >= 7 * 60 * getAnimSpeed() / Math.pow(10,9) && attackAnimationDuration < 8 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 7;
                imgPozY = 7;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
            else
            {
                attackAnimationDuration = 0;
                ongoingAttackAnimation = false;
                canAttack = false;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
        }

        if(canAttack == false)
        {
            waitForNextAttack++;
            if(waitForNextAttack >= restingTimeAfterAttack) // daca au trecut 3 secunde dupa ce inamicul a atacat
            {
                canAttack = true;
                waitForNextAttack = 0;
            }
        }

        if(getAnimID() == AnimationList.orcDeath.ordinal())
        {
            setAnimSpeed((1.6 * Math.pow(10,8)));
            setNrFrames(6);
            if(deathAnimationCounter < (60 * getNrFrames() * getAnimSpeed() / (float)Math.pow(10,9))) //am pus durata (in frame-uri) a animatiei de moarte
            {
                deathAnimationCounter++;

                if(deathAnimationCounter < 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 0;
                if(deathAnimationCounter >= 60 * getAnimSpeed() / Math.pow(10,9) && deathAnimationCounter < 2 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 1;
                if(deathAnimationCounter >= 2 * 60 * getAnimSpeed() / Math.pow(10,9) && deathAnimationCounter < 3 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 2;
                if(deathAnimationCounter >= 3 * 60 * getAnimSpeed() / Math.pow(10,9) && deathAnimationCounter < 4 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 3;
                if(deathAnimationCounter >= 4 * 60 * getAnimSpeed() / Math.pow(10,9) && deathAnimationCounter < 5 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 4;
                if(deathAnimationCounter >= 5 * 60 * getAnimSpeed() / Math.pow(10,9) && deathAnimationCounter < 6 * 60 * getAnimSpeed() / Math.pow(10,9))
                    imgPozX = 5;
                imgPozY = 20;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
            else
            {
                deathAnimationCounter = 0;
                isDead = true;
                image = characterSheet.crop(imgPozX, imgPozY);
            }
        }

        return image;
    }
}
