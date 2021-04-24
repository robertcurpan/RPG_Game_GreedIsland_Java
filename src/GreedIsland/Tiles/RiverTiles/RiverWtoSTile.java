package GreedIsland.Tiles.RiverTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RiverWtoSTile extends Tile
{
    public RiverWtoSTile(int id)
    {
        super(Assets.riverWtoS, id);
    }

    @Override
    public boolean IsSolid() { return false; }
}