package GreedIsland.Maps;

import GreedIsland.Items.Item;
import GreedIsland.RefLinks;

import java.awt.*;
import java.util.ArrayList;

/*! \class public interface MapPopulation
    \brief Aceasta clasa va fi o interfata pt clasele concrete Map1Scene1Population, Map1Scene2Population etc.
            Fiecare din aceste clase concrete va contine cate un vector de iteme, un vector de inamici etc.
            Fiecare din clasele respective va fi Singleton (Pt a putea returna starea unei scene asa cum a ramas
            dupa parcurgerea ei de catre player).
 */
public interface MapPopulation
{
    void drawItems(Graphics g);
    ArrayList<Item> getItems();
}
