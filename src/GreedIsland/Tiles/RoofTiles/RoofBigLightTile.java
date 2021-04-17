package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofBigLightTile extends Tile
{
    public RoofBigLightTile(int id)
    {
        super(Assets.roofBigLight, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}