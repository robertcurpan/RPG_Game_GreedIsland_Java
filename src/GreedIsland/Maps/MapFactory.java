package GreedIsland.Maps;

import GreedIsland.Maps.MapTiles.BaseAbstractMap;
import GreedIsland.Maps.MapTiles.Map1Scene1;

import java.io.FileNotFoundException;

//clasa ConcreteCreator din sablonul Factory -> aici "instantiem" obiecte concrete (Map1Scene1, Map1Scene2 etc..)
public class MapFactory extends BaseAbstractMapFactory
{
    // metoda factory
    @Override
    public BaseAbstractMap getMap(int mapId) throws FileNotFoundException
    {
        BaseAbstractMap returnMap = null;

        switch(mapId)
        {
            case 11: returnMap = Map1Scene1.getInstance(); break;
            default: break;
        }

        return returnMap;
    }
}
