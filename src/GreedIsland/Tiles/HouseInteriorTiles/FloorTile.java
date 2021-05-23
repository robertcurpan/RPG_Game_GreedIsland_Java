package GreedIsland.Tiles.HouseInteriorTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class FloorTile extends Tile
{
    public FloorTile(int id)
    {
        super(Assets.floor, id);
        /// Setare bounds
        tileBounds.x = 12; tileBounds.y = 0;
        tileBounds.width = 12; tileBounds.height = 1;
    }

    @Override
    public boolean IsSolid() { return false; }
}
