package GreedIsland.Tiles.TreeTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

/*! \class public class TreeFallenTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip copac doborat/cazut.
 */
public class TreeFallenTile extends Tile {

    /*! \fn public TreeFallenTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public TreeFallenTile(int id){
        /// Apel al constructorului clasei de baza
        super(Assets.treeFallen, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrierea functiei IsSolid() din clasa de baza pt a seta acest Tile ca fiind solid (exista coliziune cu el)
     */
    @Override
    public boolean IsSolid() { return true; }
}