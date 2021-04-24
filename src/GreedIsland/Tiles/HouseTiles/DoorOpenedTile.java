package GreedIsland.Tiles.HouseTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class DoorOpenedTile extends Tile
{
    public DoorOpenedTile(int id)
    {
        super(Assets.doorOpened, id);
        /// Setare bounds
        tileBounds.x = 0; tileBounds.y = 0;
        tileBounds.width = 32; tileBounds.height = 1;
    }

    @Override
    public boolean IsSolid() { return true; }
}