package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofBigLightTile extends Tile
{
    public RoofBigLightTile(int id)
    {
        super(Assets.roofBigLight, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 0;
        tileBounds.width = 32; tileBounds.height = 32;
    }

    @Override
    public boolean IsSolid() { return true; }
}