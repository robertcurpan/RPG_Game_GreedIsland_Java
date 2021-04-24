package GreedIsland.Tiles.RiverTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RiverVerticalTile extends Tile
{
    public RiverVerticalTile(int id)
    {
        super(Assets.riverVertical, id);
        /// Setare bounds
        tileBounds.x = 16; tileBounds.y = 0;
        tileBounds.width = 8; tileBounds.height = 32;
    }

    @Override
    public boolean IsSolid() { return true; }
}