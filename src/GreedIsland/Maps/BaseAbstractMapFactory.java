package GreedIsland.Maps;

import GreedIsland.Maps.MapTiles.BaseAbstractMap;
import GreedIsland.Maps.MapTiles.MapNames;

import java.io.FileNotFoundException;

// FACTORY METHOD 1 //

// clasa Creator din sablonul Factory
public abstract class BaseAbstractMapFactory
{
    // metoda factory (va fi suprascrisa in clasa MapFactory (Concrete Creator) )
    public abstract BaseAbstractMap getMap(MapNames mapId) throws FileNotFoundException;
}
