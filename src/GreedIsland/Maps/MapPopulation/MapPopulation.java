package GreedIsland.Maps.MapPopulation;

import GreedIsland.Items.Enemy;
import GreedIsland.Items.Item;
import GreedIsland.RefLinks;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// FACTORY METHOD 2 //

/*! \class public interface MapPopulation
    \brief Aceasta clasa va fi o interfata pt clasele concrete Map1Scene1Population, Map1Scene2Population etc.
            Fiecare din aceste clase concrete va contine cate un vector de iteme, un vector de inamici etc.
            Fiecare din clasele respective va fi Singleton (Pt a putea returna starea unei scene asa cum a ramas
            dupa parcurgerea ei de catre player).
 */
// Clasa Product din sablonul Factory
public abstract class MapPopulation
{
    protected ArrayList<Item> items;    // vector de iteme pt o harta (va fi folosit de clasele derivate (Concrete Products))
    protected ArrayList<Enemy> enemies; // vector de inamici pt o harta (va fi folosit de clasele derivate)

    public abstract void drawItems(Graphics g);
    public abstract ArrayList<Item> getItems();

    public abstract void drawEnemies(Graphics g);
    public abstract void updateEnemies() throws FileNotFoundException;
    public abstract ArrayList<Enemy> getEnemies();

}
