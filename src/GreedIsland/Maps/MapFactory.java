package GreedIsland.Maps;

import GreedIsland.Maps.MapTiles.BaseAbstractMap;
import GreedIsland.Maps.MapTiles.Map1Scene1;
import GreedIsland.Maps.MapTiles.Map1Scene2;
import GreedIsland.Maps.MapTiles.MapNames;

import java.io.FileNotFoundException;

// FACTORY METHOD 1 //

//clasa ConcreteCreator din sablonul Factory -> aici "instantiem" obiecte concrete (Map1Scene1, Map1Scene2 etc..)
public class MapFactory extends BaseAbstractMapFactory
{
    // metoda factory
    @Override
    public BaseAbstractMap getMap(MapNames mapId) throws FileNotFoundException
    {
        BaseAbstractMap returnMap = null;

        switch(mapId)
        {
            case map1scene1: returnMap = Map1Scene1.getInstance(); break;
            case map1scene2: returnMap = Map1Scene2.getInstance(); break;
            default: break;
        }

        return returnMap;
    }
}
