package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofMediumDarkTile extends Tile
{
    public RoofMediumDarkTile(int id)
    {
        super(Assets.roofMediumDark, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 8;
        tileBounds.width = 24; tileBounds.height = 24;
    }

    @Override
    public boolean IsSolid() { return true; }
}