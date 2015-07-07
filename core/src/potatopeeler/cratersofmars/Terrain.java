package potatopeeler.cratersofmars;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by kienme on 10/2/15.
 *
 * Generate and handle terrain.
 *
 * TO DO: ELIMINATE VARIABLE DECLARATIONS
 *
 */

public class Terrain {

    private Random random;

    private int craterFrom[];
    private int craterTo[];
    private int craterAmp[];
    private int craterWidth[];

    public Terrain() {

        random=new Random();

        craterFrom=new int[3];
        craterTo=new int[3];
        craterAmp=new int[3];
        craterWidth=new int[3];

        craterFrom[0]=200;  craterFrom[1]=800;  craterFrom[2]=1700;
        craterTo[0]=600;    craterTo[1]=1400;   craterTo[2]=2100;
        craterAmp[0]=100;   craterAmp[1]=150;   craterAmp[2]=200;
        craterWidth[0]=400; craterWidth[1]=600; craterWidth[2]=300;
    }

    public void update(float delta, float speed) {

        for(int i=0; i<3; ++i) {
            craterFrom[i]-=(speed*delta);
            craterTo[i]-=(speed*delta);
        }

        if(craterTo[0]<(-1*getCraterWidth(0))/4)
            recycleCrater(0);
        else if(craterTo[1]<(-1*getCraterWidth(1))/4)
            recycleCrater(1);
        else if(craterTo[2]<(-1*getCraterWidth(2))/4)
            recycleCrater(2);
    }

    public int generateBoundary(int index, int pointX) {

        /*
        * This function uses the mathematical function sin(acos(x)) to generate curves.
        * Only the y value corresponding to pointX is returned.
        *
        * Since domain of sin(acos(x)) is limited to [-1, 1], the position values
        * (of the area over which the curve is drawn) have to be mapped to the domain of the function.
        *
        * This is done by dividing [-1, 1] to as many number of parts as there are pixels
        * in the region over which it is to be drawn. Then mappedX is fed into the function.
        * */

        if(!((index>=0)&&(index<=2)))
        return Constants.TERRAIN_Y_LEVEL;

        float divisions=craterTo[index]-craterFrom[index];
        float unit=2/divisions;
        float mappedX=((pointX-craterFrom[index])*unit)-1;

        return (int)(craterAmp[index]*Math.sin(Math.acos(mappedX)))+Constants.TERRAIN_Y_LEVEL;
    }

    private void recycleCrater(int i) {

        int craterDia=random.nextInt(500)+400;
        int craterHeight=random.nextInt(50)+150;

        if(i==0)
            craterFrom[i]=craterTo[2]+(getCraterWidth(2)/2);
        else
            craterFrom[i]=craterTo[i-1]+(getCraterWidth(i-1)/2);

        craterTo[i]=craterFrom[i]+craterDia;
        craterAmp[i]=craterHeight;
        craterWidth[i]=craterDia;
    }

    public int craftInCrater(int point) {

        for(int i=0; i<3; ++i) {
            if(((craterFrom[i]/*+Constants.HOVER_HEIGHT*/)<=point)&&((craterTo[i]/*-Constants.HOVER_HEIGHT*/)>=point))
                return i;
        }

        return -1;
    }



    public int getCraterWidth(int i) {
        return craterWidth[i];
         //(craterTo[i]-craterFrom[i]);
    }

    public int getCraterFrom(int i) {
        if(i<3) return craterFrom[i];
        return -1;
    }

    public int getCraterTo(int i) {
        if(i<3) return craterTo[i];
        return -1;
    }
}