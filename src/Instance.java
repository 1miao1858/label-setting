import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Instance {
    String filename;
    int vehicle;
    int capacity;
    int[] id;
    int[]x;
    int[]y;
    int[]d;
    int[]a;
    int[]b;
    int[]s;
    double[][] reduced_cost;
    double[][] time;

    public Instance(String filepath) throws FileNotFoundException {
        File path=new File(filepath);
        Scanner scan=new Scanner(path);
        for (int i=0;i<=3;i++){
            scan.nextLine();
        }
        String line=scan.nextLine();
        String[]subs=line.split("         ");
        vehicle=Integer.parseInt(subs[0].trim());
        capacity=Integer.parseInt(subs[1].trim());
        for (int i=0;i<=3;i++){
            scan.nextLine();
        }
        id=new int[102];
        x=new int[102];
        y=new int[102];
        d=new int[102];
        a=new int[102];
        b=new int[102];
        s=new int[102];
        for (int i=0;i<=100;i++){
            line=scan.nextLine();
            subs=line.split("      ");
            id[i]=i;
            x[i]=Integer.parseInt(subs[1].trim());
            y[i]=Integer.parseInt(subs[2].trim());
            d[i]=Integer.parseInt(subs[3].trim());
            a[i]=Integer.parseInt(subs[4].trim());
            b[i]=Integer.parseInt(subs[5].trim());
            s[i]=Integer.parseInt(subs[6].trim());
        }
        id[101]=102;
        x[101]=x[0];
        y[101]=y[0];
        d[101]=d[0];
        a[101]=a[0];
        b[101]=b[0];
        s[101]=s[0];
        scan.nextLine();
        reduced_cost=new double[102][102];
        for (int i=0;i<102;i++){
            for (int j=0;j<102;j++){
                reduced_cost[i][j]=scan.nextDouble();
            }
        }
        time=new double[102][102];
        for (int i=0;i<101;i++){
            for (int j=i;j<101;j++){
                int deltax=x[i]-x[j];
                int deltay=y[i]-y[j];
                time[i][j]=Math.sqrt(Math.pow(deltax,2)+Math.pow(deltay,2));
                time[j][i]=time[i][j];
            }
        }
        for (int i=0;i<102;i++){
//            reduced_cost[i][101]=reduced_cost[i][0];
            time[i][101]=time[i][0];
        }
        for (int i=0;i<102;i++){
//            reduced_cost[101][i]=reduced_cost[101][0];
            time[101][i]=time[101][i];
        }
    }
}
