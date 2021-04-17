package GreedIsland.Tiles.RiverTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RiverVerticalTile extends Tile
{
    public RiverVerticalTile(int id)
    {
        super(Assets.riverVertical, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}