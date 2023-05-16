package uj.wmii.pwj.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public interface BattleshipGenerator {

    String generateMap();

    static BattleshipGenerator defaultInstance() {
        return new Board();
    }

}
class Board implements BattleshipGenerator
{
    private final int sizes=10;
    private final Character water='.';
    private final Character sh='#';
    Character[][] board=new Character[sizes][sizes];
    private class Point
    {
        private int x;
        private int y;
        private int hashCode;
        public Point(int x,int y)
        {
            this.x=x;
            this.y=y;
            this.hashCode= Objects.hash(x,y);

        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y && hashCode == point.hashCode;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    HashMap<Point, Boolean> mp;

    public String generateMap()
    {
        mp=new HashMap<Point, Boolean>();
        for(int i=0;i<sizes;i++)
        {
            for (int j=0;j<sizes;j++)
            {
                Point p=new Point(i,j);
                board[i][j]=water;

                mp.put(p,true);
            }
        }
        for(int i=0;i<4;i++)
        {
            makeShip(1,mp,board);
        }
        for(int i=0;i<3;i++)
        {
           makeShip(2,mp,board);

        }
        for(int i=0;i<2;i++)
        {
            makeShip(3,mp,board);

        }
        for(int i=0;i<1;i++)
        {
            makeShip(4,mp,board);

        }
        String ret="";
        for(int i=0;i<sizes;i++)
        {
            for (int j=0;j<sizes;j++)
            {
               ret+=board[i][j];
            }
        }
        return ret;
    }
    private ArrayList<Point> addShip(int sz, HashMap<Point, Boolean> mapka)
    {
        ArrayList<Point> ret=new ArrayList<Point>();
        ArrayList<Point> keys=new ArrayList<Point>(mapka.keySet());
        int indx=new Random().nextInt(keys.size());
        Point one;
        one=keys.get(indx);
        HashMap<Point, Boolean> temp=new HashMap<Point, Boolean>();
        ret.add(one);
        temp.put(one,true);
        keys=new ArrayList<Point>();
        for(int i=1;i<sz;i++)
        {
            keys.addAll(addPoint(one,mapka,temp));
            indx=new Random().nextInt(keys.size());
            one= keys.get(indx);
            ret.add(one);
            temp.put(one,true);
            keys.remove(indx);

        }
        return ret;
    }
    private ArrayList<Point> addPoint(Point pkt,HashMap<Point, Boolean> mapka,HashMap<Point, Boolean> curr)
    {
        ArrayList<Point> ret= new ArrayList<Point>();
        Point a=new Point(pkt.getX()-1,pkt.getY());
        Point b=new Point(pkt.getX()+1,pkt.getY());
        Point c=new Point(pkt.getX(),pkt.getY()-1);
        Point d=new Point(pkt.getX(),pkt.getY()+1);

        if(mapka.containsKey(a)==true && curr.containsKey(a)==false)
        {
            ret.add(a);
        }
        if(mapka.containsKey(b)==true &&curr.containsKey(b)==false)
        {
            ret.add(b);
        }
        if(mapka.containsKey(c)==true && curr.containsKey(c)==false)
        {
            ret.add(c);
        }
        if(mapka.containsKey(d)==true && curr.containsKey(d)==false)
        {
            ret.add(d);
        }
        return ret;
    }
    private void makeShip(int sz,HashMap<Point, Boolean> mapka,Character[][] b)
    {
        int temp=sz;
        ArrayList<Point> places;
        do {

            places=addShip(sz,mapka);
            if(places.size()==temp)
            {
                for (int j=0;j<sz;j++)
                {
                    b[places.get(j).getX()][places.get(j).getY()]=sh;
                    places.addAll(addtoClear(places.get(j),mapka));
                }
                //show();
            }
            for (var pkt:places)
            {
                mapka.remove(pkt);
            }
        }while(places.size()<temp);
    }
    private ArrayList<Point> addtoClear(Point pkt,HashMap<Point, Boolean> mapka)
    {
        ArrayList<Point> ret= new ArrayList<Point>();

        Point a=new Point(pkt.getX()-1,pkt.getY());
        Point b=new Point(pkt.getX()+1,pkt.getY());
        Point c=new Point(pkt.getX(),pkt.getY()-1);
        Point d=new Point(pkt.getX(),pkt.getY()+1);
        Point e=new Point(pkt.getX()-1,pkt.getY()-1);
        Point f=new Point(pkt.getX()-1,pkt.getY()+1);
        Point g=new Point(pkt.getX()+1,pkt.getY()-1);
        Point h=new Point(pkt.getX()+1,pkt.getY()+1);
        if(mapka.containsKey(a)==true )
        {
            ret.add(a);
        }
        if(mapka.containsKey(b)==true)
        {
            ret.add(b);
        }
        if(mapka.containsKey(c)==true)
        {
            ret.add(c);
        }
        if(mapka.containsKey(d)==true)
        {
            ret.add(d);
        }
        if(mapka.containsKey(e)==true )
        {
            ret.add(e);
        }
        if(mapka.containsKey(f)==true)
        {
            ret.add(f);
        }
        if(mapka.containsKey(g)==true)
        {
            ret.add(g);
        }
        if(mapka.containsKey(h)==true)
        {
            ret.add(h);
        }
        return ret;
    }
    private void show()
    {
        for(int i=0;i<this.sizes;i++)
        {
            for (int j=0;j<sizes;j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
/*class mainek
{
    public static void main(String[] args)
    {
        String ans=BattleshipGenerator.defaultInstance().generateMap();
        final int t=10;
        String[] show= new String[t];
        for(int i=0;i<t;i++)
        {
            show[i]=ans.substring(i*t,i*t+t);
        }
        for(int i=0;i<t;i++)
        {
            System.out.println(show[i]);
        }
    }
}*/
