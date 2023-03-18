public class PaperFolding {

    //  i代表此次是第几次折
    //  N代表最大的对折次数
    //  down = true,代表凹
    public static void process(int i,int N,boolean down){
        if (i > N){
            return;
        }
        process(i+1,N,true);
        System.out.println(down ? "凹" : "凸");
        process(i+1,N,false);
    }

    public static void printAllFolds(int N){
        process(1,N,true);
    }
}
