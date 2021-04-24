package GreedIsland.Tiles.TreeTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

/*! \class public class TreeRoundTile1 extends Tile
    \brief Abstractizeaza notiunea de dala de tip copac rotund. (partea din stanga-sus)
 */
public class TreeRoundTile1 extends Tile {

    /*! \fn public TreeRoundTile1(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public TreeRoundTile1(int id){
        /// Apel al constructorului clasei de baza
        super(Assets.treeRound1, id);
        /// Setare bounds
        tileBounds.x = 16; tileBounds.y = 0;
        tileBounds.width = 16; tileBounds.height = 24;
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrierea functiei IsSolid() din clasa de baza pt a seta acest Tile ca fiind solid (exista coliziune cu el)
     */
    @Override
    public boolean IsSolid() { return true; }
}