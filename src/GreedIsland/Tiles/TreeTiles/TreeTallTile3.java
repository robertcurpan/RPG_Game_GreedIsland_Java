package GreedIsland.Tiles.TreeTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

/*! \class public class TreeTallTile3 extends Tile
    \brief Abstractizeaza notiunea de dala de tip copac inalt. (partea de jos)
 */
public class TreeTallTile3 extends Tile {

    /*! \fn public TreeTallTile3(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public TreeTallTile3(int id){
        /// Apel al constructorului clasei de baza
        super(Assets.treeTall3, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrierea functiei IsSolid() din clasa de baza pt a seta acest Tile ca fiind solid (exista coliziune cu el)
     */
    @Override
    public boolean IsSolid() { return true; }
}