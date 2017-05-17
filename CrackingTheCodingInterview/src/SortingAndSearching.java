/**
 * Created by lanceji on 5/8/17.
 */
public class SortingAndSearching {
    int[] sortedMerge(int[] a, int lastAindex, int[] b){
        int lastBindex = b.length-1;
        int lastA = a.length-1;
        while(lastAindex >= 0 && lastBindex >= 0){
            if(b[lastBindex] > a[lastAindex]){
                a[lastA] = b[lastBindex--];
            }else {
                a[lastA] = a[lastAindex--];
            }
            lastA--;

        }
        while (lastAindex > 0){
            a[lastA--] = a[lastAindex--];
        }
        while (lastBindex > 0){
            a[lastA--] = b[lastBindex--];
        }
        return a;
    }

    public static void main(String[] args){
        SortingAndSearching test = new SortingAndSearching();
        int[] a = {1,5,7,9,15,16,19,-1,-1,-1,-1,-1};
        int[] b = {2,6,8,10,18};
        test.sortedMerge(a, 6, b);
    }
}
