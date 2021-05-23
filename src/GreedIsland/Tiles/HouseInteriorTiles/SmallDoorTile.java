package GreedIsland.Tiles.HouseInteriorTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class SmallDoorTile extends Tile
{
    public SmallDoorTile(int id)
    {
        super(Assets.smallDoor, id);
        /// Setare bounds
        tileBounds.x = 12; tileBounds.y = 0;
        tileBounds.width = 12; tileBounds.height = 1;
    }

    @Override
    public boolean IsSolid() { return true; }
}