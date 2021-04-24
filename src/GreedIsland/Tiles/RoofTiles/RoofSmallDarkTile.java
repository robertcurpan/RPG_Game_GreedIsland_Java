package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofSmallDarkTile extends Tile
{
    public RoofSmallDarkTile(int id)
    {
        super(Assets.roofSmallDark, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 8;
        tileBounds.width = 32; tileBounds.height = 24;
    }

    @Override
    public boolean IsSolid() { return true; }
}