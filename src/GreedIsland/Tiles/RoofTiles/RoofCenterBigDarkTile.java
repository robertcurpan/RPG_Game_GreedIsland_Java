package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofCenterBigDarkTile extends Tile
{
    public RoofCenterBigDarkTile(int id)
    {
        super(Assets.roofCenterBigDark, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}