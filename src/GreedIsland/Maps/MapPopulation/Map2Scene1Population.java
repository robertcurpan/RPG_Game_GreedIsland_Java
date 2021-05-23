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

/*! \class public class Map1Scene1Population implements MapPopulation
    \brief Aceasta clasa este de tip Singleton si va contine, momentan, un vector de iteme cu care putem interactiona.
 */
// Clasa Concrete Product 1 din sablonul Factory
public class Map2Scene1Population extends MapPopulation
{
    private static Map2Scene1Population map2S1Pop = null;

    public Map2Scene1Population(RefLinks refLink)
    {
        items = new ArrayList<Item>();
        enemies = new ArrayList<Enemy>();

        // Aici vom adauga itemele concrete care se afla in Map1 Scene1 (cea cu casa) (la inceputul jocului)
        Item chest1 = new Chest(refLink, 1*32, 1*32, 32, 32, ChestActionType.giveLife.ordinal());
        items.add(chest1);

        // Aici vom adauga inamicii concreti care se afla in Map1 Scene1 (la inceputul jocului)
        Enemy enemy1 = new Wolf(refLink, 12*32, 7*32);
        Enemy enemy2 = new Skeleton(refLink, 6*32, 3*32);
        enemies.add(enemy1);
        enemies.add(enemy2);
    }

    // Metoda "Singleton" de returnare a instantei UNICE (vrem o instanta unica deoarece dorim si un vector unic de Iteme pt fiecare harta).
    public static Map2Scene1Population getInstance(RefLinks refLink)
    {
        if(map2S1Pop == null)
        {
            synchronized (Map2Scene1Population.class)
            {
                if(map2S1Pop == null)
                    map2S1Pop = new Map2Scene1Population(refLink);
            }
        }

        return map2S1Pop;
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

