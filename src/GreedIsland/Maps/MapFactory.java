package GreedIsland.Maps;

import GreedIsland.Maps.MapTiles.*;

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
            case map1scene3: returnMap = Map1Scene3.getInstance(); break;
            case map1scene4: returnMap = Map1Scene4.getInstance(); break;
            case map1scene5: returnMap = Map1Scene5.getInstance(); break;
            case map1scene6: returnMap = Map1Scene6.getInstance(); break;
            case map1scene7: returnMap = Map1Scene7.getInstance(); break;
            case map1scene8: returnMap = Map1Scene8.getInstance(); break;
            case map2scene1: returnMap = Map2Scene1.getInstance(); break;
            case map2scene2: returnMap = Map2Scene2.getInstance(); break;
            case map2scene3: returnMap = Map2Scene3.getInstance(); break;
            case map2scene4: returnMap = Map2Scene4.getInstance(); break;
            case map2scene5: returnMap = Map2Scene5.getInstance(); break;
            case map2scene6: returnMap = Map2Scene6.getInstance(); break;
            case map2scene7: returnMap = Map2Scene7.getInstance(); break;
            case map2scene8: returnMap = Map2Scene8.getInstance(); break;
            default: break;
        }

        return returnMap;
    }
}
