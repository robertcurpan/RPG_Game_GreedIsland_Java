package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofSmallLightTile extends Tile
{
    public RoofSmallLightTile(int id)
    {
        super(Assets.roofSmallLight, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}