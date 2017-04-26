package io.openmessaging.demo;
import java.util.Scanner;

/**
 * Created by E450C on 2017/4/26.
 */

class MyFilter {
    /*
    ‘?’ 匹配一个字符

    ‘*’ 匹配任意连串的字符*/
    public char patternOne = "?".charAt(0);
    char patternAll = "*".charAt(0);

    public static void main(String[] args) {
        MyFilter filter = new MyFilter();
        Scanner s=new Scanner(System.in);//线上测试应该是一个System.in，格式为用空格把两个参数分开
        Scanner s2=new Scanner(System.in);//注意点开编程说明，切记

        while(s.hasNext()&&s2.hasNext()){
            System.out.println(filter.filter(s.next(),s2.next()));
        }
     //   System.out.println(filter.filter("8f24","8?*4"));
    }

    public int filter(String tradeDone, String pattern) {
        char[] patterns = pattern.toCharArray();
        char[] tradeDones = tradeDone.toCharArray();


        //int length = patterns.length > tradeDones.length ? tradeDones.length : pattern.length();
        int length=tradeDone.length();
        for (int checkNum = 0, checkNum2 = 0; checkNum< tradeDones.length&&checkNum2<pattern.length(); checkNum++,checkNum2++) {

            if (Character.compare(tradeDones[checkNum], patterns[checkNum2]) != 0) {

                if (Character.compare(patterns[checkNum2], patternOne) == 0) {

                    continue;
                }
                if (Character.compare(patterns[checkNum2], patternAll) == 0) {
                        if(checkNum2+1>=pattern.length()-1){
                            return 1;
                        }
                    char nextPattern=patterns[checkNum2+1];
                    int nextIndex=checkNum+1;
                    checkNum=patternAll(nextPattern,nextIndex,tradeDones);
                }
                return 0;
            }
                     if(checkNum==length-1){
                         return 1;
                     }
        }
        return 0;
    }
    public int  patternAll(char nextPattern,int next,char[] tradeDone){
        int checkNum=next;
        for(;checkNum<tradeDone.length;checkNum++){
            if(Character.compare(tradeDone[checkNum],nextPattern)==0){
                return checkNum;
            }
        }
        return checkNum;
    }
}