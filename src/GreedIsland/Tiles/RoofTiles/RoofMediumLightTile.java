package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofMediumLightTile extends Tile
{
    public RoofMediumLightTile(int id)
    {
        super(Assets.roofMediumLight, id);
        /// Setare bounds
        tileBounds.x = 8; tileBounds.y = 8;
        tileBounds.width = 32; tileBounds.height = 24;
    }

    @Override
    public boolean IsSolid() { return true; }
}