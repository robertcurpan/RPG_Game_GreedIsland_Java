package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofCenterSmallDarkTile extends Tile
{
    public RoofCenterSmallDarkTile(int id)
    {
        super(Assets.roofCenterSmallDark, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 0;
        tileBounds.width = 16; tileBounds.height = 32;
    }

    @Override
    public boolean IsSolid() { return true; }
}