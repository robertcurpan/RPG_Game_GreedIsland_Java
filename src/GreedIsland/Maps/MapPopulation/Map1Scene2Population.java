package GreedIsland.Maps.MapPopulation;

import GreedIsland.Items.ConcreteEnemies.Orc;
import GreedIsland.Items.ConcreteEnemies.Skeleton;
import GreedIsland.Items.ConcreteEnemies.Wolf;
import GreedIsland.Items.ConcreteItems.Chest;
import GreedIsland.Items.ConcreteItems.ChestActionType;
import GreedIsland.Items.Enemy;
import GreedIsland.Items.Item;
import GreedIsland.RefLinks;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// FACTORY METHOD 2 //

/*! \class public class Map1Scene2Population implements MapPopulation
    \brief Aceasta clasa este de tip Singleton si va contine, momentan, un vector de iteme cu care putem interactiona.
 */
// Clasa Concrete Factory 2 din sablonul Factory
public class Map1Scene2Population extends MapPopulation
{
    private static Map1Scene2Population map1S2Pop = null;

    public Map1Scene2Population(RefLinks refLink)
    {
        items = new ArrayList<Item>();
        enemies = new ArrayList<Enemy>();

        // Aici vom adauga itemele concrete care se afla in Map1 Scene2
        Item chest1 = new Chest(refLink, 21*32, 2*32, 32, 32, ChestActionType.giveLife.ordinal());
        Item chest2 = new Chest(refLink, 21*32, 11*32, 32, 32, ChestActionType.giveKey.ordinal());
        items.add(chest1);
        items.add(chest2);

        // Aici vom adauga inamicii concreti din Map1 Scene2
        Enemy enemy1 = new Orc(refLink, 17*32, 11*32);
        Enemy enemy2 = new Skeleton(refLink, 10*32, 9*32);
        enemies.add(enemy1);
        enemies.add(enemy2);
    }

    // Metoda "Singleton" de returnare a instantei UNICE (vrem o instanta unica deoarece dorim si un vector unic de Iteme pt fiecare harta).
    public static Map1Scene2Population getInstance(RefLinks refLink)
    {
        if(map1S2Pop == null)
        {
            synchronized (Map1Scene1Population.class)
            {
                if(map1S2Pop == null)
                    map1S2Pop = new Map1Scene2Population(refLink);
            }
        }

        return map1S2Pop;
    }

    // Afisam pe ecran toate itemele din vectorul items
    @Override
    public void drawItems(Graphics g)
    {
        for(Item i : items)
            i.Draw(g);
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    @Override
    public void drawEnemies(Graphics g)
    {
        for(Enemy e : enemies)
            e.Draw(g);
    }

    @Override
    public void updateEnemies() throws FileNotFoundException
    {
        for(Enemy e : enemies)
            e.Update();
    }

    @Override
    public ArrayList<Enemy> getEnemies() { return enemies; }
}
