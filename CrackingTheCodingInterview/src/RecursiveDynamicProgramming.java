import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanceji on 5/7/17.
 */
public class RecursiveDynamicProgramming {
    void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
        if (leftRem < 0 || rightRem < leftRem)
            return;
        if (leftRem == 0 && rightRem == 0) {
            list.add(String.copyValueOf(str));
        } else {
            str[index] = '(';
            addParen(list, leftRem - 1, rightRem, str, index + 1);
            str[index] = ')';
            addParen(list, leftRem, rightRem - 1, str, index + 1);
        }
    }
    ArrayList<String> generateParens(int count) {
        char[] str = new char[count*2];
        ArrayList<String> list = new ArrayList<String>();
        addParen(list, count, count, str, 0);
        return list;
    }

    List<String> getValidParen(int n){
        List<String> result = new ArrayList<String>();
        if(n==1){
            result.add("()");
            return result;
        }
        List<String> subParen = getValidParen(n-1);
        result = combine(subParen);
        return result;
    }

    List<String> combine(List<String> input){
        List<String> result = new ArrayList<String>();
        for(String s: input){
            result.add("()" + s);
            result.add(s + "()");
            result.add("(" + s + ")");
        }
        result.remove(0);
        return result;
    }

    int Coins(int n){
        HashMap<Integer,Integer> solutionTrack = new HashMap<Integer, Integer>();
        return countSolutions(n, solutionTrack);
    }

    int countSolutions(int n, Map<Integer,Integer> track){
        if(n<0)
            return 0;
        else if(n==0){
            track.put(0,1);
            return 1;
        }else{
            int result = (track.get(n-25)==null?countSolutions(n-25,track):track.get(n-25))
                    + (track.get(n-10)==null?countSolutions(n-10,track):track.get(n-10))
                    + (track.get(n-5)==null?countSolutions(n-5,track):track.get(n-5))
                    + (track.get(n-1)==null?countSolutions(n-1,track):track.get(n-1));
            track.put(n,result);
            return result;
        }
    }

    public static void main(String[] args){
        RecursiveDynamicProgramming test = new RecursiveDynamicProgramming();
        /*List<String> result = test.getValidParen(4);
        for(String s:result)
            System.out.println(s);*/
        System.out.println(test.Coins(10));
    }
}
