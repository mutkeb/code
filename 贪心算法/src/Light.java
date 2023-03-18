public class Light {

    public static int minLight(String road){
        char[] chars = road.toCharArray();
        int i = 0;
        int light = 0;
        while (i < chars.length){
            if (chars[i] == 'X'){
                i++;
            }else {
                light++;
                if (i + 1 == chars.length){
                    break;
                }
                if (chars[i+1] == 'X'){
                    i = i + 2;
                }else {
                    i = i + 3;
                }
            }
        }
        return light;
    }

    //  暴力解法
}
