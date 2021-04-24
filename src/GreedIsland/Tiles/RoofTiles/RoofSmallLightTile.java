package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofSmallLightTile extends Tile
{
    public RoofSmallLightTile(int id)
    {
        super(Assets.roofSmallLight, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 8;
        tileBounds.width = 32; tileBounds.height = 24;
    }

    @Override
    public boolean IsSolid() { return true; }
}