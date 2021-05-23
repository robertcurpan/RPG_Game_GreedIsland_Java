package GreedIsland.Tiles.HouseInteriorTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class WallRightTile extends Tile
{
    public WallRightTile(int id)
    {
        super(Assets.wallRight, id);
        /// Setare bounds
        tileBounds.x = 28; tileBounds.y = 4;
        tileBounds.width = 8; tileBounds.height = 16;
    }

    @Override
    public boolean IsSolid() { return true; }
}
