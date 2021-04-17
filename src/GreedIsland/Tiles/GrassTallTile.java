package GreedIsland.Tiles;

import GreedIsland.Graphics.Assets;

/*! \class public class GrassTallTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba inalta.
 */
public class GrassTallTile extends Tile {

    /*! \fn public GrassTallTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public GrassTallTile(int id){
        /// Apel al constructorului clasei de baza
        super(Assets.grassTall, id); // grassTall-ul are size 32x32 (nu trb si ultimii doi parametri)
    }
}