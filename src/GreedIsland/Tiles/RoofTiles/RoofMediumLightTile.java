package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofMediumLightTile extends Tile
{
    public RoofMediumLightTile(int id)
    {
        super(Assets.roofMediumLight, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}