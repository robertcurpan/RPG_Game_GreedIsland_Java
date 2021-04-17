package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofCenterSmallLightTile extends Tile
{
    public RoofCenterSmallLightTile(int id)
    {
        super(Assets.roofCenterSmallLight, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}