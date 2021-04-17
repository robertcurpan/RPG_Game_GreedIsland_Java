package GreedIsland.Tiles;

import GreedIsland.Graphics.Assets;

public class BarrelTile extends Tile
{
    public BarrelTile(int id)
    {
        super(Assets.barrel, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}