package GreedIsland.Tiles.TreeTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

/*! \class public class TreeTallTile1 extends Tile
    \brief Abstractizeaza notiunea de dala de tip copac inalt. (partea de sus)
 */
public class TreeTallTile1 extends Tile {

    /*! \fn public TreeTallTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public TreeTallTile1(int id){
        /// Apel al constructorului clasei de baza
        super(Assets.treeTall1, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrierea functiei IsSolid() din clasa de baza pt a seta acest Tile ca fiind solid (exista coliziune cu el)
     */
    @Override
    public boolean IsSolid() { return true; }
}