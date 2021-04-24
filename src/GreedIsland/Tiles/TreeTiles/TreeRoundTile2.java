package GreedIsland.Tiles.TreeTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

/*! \class public class TreeRoundTile2 extends Tile
    \brief Abstractizeaza notiunea de dala de tip copac rotund. (partea din dreapta-sus)
 */
public class TreeRoundTile2 extends Tile {

    /*! \fn public TreeRoundTile2(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public TreeRoundTile2(int id){
        /// Apel al constructorului clasei de baza
        super(Assets.treeRound2, id);
        /// Setare bounds
        tileBounds.x = 16; tileBounds.y = 0;
        tileBounds.width = 4; tileBounds.height = 24;
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrierea functiei IsSolid() din clasa de baza pt a seta acest Tile ca fiind solid (exista coliziune cu el)
     */
    @Override
    public boolean IsSolid() { return true; }
}