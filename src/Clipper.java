import java.lang.Math;
public class Clipper {
    static int x_min;
    static int x_max;
    static int y_min;
    static int y_max;
    public Clipper(int x_min, int x_max, int y_min, int y_max){
        this.x_max=x_max;
        this.x_min=x_min;
        this.y_max=y_max;
        this.y_min=y_min;
    }
    public void clip(Line l){
        boolean[]b1=new boolean[4];
        boolean[]b2=new boolean[4];
        outcode(l.x1, l.y1, b1);
        outcode(l.x2, l.y2, b2);
        System.out.print("Outcode of ("+l.x1+", "+l.y1+") is: ");
        for(int i=b1.length-1; i>=0; i--){
            boolean b=b1[i];
            if(b){
                System.out.print(1);
            }
            else{
                System.out.print(0);
            }
        }
        System.out.print("\nOutcode of ("+l.x2+", "+l.y2+") is: ");
        for(int i=b2.length-1; i>=0; i--){
            boolean b=b2[i];
            if(b){
                System.out.print(1);
            }
            else{
                System.out.print(0);
            }
        }
        cohen_sutherland(b1, b2, l);
    }
    public static void outcode(int x, int y, boolean[]a){
        for(int i=0; i<a.length; i++){
            a[i]=false;
        }
        if(x<x_min) a[0]=true;
        if(x>x_max) a[1]=true;
        if(y<y_min) a[2]=true;
        if(y>y_max) a[3]=true;
    }
    public static void cohen_sutherland(boolean[] b1, boolean[] b2, Line l){
        System.out.println("\n___________________________________________________________________");
        while(true){
            boolean b=false;
            for(int i=0; i<=3; i++){
                b=b || b1[i] || b2[i];
            }
            boolean a=false;
            for(int i=0; i<=3; i++){
                a=a || (b1[i] && b2[i]);
            }
            boolean c=false;
            for(boolean z: b1){
                c=c || z;
            }

            if(!b){
                System.out.println("\n("+l.x1+", "+l.y1+") and "+"("+l.x2+", "+l.y2+") is clipped line.");
                break;
            }
            else if(a){
                System.out.println("The line is outside of the clip.");
                break;
            }
            else{
                if(c){
                    intersect_point1(l, b1);
                    System.out.print("\nOutcode of ("+l.x1+", "+l.y1+") is: ");
                    for(int i=b1.length-1; i>=0; i--){
                        boolean d=b1[i];
                        if(d){
                            System.out.print(1);
                        }
                        else{
                            System.out.print(0);
                        }
                    }
                }
                else{
                    intersect_point2(l, b2);
                    System.out.print("\nOutcode of ("+l.x2+", "+l.y2+") is: ");
                    for(int i=b2.length-1; i>=0; i--){
                        boolean d=b2[i];
                        if(d){
                            System.out.print(1);
                        }
                        else{
                            System.out.print(0);
                        }
                    }
                }
            }
            System.out.println();
            System.out.println("___________________________________________________________________");
        }
    }
    public static void intersect_point1(Line l, boolean[] b){
        if(b[0]){
            System.out.println("\nLeft intersection of point1");
            int lx1= l.x1;
            l.x1=x_min;
            l.y1=Math.round(l.y1+l.m*(x_min-lx1));
            //System.out.println("Without rounding: "+l.y1+l.m*(x_min-lx1));

        }
        else if(b[1]){
            System.out.println("\nRight intersection of point1");
            int lx1= l.x1;
            l.x1=x_max;
            l.y1=Math.round(l.y1+l.m*(x_max-lx1));
            //System.out.println("Without rounding: "+l.y1+l.m*(x_max-lx1));
        }
        else if(b[2]){
            System.out.println("\nBottom intersection of point1");
            int ly1=l.y1;
            l.y1=y_min;
            l.x1=Math.round(l.x1+(1/l.m)*(y_min-ly1));
            //System.out.println("Without rounding: "+l.x1+(1/l.m)*(y_min-ly1));
        }
        else if(b[3]){
            System.out.println("\nTop intersection of point1");
            int ly1=l.y1;
            l.y1=y_max;
            l.x1=Math.round(l.x1+(1/l.m)*(y_max-ly1));
            //System.out.println("Without rounding: "+(l.x1+(1/l.m)*(y_max-ly1)));
        }
        outcode(l.x1, l.y1, b);
    }
    public static void intersect_point2(Line l, boolean[] b){
        if(b[0]){
            System.out.println("\nLeft intersection of point2");
            int lx2= l.x2;
            l.x2=x_min;
            l.y2=Math.round(l.y2+l.m*(x_min-lx2));
            //System.out.println("Without rounding: "+l.y2+l.m*(x_min-lx2));
        }
        else if(b[1]){
            System.out.println("\nRight intersection of point2");
            int lx2= l.x2;
            l.x2=x_max;
            l.y2=Math.round(l.y2+l.m*(x_max-lx2));
            //System.out.println("Without rounding: "+l.y2+l.m*(x_max-lx2));
        }
        else if(b[2]){
            System.out.println("\nBottom intersection of point2");
            int ly2=l.y2;
            l.y2=y_min;
            l.x2=Math.round(l.x2+(1/l.m)*(y_min-ly2));
            //System.out.println("Without rounding: "+l.x2+(1/l.m)*(y_min-ly2));
        }
        else if(b[3]){
            System.out.println("\nTop intersection of point2");
            int ly2=l.y2;
            l.y2=y_max;
            l.x2=Math.round(l.x2+(1/l.m)*(y_max-ly2));
            //System.out.println("Without rounding: "+(l.x2+(1/l.m)*(y_max-ly2)));
            //l.x2=l.x2+(1/l.m)*(y_max-ly2);
        }
        outcode(l.x2, l.y2, b);
    }
}
