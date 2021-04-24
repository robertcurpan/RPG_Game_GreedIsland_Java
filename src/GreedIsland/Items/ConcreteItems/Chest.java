package GreedIsland.Items.ConcreteItems;

import GreedIsland.Graphics.Assets;
import GreedIsland.Items.Item;
import GreedIsland.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

// Itemele Concrete reprezinta acele Iteme cu care putem interactiona. Momentan, la apasarea tastei E langa un chest, acesta va disparea
// Ulterior vom face implementari mai complexe pt astfel de actiuni.
public class Chest extends Item
{
    private BufferedImage image;

    public Chest(RefLinks refLink, float x, float y, int width, int height)
    {
        super(refLink, x, y, width, height);
        image = Assets.chest;
        /// Setare bounds
        bounds.x = 12; bounds.y = 0;
        bounds.width = 8; bounds.height = 1;
    }

    @Override
    public void Update()
    {

    }

    @Override
    public void Draw(Graphics g)
    {
            /// Afisam chestul
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    /*! \fn public void DoAction()
        \brief Defineste actiunea ce trebuie executata de un item atunci cand se apasa o tasta in apropierea lui.
                (Itemele = acele obiecte cu care se poate interactiona prin apasarea unei taste, nu doar prin coliziune)
     */
    @Override
    public void DoAction()
    {
        /// Actiunea ce trebuie "executata" atunci cand se apasa o tasta langa un item (apasarea tastei e verificata in clasa Hero, nu aici)
        System.out.println("Apasat tasta E langa chest!!");
        refLink.GetMap().getMapPopulation().getItems().remove(this); // Am sters chest-ul din lista de iteme aferente primei scene din prima harta
    }

}
