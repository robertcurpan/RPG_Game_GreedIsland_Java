package GreedIsland.Tiles.HouseTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class HouseStoneRightMarginTile extends Tile
{
    public HouseStoneRightMarginTile(int id)
    {
        super(Assets.houseStoneRightMargin, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}