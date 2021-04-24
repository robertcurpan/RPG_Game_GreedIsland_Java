package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofBigDarkTile extends Tile
{
    public RoofBigDarkTile(int id)
    {
        super(Assets.roofBigDark, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 0;
        tileBounds.width = 32; tileBounds.height = 32;
    }

    @Override
    public boolean IsSolid() { return true; }
}