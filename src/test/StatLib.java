package test;


public class StatLib {

    // simple average
    public static float avg(float[] x){
        float total =0;
        for(int i=0; i<x.length; i++){
            total = total + x[i];
        }
        total /= x.length;
        return total;
    }

    // returns the variance of X and Y
    public static float var(float[] x){
        float variance = 0;
        for (int i = 0; i < x.length; i++) {
            variance += Math.pow(x[i] - avg(x), 2);
        }
        variance /= x.length;
        return variance;
    }

    // returns the covariance of X and Y
    public static float cov(float[] x, float[] y){
        float covariance = 0;
        for (int i = 0; i < x.length; i++) {
            covariance += ((x[i]-avg(x))*(y[i]-avg(y)));
        }
        return covariance/x.length;
    }


    // returns the Pearson correlation coefficient of X and Y
    public static float pearson(float[] x, float[] y){
        float covariance = cov(x,y);
        float varX=(float)(Math.sqrt(var(x)));
        float varY=(float)(Math.sqrt(var(y)));
        return (covariance)/(varX*varY);
    }

    // performs a linear regression and returns the line equation
    public static Line linear_reg(Point[] points){
        float[] x=new float[points.length];
        float[] y=new float[points.length];

        for (int i = 0; i < points.length; i++) {
            x[i]=points[i].x;
            y[i]=points[i].y;
        }

        float a = cov(x,y)/var(x);
        float b= avg(y)-(a*(avg(x)));
        Line line = new Line(a,b);
        return line;
    }

    // returns the deviation between point p and the line equation of the points
    public static float dev(Point p,Point[] points){
        Line line=linear_reg(points);
        return dev(p,line);
    }

    // returns the deviation between point p and the line
    public static float dev(Point p,Line l){
        float num = Math.abs(((l.a*p.x)+l.b)-p.y);
        return num;
    }

}
