package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofSmallDarkTile extends Tile
{
    public RoofSmallDarkTile(int id)
    {
        super(Assets.roofSmallDark, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}