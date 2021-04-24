package GreedIsland.Tiles.HouseTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class HouseStoneTile extends Tile
{
    public HouseStoneTile(int id)
    {
        super(Assets.houseStone, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 0;
        tileBounds.width = 1; tileBounds.height = 1;
    }

    @Override
    public boolean IsSolid() { return true; }
}