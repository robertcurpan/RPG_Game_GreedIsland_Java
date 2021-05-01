package GreedIsland.Maps.MapPopulation;

import GreedIsland.Items.ConcreteItems.Chest;
import GreedIsland.Items.Item;
import GreedIsland.RefLinks;

import java.awt.*;
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

        // Aici vom adauga itemele concrete care se afla in Map1 Scene1 (cea cu casa)
        Item chest1 = new Chest(refLink, 40, 40, 32, 32);
        items.add(chest1);
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
}
