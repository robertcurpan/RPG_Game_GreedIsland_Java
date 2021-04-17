package GreedIsland.Tiles;

import GreedIsland.Graphics.Assets;

/*! \class public class FountainTopTile extends Tile
    \brief Abstractizeaza notiunea de fantana.
 */
public class FountainTopTile extends Tile {

    /*! \fn public FountainTopTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public FountainTopTile(int id){
        /// Apel al constructorului clasei de baza
        super(Assets.fountainTop, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrierea functiei IsSolid() din clasa de baza pt a seta acest Tile ca fiind solid (exista coliziune cu el)
     */
    @Override
    public boolean IsSolid() { return true; }
}