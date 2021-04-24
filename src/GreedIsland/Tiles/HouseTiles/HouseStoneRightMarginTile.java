package GreedIsland.Tiles.HouseTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class HouseStoneRightMarginTile extends Tile
{
    public HouseStoneRightMarginTile(int id)
    {
        super(Assets.houseStoneRightMargin, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 0;
        tileBounds.width = 24; tileBounds.height = 1;
    }

    @Override
    public boolean IsSolid() { return true; }
}