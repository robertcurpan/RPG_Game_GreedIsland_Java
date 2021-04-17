package GreedIsland.Tiles.HouseTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class DoorClosedTile extends Tile
{
    public DoorClosedTile(int id)
    {
        super(Assets.doorClosed, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}