import java.util.HashMap;
import java.util.List;

/**
 * Created by lanceji on 5/15/17.
 */
public class LeetCode20 {
    public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) {
             val = x;
         }
    }

    //1. two sum
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if(nums==null || nums.length==0){
            return result;
        }
        HashMap<Integer,Integer> indexMap = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(indexMap.get(nums[i])!=null){
                result[0]=indexMap.get(nums[i]);
                result[1]=i;
                return result;
            }
            else{
                indexMap.put(target-nums[i],i);
            }
        }
        return result;
    }

    //2. add two numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {//reverse order, (2->4->3) + (5->6->4) = (7->0->8)
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        ListNode dummy = new ListNode(0);
        ListNode resultTail = dummy;
        int carry=0,sum=0;
        while(l1!=null || l2!=null || carry!=0){
            ListNode newNode = new ListNode(0);
            sum = (l1==null?0:l1.val) + (l2==null?0:l2.val) + carry;
            carry = sum / 10;
            sum %= 10;
            newNode.val = sum;
            resultTail.next = newNode;
            resultTail = resultTail.next;
            l1 = (l1==null)?l1:l1.next;
            l2 = (l2==null)?l2:l2.next;
        }
        return dummy.next;
    }

    //3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0)
            return 0;
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        int result=0,resultStart=0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            Integer prevIndex = map.get(c);
            if(prevIndex!=null){
                resultStart = Math.max(resultStart, prevIndex+1);
            }
            map.put(c,i);
            result = Math.max(i-resultStart+1, result);
        }
        return result;
    }

    //5. Longest Palindromic Substring
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    //6. ZigZag Conversion
    public String convert(String s, int numRows){
        StringBuffer[] bufferArray = new StringBuffer[numRows];
        for(int index=0;index<numRows;index++){
            bufferArray[index] = new StringBuffer();
        }
        int i=0,l=s.length();
        while(i<l){
            for(int j=0;j<numRows && i<l;j++){
                bufferArray[j].append(s.charAt(i++));
            }
            for(int j=numRows-2;j>0 && i<l;j--){
                bufferArray[j].append(s.charAt(i++));
            }
        }
        for(int j=1;j<numRows;j++){
            bufferArray[0].append(bufferArray[j]);
        }

        return bufferArray[0].toString();
    }

    //7. reverse integer
    public int reverse(int x) {
        int sign = x<0?-1:1;
        int value = x<0?(x*-1):x;
        int res = 0;
        while(value>0){
            if(Integer.MAX_VALUE/10 < res || (sign==-1 && Integer.MIN_VALUE/10 > (res * -1))){
                return 0;
            }
            res = res*10 + value%10;
            value /= 10;
        }
        return (sign * res);
    }

    //8. String to Integer(atoi)
    public int myAtoi(String str) {
        if(str == null || str.length()==0)
            return 0;
        char[] charArray = str.toCharArray();
        int res=0,sign=1,index=0;
        while(charArray[index]==' ' && index<charArray.length){
            index++;
        }
        if(charArray[index] == '+' || charArray[index] == '-'){
            sign = charArray[index]=='-'?-1:1;
            index++;
        }
        while(index < charArray.length){
            int digit = charArray[index] - '0';
            if(digit>9 || digit<0)
                break;
            if(Integer.MAX_VALUE/10 < res || (Integer.MAX_VALUE/10 == res && Integer.MAX_VALUE % 10 < digit)) {
                return sign==1?Integer.MAX_VALUE: Integer.MIN_VALUE;
            }
            res = res * 10 + digit;
            index++;
        }
        return res * sign;
    }

    //9 Palindrome Number
    public boolean isPalindrome(int x) {
        if(x<0 || (x%10==0 && x!=0)){
            return false;
        }
        int reverse =0;
        while(x>reverse){
            reverse = reverse*10 + x%10;
            x = x/10;
        }
        return (x==reverse || x==reverse/10);
    }

    //11. Container with most water
    public int maxArea(int[] height) {
        int left = 0,right=height.length-1;
        int max = 0;
        while (left<right) {
            max = Math.max((right - left) * Math.min(height[left], height[right]),max);
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return max;
    }

    //12. Integer to Roman
    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100]+ X[(num%100)/10] + I[num%10];
    }

    //14.Longest Common Prefix
    public String longestCommonPrefix(String[] strs) {
        if(strs==null || strs.length==0)
            return "";
        if(strs.length==1)
            return strs[0];
        StringBuilder sb = new StringBuilder();
        int pos;
        for(int i=0;i<strs[0].length();i++){
            for(pos=1;pos<strs.length;pos++){
                if(i>=strs[pos].length() || (strs[pos].charAt(i) != strs[0].charAt(i))){
                    break;
                }
            }
            if(pos==strs.length)
                sb.append(strs[0].charAt(i));
            else
                break;
        }
        return sb.toString();
    }

    //15. 3sum
    public List<List<Integer>> threeSum(int[] nums) {

    }
    public static void main(String[] args){
        LeetCode20 test = new LeetCode20();

        //1. two sum
        /*int[] tempArray = {3,2,4};
        test.twoSum(tempArray, 6);*/


        /*ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(6);
        ListNode node3 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(0);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(3);
        node4.next = node5;
        node5.next = node6;
        ListNode result2 = test.addTwoNumbers(node1, node4);
        while(result2!=null){
            System.out.print(result2.val + " ");
            result2 = result2.next;
        }*/

//        test.myAtoi("010");

        test.longestCommonPrefix(new String[]{"aca","cba"});
    }


}
