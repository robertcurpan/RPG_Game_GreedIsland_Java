package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofCenterBigLightTile extends Tile
{
    public RoofCenterBigLightTile(int id)
    {
        super(Assets.roofCenterBigLight, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}