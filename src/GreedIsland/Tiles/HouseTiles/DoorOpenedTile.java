package GreedIsland.Tiles.HouseTiles;

import GreedIsland.Graphics.Assets;
import GreedIsland.Tiles.Tile;

public class DoorOpenedTile extends Tile
{
    public DoorOpenedTile(int id)
    {
        super(Assets.doorOpened, id);
    }

    @Override
    public boolean IsSolid() { return true; }
}