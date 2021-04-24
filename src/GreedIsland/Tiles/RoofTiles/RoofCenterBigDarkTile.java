package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofCenterBigDarkTile extends Tile
{
    public RoofCenterBigDarkTile(int id)
    {
        super(Assets.roofCenterBigDark, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 16;
        tileBounds.width = 32; tileBounds.height = 16;
    }

    @Override
    public boolean IsSolid() { return true; }
}