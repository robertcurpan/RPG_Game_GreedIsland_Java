package GreedIsland.Tiles.RiverTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RiverWtoNTile extends Tile
{
    public RiverWtoNTile(int id)
    {
        super(Assets.riverWtoN, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}
