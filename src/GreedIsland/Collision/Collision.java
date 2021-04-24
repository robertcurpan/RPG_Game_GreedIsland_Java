package GreedIsland.Collision;

import java.awt.*;

public class Collision
{
    /*! \fn public static boolean CollisionDetection(Rectangle srcRect, Rectangle dstRect)
        \brief Aceasta este o functie statica (nu trebuie sa fie "legata" de un obiect de tip Collision; vrem sa o folosim in orice parte
                a programului) ce returneaza true daca srcRect si dstRect se suprapun, repsectiv false daca nu exista coliziune intre ele.

        \param srcRect si dstRect sunt pur si simplu doua "hitbox-uri". Nu prea conteaza care dintre ele e primul si care e al doilea
                (atunci cand vom apela functia cu parametrii corespunzatori)
     */
    public static boolean CollisionDetection(Rectangle srcRect, Rectangle dstRect)
    {
        if(srcRect.intersects(dstRect))
            return true;
        return false;
    }
}
