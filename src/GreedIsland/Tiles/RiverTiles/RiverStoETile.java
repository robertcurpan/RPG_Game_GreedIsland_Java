package GreedIsland.Tiles.RiverTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RiverStoETile extends Tile
{
    public RiverStoETile(int id)
    {
        super(Assets.riverStoE, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}
