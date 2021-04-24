package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofCenterSmallLightTile extends Tile
{
    public RoofCenterSmallLightTile(int id)
    {
        super(Assets.roofCenterSmallLight, id);
        /// Setare bounds
        tileBounds.x = 8; tileBounds.y = 0;
        tileBounds.width = 24; tileBounds.height = 32;
    }

    @Override
    public boolean IsSolid() { return true; }
}