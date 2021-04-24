package GreedIsland.Maps;

import GreedIsland.Items.ConcreteItems.Chest;
import GreedIsland.Items.Hero;
import GreedIsland.Items.Item;
import GreedIsland.RefLinks;

import java.awt.*;
import java.util.ArrayList;

/*! \class public class Map1Scene1Population implements MapPopulation
    \brief Aceasta clasa este de tip Singleton si va contine, momentan, un vector de iteme cu care putem interactiona.
 */
public class Map1Scene1Population implements MapPopulation
{
    private static Map1Scene1Population map1S1Pop;
    private ArrayList<Item> items;  // vector de Iteme pentru Map1 Scene1

    public Map1Scene1Population(RefLinks refLink)
    {
        items = new ArrayList<Item>();

        // Aici vom adauga itemele concrete care se afla in Map1 Scene1 (cea cu casa)
        Item chest1 = new Chest(refLink, 360, 360, 32, 32);
        Item chest2 = new Chest(refLink, 480,240,32,32);
        items.add(chest1);
        items.add(chest2);
    }

    // Metoda "Singleton" de returnare a instantei UNICE (vrem o instanta unica deoarece dorim si un vector unic de Iteme pt fiecare harta).
    public static Map1Scene1Population getMap1Scene1Pop(RefLinks refLink)
    {
        if(map1S1Pop == null)
            map1S1Pop = new Map1Scene1Population(refLink);

        return map1S1Pop;
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
}
