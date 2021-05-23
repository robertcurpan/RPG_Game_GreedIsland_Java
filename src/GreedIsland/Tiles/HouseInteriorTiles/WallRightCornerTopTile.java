package GreedIsland.Tiles.HouseInteriorTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class WallRightCornerTopTile extends Tile
{
    public WallRightCornerTopTile(int id)
    {
        super(Assets.wallRightCornerTop, id);
        /// Setare bounds
        tileBounds.x = 12; tileBounds.y = 0;
        tileBounds.width = 12; tileBounds.height = 1;
    }

    @Override
    public boolean IsSolid() { return true; }
}