package GreedIsland.Tiles;

import GreedIsland.Graphics.Assets;

public class BarrelTile extends Tile
{
    public BarrelTile(int id)
    {
        super(Assets.barrel, id);
        /// Setare bounds
        tileBounds.x = 12; tileBounds.y = 0;
        tileBounds.width = 12; tileBounds.height = 1;
    }

    @Override
    public boolean IsSolid() { return true; }
}