package GreedIsland.Tiles.RiverTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RiverHorizontalTile extends Tile
{
    public RiverHorizontalTile(int id)
    {
        super(Assets.riverHorizontal, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 0;
        tileBounds.width = 32; tileBounds.height = 1;
    }

    @Override
    public boolean IsSolid() { return true; }
}