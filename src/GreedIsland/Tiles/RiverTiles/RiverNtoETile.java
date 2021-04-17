package GreedIsland.Tiles.RiverTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RiverNtoETile extends Tile
{
    public RiverNtoETile(int id)
    {
        super(Assets.riverNtoE, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}