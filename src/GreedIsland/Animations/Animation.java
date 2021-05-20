package GreedIsland.Animations;

import GreedIsland.Graphics.SpriteSheetCharacters;
import GreedIsland.RefLinks;

import java.awt.image.BufferedImage;

public abstract class Animation
{
    private int animID;         // id-ul animatiei
    private double animSpeed;   // cat de "repede" sa se faca tranzitiile intre imagini (care formeaza animatia)
    private int nrFrames;       // numarul de frameuri (nr de imagini) ale animatiei

    public boolean ongoingAttackAnimation;      //Acest camp ne va indica daca ne aflam in timpul unei animatii de atac sau nu
    public int attackAnimationDuration;         //Cu ajutorul acestui camp vom putea executa o singura animatie de atac la apasarea tastei SPACE (sau pt animatia inamicilor)
    public boolean canAttack;                   //Flag care ne indica daca un inamic poate ataca. Daca e setat pe false, el trebuie sa astepte o anumta perioada pt a putea ataca din nou
    public boolean canInflictDamageToHero;      //Flag care ne indica faptul ca atacul inamicului determina scaderea punctelor de viata ale eroului.
    public boolean canInflictDamageToEnemies;   //Flag care ne indica faptul ca atacul eroului va provoca daune inamicului pe care l-a lovit
    public int deathAnimationCounter;           //Variabila care va contoriza timpul scurs de la inceputul animatiei de moarte a inamicului
    public boolean isDead;                      //Flag care ne indica daca inamicul este mort sau nu

        /// Fiecare caracter va implementa spritesheet-ul corespunzator
    protected SpriteSheetCharacters characterSheet;

    // Obligam toate clasele care extind Animation sa implementeze aceasta metoda.
    // Practic, aceasta clasa abstracta va fi extinsa de clase precum HeroAnimation, SkeletonAnimation etc.., fiecare
    // avand propria implementare a functiei de playAnimation() (cu propriile seturi de imagini, viteza etc.)
    public abstract BufferedImage playAnimation(RefLinks refLink);

    public int getAnimID() { return animID; }
    public double getAnimSpeed() { return animSpeed; }
    public int getNrFrames() { return nrFrames; }
    public void setAnimID(int id) { animID = id; }
    public void setAnimSpeed(double speed) { animSpeed = speed; }
    public void setNrFrames(int frames) { nrFrames = frames; }
}
