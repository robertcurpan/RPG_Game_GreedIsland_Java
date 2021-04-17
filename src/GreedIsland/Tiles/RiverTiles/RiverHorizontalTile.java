package GreedIsland.Tiles.RiverTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RiverHorizontalTile extends Tile
{
    public RiverHorizontalTile(int id)
    {
        super(Assets.riverHorizontal, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}