package GreedIsland.Tiles.TreeTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;


/*! \class public class TreeCutTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip copac taiat (ciot/butuc de copac)
 */
public class TreeCutTile extends Tile {

    /*! \fn public TreeCutTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public TreeCutTile(int id){
        /// Apel al constructorului clasei de baza
        super(Assets.treeCut, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrierea functiei IsSolid() din clasa de baza pt a seta acest Tile ca fiind solid (exista coliziune cu el)
     */
    @Override
    public boolean IsSolid() { return true; }
}