package GreedIsland.Maps.MapPopulation;

import GreedIsland.Items.ConcreteEnemies.Orc;
import GreedIsland.Items.ConcreteEnemies.Skeleton;
import GreedIsland.Items.ConcreteEnemies.Wolf;
import GreedIsland.Items.ConcreteItems.Chest;
import GreedIsland.Items.ConcreteItems.ChestActionType;
import GreedIsland.Items.Enemy;
import GreedIsland.Items.Item;
import GreedIsland.Maps.MapTiles.Map1Scene1;
import GreedIsland.RefLinks;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// FACTORY METHOD 2 //

/*! \class public class Map1Scene3Population implements MapPopulation
    \brief Aceasta clasa este de tip Singleton si va contine, momentan, un vector de iteme cu care putem interactiona.
 */
// Clasa Concrete Product 1 din sablonul Factory
public class Map1Scene3Population extends MapPopulation
{
    private static Map1Scene3Population map1S3Pop = null;

    public Map1Scene3Population(RefLinks refLink)
    {
        items = new ArrayList<Item>();
        enemies = new ArrayList<Enemy>();

        // Aici vom adauga itemele concrete care se afla in Map1 Scene3
        Item chest1 = new Chest(refLink, 7*32, 16*32, 32, 32, ChestActionType.giveLife.ordinal());
        Item chest2 = new Chest(refLink, 18*32, 18*32, 32, 32, ChestActionType.giveLife.ordinal());
        items.add(chest1);
        items.add(chest2);

        // Aici vom adauga inamicii concreti care se afla in Map1 Scene3
        Enemy enemy1 = new Wolf(refLink, 400, 400);
        enemies.add(enemy1);

    }

    // Metoda "Singleton" de returnare a instantei UNICE (vrem o instanta unica deoarece dorim si un vector unic de Iteme pt fiecare harta).
    public static Map1Scene3Population getInstance(RefLinks refLink)
    {
        if(map1S3Pop == null)
        {
            synchronized (Map1Scene3Population.class)
            {
                if(map1S3Pop == null)
                    map1S3Pop = new Map1Scene3Population(refLink);
            }
        }

        return map1S3Pop;
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
