package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofCenterSmallDarkTile extends Tile
{
    public RoofCenterSmallDarkTile(int id)
    {
        super(Assets.roofCenterSmallDark, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}