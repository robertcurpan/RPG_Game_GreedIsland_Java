package GreedIsland.Tiles.RoofTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class RoofMediumDarkTile extends Tile
{
    public RoofMediumDarkTile(int id)
    {
        super(Assets.roofMediumDark, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}