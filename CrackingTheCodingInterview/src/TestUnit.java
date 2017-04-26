/**
 * Created by lanceji on 4/17/17.
 */
public class TestUnit {
    static boolean isUniqueChars1(String input){
        //assume there is only lower-cased characters
        //since there is only 26 possibilities, we can just use a integer to check(32 bit)
        int checker = 0;
//        String lowercasedInput = input.toLowerCase();
        for(int i=0;i<input.length();i++){
            int value = input.charAt(i) - 'a';
            if( (checker & (1 << value)) != 0)
                return false;
            checker |= (1 << value);
        }
        return true;
    }

    static boolean isUniqueChars2(String input){
        //assume input contains all possible characters in ASCII table, there are 128 possibilities
        if(input==null || input.length() > 128)
            return false;
        boolean[] checkArray = new boolean[128];
        for(char c: input.toCharArray()){
            if(checkArray[c])
                return false;
            checkArray[c] = true;
        }
        return true;
    }

    static boolean checkPermutation(String input1, String input2){
        if(input1==null || input2 == null || input1.length() != input2.length())
            return  false;
        int[] charCount = new int[128];
        for(char c:input1.toCharArray()){
            charCount[c]++;
        }
        for(char c:input2.toCharArray()){
            charCount[c]--;
            if(charCount[c]<0)
                return false;
        }
        return true;
    }

    static String URLift(String input, int realLength){
        return input;
    }

    static int clearBitsMSBthroughI(int num, int i) {
        int mask =(1 << i) - 1;
        return num & mask;
    }

    static int clearBitslthrough0(int num, int i) {
        int mask =(-1 << (i + 1));
        return num &mask;
       }

    public static void main(String[] args){
        /*System.out.println(clearBitsMSBthroughI(15, 2));
        System.out.println(clearBitslthrough0(15, 2));*/
        System.out.println(checkPermutation("abc","cba"));
    }
}
