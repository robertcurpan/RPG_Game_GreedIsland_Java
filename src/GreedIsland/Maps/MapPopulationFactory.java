package GreedIsland.Maps;

import GreedIsland.Maps.MapPopulation.Map1Scene1Population;
import GreedIsland.Maps.MapPopulation.Map1Scene2Population;
import GreedIsland.Maps.MapPopulation.MapPopulation;
import GreedIsland.Maps.MapTiles.BaseAbstractMap;
import GreedIsland.Maps.MapTiles.Map1Scene1;
import GreedIsland.Maps.MapTiles.Map1Scene2;
import GreedIsland.Maps.MapTiles.MapNames;
import GreedIsland.RefLinks;

import java.io.FileNotFoundException;

// FACTORY METHOD 2 //

//clasa ConcreteCreator din sablonul Factory -> aici "instantiem" obiecte concrete (Map1Scene1, Map1Scene2 etc..)
public class MapPopulationFactory extends BaseAbstractMapPopulationFactory
{
    // metoda factory
    @Override
    public MapPopulation getMapPopulation(MapNames mapId, RefLinks refLink) throws FileNotFoundException
    {
        MapPopulation returnMapPopulation = null;

        switch(mapId)
        {
            case map1scene1: returnMapPopulation = Map1Scene1Population.getInstance(refLink); break;
            case map1scene2: returnMapPopulation = Map1Scene2Population.getInstance(refLink); break;
            default: break;
        }

        return returnMapPopulation;
    }
}
