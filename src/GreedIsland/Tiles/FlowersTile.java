package GreedIsland.Tiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class FlowersTile extends Tile
{
    public FlowersTile(int id)
    {
        super(Assets.flowers, id);
        /// Setare bounds
        tileBounds.x = 12; tileBounds.y = 0;
        tileBounds.width = 12; tileBounds.height = 1;
    }

    @Override
    public boolean IsSolid() { return false; }
}

