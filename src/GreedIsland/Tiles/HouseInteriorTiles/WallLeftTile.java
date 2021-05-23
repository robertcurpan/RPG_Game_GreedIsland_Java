package GreedIsland.Tiles.HouseInteriorTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class WallLeftTile extends Tile
{
    public WallLeftTile(int id)
    {
        super(Assets.wallLeft, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 0;
        tileBounds.width = 4; tileBounds.height = 16;
    }

    @Override
    public boolean IsSolid() { return true; }
}
