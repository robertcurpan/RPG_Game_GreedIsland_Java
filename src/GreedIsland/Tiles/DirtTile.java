package GreedIsland.Tiles;

import GreedIsland.Graphics.Assets;

/*! \class public class DirtTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip pietris.
 */
public class DirtTile extends Tile {

    /*! \fn public DirtTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public DirtTile(int id){
            /// Apel al constructorului clasei de baza
        super(Assets.dirt, id); //dirt-ul are size 32x32 (nu trb si ultimii doi parametri)
    }
}
