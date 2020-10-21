public class Line {
    int x1;
    int y1;
    int x2;
    int y2;
    float m;
    public Line(int x1, int y1, int x2, int y2){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.m=((float)y2-(float)y1)/((float)x2-(float)x1);
        //this.m=(y2-y1)/(x2-x1);
        //System.out.println(this.m);
    }
}
