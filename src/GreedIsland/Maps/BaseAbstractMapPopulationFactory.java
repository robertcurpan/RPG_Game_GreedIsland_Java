package GreedIsland.Maps;

import GreedIsland.Maps.MapPopulation.MapPopulation;
import GreedIsland.Maps.MapTiles.BaseAbstractMap;
import GreedIsland.Maps.MapTiles.MapNames;
import GreedIsland.RefLinks;

import java.io.FileNotFoundException;

// FACTORY METHOD 2 //

// clasa Creator din sablonul Factory
public abstract class BaseAbstractMapPopulationFactory
{
    // metoda factory (va fi suprascrisa in clasa MapPopulationFactory (Concrete Creator) )
    public abstract MapPopulation getMapPopulation(MapNames mapId, RefLinks refLink) throws FileNotFoundException;
}