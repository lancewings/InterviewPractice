/**
 * Created by lanceji on 4/17/17.
 */
public class StringsAndArray {
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

    boolean OneAway(String str1, String str2){
        int diff = Math.abs(str1.length() - str2.length());
        if(diff >= 2)
            return false;
        else{
            return checkOneWay(str1, str2, str1.length() - str2.length());
        }
    }

    boolean checkOneWay(String str1, String str2, int lengthDiff){
        int countDiff = 0;
        int i=0,j=0;
        while(i < str1.length() && j< str2.length()){
            if(str1.charAt(i) != str2.charAt(j)){
                countDiff++;
                if(countDiff >= 2)
                    return false;
                if(lengthDiff == 0){
                    i++;j++;
                }else if(lengthDiff > 0){
                    i++;
                }else{
                    j++;
                }
            }
            else{
                i++;j++;
            }
        }
        return (countDiff <= 1);
    }

    boolean isRotation(String str1, String str2){
        if(str1 != null && str2 != null && str1.length() == str2.length() && str1.length() > 0){
            String str1str1 = str1 + str1;
            return str1str1.indexOf(str2) >= 0;
        }
        return false;
    }


    String stringCompression(String input){
        if(input==null || input.length() ==0)
            return input;
        char prev = input.charAt(0),cur='\0';
        int index = 1,count = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(prev);
        while(index < input.length()){
            cur = input.charAt(index);
            if(cur == prev)
                count++;
            else{
                sb.append(count);
                sb.append(cur);
                count = 1;
                prev = cur;
            }
            index++;
        }

        return sb.length()>=input.length()?input:sb.toString();
    }

    int[][] rotateMatrix(int[][] matrix){//left rotate 90 degrees
        if(matrix.length == 0 || matrix.length != matrix[0].length)
            return null;
        int n = matrix.length;
        for(int layer=0;layer < n/2;layer++){
            int first = layer;
            int last = n -1 - layer;
            for(int i=first;i<last;i++){
                int offset = i-first;
                int top = matrix[first][i];

                matrix[first][i] = matrix[last-offset][first];//left -> top
                matrix[last-offset][first] = matrix[last][last - offset];//bottom -> left
                matrix[last][last-offset] = matrix[i][last];//right->bottom
                matrix[i][last]= top;//right<- saved top
            }
        }
        return matrix;
    }


    public static void main(String[] args){
        /*System.out.println(clearBitsMSBthroughI(15, 2));
        System.out.println(clearBitslthrough0(15, 2));*/
        System.out.println(checkPermutation("abc","cba"));
    }
}
