package GreedIsland.Tiles;

import GreedIsland.Graphics.Assets;

/*! \class public class FountainBottomTile extends Tile
    \brief Abstractizeaza notiunea de fantana.
 */
public class FountainBottomTile extends Tile {

    /*! \fn public FountainBottomTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public FountainBottomTile(int id){
        /// Apel al constructorului clasei de baza
        super(Assets.fountainBottom, id);
        /// Setare bounds
        tileBounds.x = 4; tileBounds.y = 0;
        tileBounds.width = 24; tileBounds.height = 1;
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrierea functiei IsSolid() din clasa de baza pt a seta acest Tile ca fiind solid (exista coliziune cu el)
     */
    @Override
    public boolean IsSolid() { return true; }
}