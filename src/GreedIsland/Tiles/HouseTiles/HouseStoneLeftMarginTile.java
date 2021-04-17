package GreedIsland.Tiles.HouseTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class HouseStoneLeftMarginTile extends Tile
{
    public HouseStoneLeftMarginTile(int id)
    {
        super(Assets.houseStoneLeftMargin, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}