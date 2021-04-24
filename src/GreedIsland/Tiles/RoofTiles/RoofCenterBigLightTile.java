package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofCenterBigLightTile extends Tile
{
    public RoofCenterBigLightTile(int id)
    {
        super(Assets.roofCenterBigLight, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 16;
        tileBounds.width = 32; tileBounds.height = 16;
    }

    @Override
    public boolean IsSolid() { return true; }
}