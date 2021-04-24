package GreedIsland.Tiles.HouseTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class HouseStoneLeftMarginTile extends Tile
{
    public HouseStoneLeftMarginTile(int id)
    {
        super(Assets.houseStoneLeftMargin, id);
        /// Setare bounds
        tileBounds.x = 16; tileBounds.y = 0;
        tileBounds.width = 1; tileBounds.height = 1;
    }

    @Override
    public boolean IsSolid() { return true; }
}