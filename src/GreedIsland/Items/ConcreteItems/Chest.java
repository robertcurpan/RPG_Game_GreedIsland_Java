package GreedIsland.Items.ConcreteItems;

import GreedIsland.Graphics.Assets;
import GreedIsland.Items.Hero;
import GreedIsland.Items.Item;
import GreedIsland.Maps.Map;
import GreedIsland.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

// Itemele Concrete reprezinta acele Iteme cu care putem interactiona. Momentan, la apasarea tastei E langa un chest, acesta va disparea
// Ulterior vom face implementari mai complexe pt astfel de actiuni.
public class Chest extends Item
{
    private BufferedImage image;
    private final int actionType;         /*!< Acest parametru va indica actiunea declansata de culegerea chestului (giveLifePoint, giveKey, giveClueForTreasure). */

    public Chest(RefLinks refLink, float x, float y, int width, int height, int actionType)
    {
        super(refLink, x, y, width, height);
        image = Assets.chest;
            /// Setare bounds
        bounds.x = 12; bounds.y = 0;
        bounds.width = 8; bounds.height = 1;
            /// Setare actionType
        this.actionType = actionType;
    }

    @Override
    public void Update()
    {

    }

    @Override
    public void Draw(Graphics g)
    {
            /// Afisam chestul
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    /*! \fn public void DoAction()
        \brief Defineste actiunea ce trebuie executata de un item atunci cand se apasa o tasta in apropierea lui.
                (Itemele = acele obiecte cu care se poate interactiona prin apasarea unei taste, nu doar prin coliziune)
     */
    @Override
    public void DoAction()
    {
        Hero hero = Hero.getHeroInstance(refLink, 0, 0);

        /// Actiunea ce trebuie "executata" atunci cand se apasa o tasta langa un item (apasarea tastei e verificata in clasa Hero, nu aici)

        if(actionType == ChestActionType.giveLife.ordinal())
        {
            if(hero.GetLife() < hero.getMaxLife())
            {
                hero.SetLife(hero.GetLife() + 1);
                refLink.GetMap().setVariablesForPrintingOnScreen(true, "RECEIVED 1 LIFE POINT!");
            }
            else
            {
                refLink.GetMap().setVariablesForPrintingOnScreen(true, "MAXIMUM HEALTH!");
            }

        }
        if(actionType == ChestActionType.giveKey.ordinal())
        {
            hero.hasKey = true;
            refLink.GetMap().setVariablesForPrintingOnScreen(true, "RECEIVED A KEY!");

        }
        if(actionType == ChestActionType.giveClueForTreasure.ordinal())
        {
            if(hero.nrEnemiesKilled == refLink.GetMap().NR_ENEMIES)
            {
                hero.hasTreasureLocation = true;
                refLink.GetMap().score += 50;
                refLink.GetMap().setVariablesForPrintingOnScreen(true, "RECEIVED TREASURE'S LOCATION!");
            }
            else
            {
                refLink.GetMap().setVariablesForPrintingOnScreen(true, "KILL ALL ENEMIES FIRST!");
                return;
            }
        }
        if(actionType == ChestActionType.giveTreasure.ordinal())
        {
            if(hero.nrEnemiesKilled == refLink.GetMap().NR_ENEMIES)
            {
                hero.hasTreasure = true;
                refLink.GetMap().score += 50;
                refLink.GetMap().setVariablesForPrintingOnScreen(true, "FOUND TREASURE! GAME WON!");
            }
            else
            {
                refLink.GetMap().setVariablesForPrintingOnScreen(true, "KILL ALL ENEMIES FIRST!");
                return;
            }
        }

        hero.nrChestsCollected++;
        refLink.GetMap().score += 10;

        refLink.GetMap().getMapPopulation().getItems().remove(this); // Am sters chest-ul din lista de iteme aferente primei scene din prima harta
    }

}
