package GreedIsland.Maps;

import GreedIsland.Maps.MapTiles.BaseAbstractMap;

import java.io.FileNotFoundException;

// clasa Creator din sablonul Factory
public abstract class BaseAbstractMapFactory
{
    // metoda factory (va fi suprascrisa in clasa MapFactory (Concrete Creator) )
    public abstract BaseAbstractMap getMap(int mapId) throws FileNotFoundException;
}
