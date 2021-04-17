package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofBigDarkTile extends Tile
{
    public RoofBigDarkTile(int id)
    {
        super(Assets.roofBigDark, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}