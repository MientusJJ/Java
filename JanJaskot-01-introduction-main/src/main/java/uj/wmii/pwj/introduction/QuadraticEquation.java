package uj.wmii.pwj.introduction;

public class QuadraticEquation {

    public double[] findRoots(double a, double b, double c) {
        double[] ret;
        double delta= b*b-4*a*c;
        if(delta<0)
        {
            ret=new double[0];
        }
        else if(delta==0.0)
        {
            ret=new double[1];
            ret[0]=-b/(2*a);
        }
        else
        {
            ret= new double[2];
            ret[0]=(-b+Math.sqrt(delta))/(2*a);
            ret[1]=(-b-Math.sqrt(delta))/(2*a);
        }
        return ret;
    }

}
