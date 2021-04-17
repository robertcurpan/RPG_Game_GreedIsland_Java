package GreedIsland.Tiles.HouseTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class HouseStoneTile extends Tile
{
    public HouseStoneTile(int id)
    {
        super(Assets.houseStone, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}