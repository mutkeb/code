public class Code {
    //  位运算加法
    public static int add(int a, int b){
        //  先用或运算算出无进位信息，再用与运算算出进位信息
        int sum = a;
        while (b != 0){
            b = (a & b) << 1;
            sum |= b;
            a = sum;
        }
        return sum;
    }
    //  位运算减法
    public static int minus(int a,int b){
        return add(a,add(~b,1));
    }
    //  位运算乘法
    public static int multi(int a,int b){
        int sum = 0;
        while (b != 0){
            if ((b & 1) != 0){
                sum = add(sum,a);
            }
            b >>= 1;
            a <<= 1;
        }
        return sum;
    }

    //  位运算除法
    public static int div(int a,int b){
        int sum = 0;
        int x = isNeg(a) ? a : negNum(a);
        int y = isNeg(b) ? b : negNum(b);
        int res = 0;
        for (int i = 30; i >= 0 ; i--) {
            if (x >= (y << i)){
                //  说明可以除
                x = minus(x,y<<i);
                res |= (1 << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int a,int b){
        int res = 0;
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE){
            return 1;
        }else if (b == Integer.MIN_VALUE){
            return 0;
        }else if (a == Integer.MIN_VALUE){
            if (b == negNum(1)){
                return Integer.MAX_VALUE;
            }
            int x = div(add(a,1),b);
            return add(x,divide(minus(a,multi(x,b)),b));
        }else {
            return div(a,b);
        }
    }
    public static int negNum(int n){
        return add(~n,1);
    }


    public static boolean isNeg(int n){
        return n < 0;
    }


}
