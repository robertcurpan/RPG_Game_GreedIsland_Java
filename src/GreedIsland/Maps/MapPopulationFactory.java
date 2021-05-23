package GreedIsland.Maps;

import GreedIsland.Maps.MapPopulation.*;
import GreedIsland.Maps.MapTiles.*;
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
            case map1scene3: returnMapPopulation = Map1Scene3Population.getInstance(refLink); break;
            case map1scene4: returnMapPopulation = Map1Scene4Population.getInstance(refLink); break;
            case map1scene5: returnMapPopulation = Map1Scene5Population.getInstance(refLink); break;
            case map1scene6: returnMapPopulation = Map1Scene6Population.getInstance(refLink); break;
            case map1scene7: returnMapPopulation = Map1Scene7Population.getInstance(refLink); break;
            case map1scene8: returnMapPopulation = Map1Scene8Population.getInstance(refLink); break;
            case map2scene1: returnMapPopulation = Map2Scene1Population.getInstance(refLink); break;
            case map2scene2: returnMapPopulation = Map2Scene2Population.getInstance(refLink); break;
            case map2scene3: returnMapPopulation = Map2Scene3Population.getInstance(refLink); break;
            case map2scene4: returnMapPopulation = Map2Scene4Population.getInstance(refLink); break;
            case map2scene5: returnMapPopulation = Map2Scene5Population.getInstance(refLink); break;
            case map2scene6: returnMapPopulation = Map2Scene6Population.getInstance(refLink); break;
            case map2scene7: returnMapPopulation = Map2Scene7Population.getInstance(refLink); break;
            case map2scene8: returnMapPopulation = Map2Scene8Population.getInstance(refLink); break;
            default: break;
        }

        return returnMapPopulation;
    }
}
