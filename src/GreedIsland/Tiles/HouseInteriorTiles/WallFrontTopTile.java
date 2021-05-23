package GreedIsland.Tiles.HouseInteriorTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class WallFrontTopTile extends Tile
{
    public WallFrontTopTile(int id)
    {
        super(Assets.wallFrontTop, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = -5;
        tileBounds.width = 32; tileBounds.height = 32;
    }

    @Override
    public boolean IsSolid() { return true; }
}
