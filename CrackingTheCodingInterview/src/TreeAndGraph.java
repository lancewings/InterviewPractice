import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by lanceji on 5/5/17.
 */
public class TreeAndGraph {
    class BinaryNode{
        int value;
        BinaryNode left;
        BinaryNode right;

        public BinaryNode(int value){
            this.value = value;
            left = null;
            right = null;
        }

        public BinaryNode(){}

    }

    class GraphNode<T>{
        T value;
        GraphNode[] neighbors;

        public GraphNode(T value){
            this.value = value;
            neighbors = null;
        }
    }

    public static void main(String[] args){

    }


    BinaryNode randomNode(BinaryNode root, int random){
        if(root == null)
            return null;
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
        BinaryNode cur = root;

        return cur;
    }


    int pathSumIII(BinaryNode root, int target){
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.put(0,1);
        return pathSumHelperIII(root, 0, target, map);
    }

    int pathSumHelperIII(BinaryNode node, int curSum, int target, HashMap<Integer, Integer> map){
        if(node==null)
            return 0;
        curSum += node.value;
        int result = map.get(curSum-target)==null?0:map.get(curSum-target);
        map.put(curSum, (map.get(curSum)==null?0:map.get(curSum)) + 1);
        result += pathSumHelperIII(node.left, curSum, target, map) + pathSumHelperIII(node.right, curSum, target, map);
        map.put(curSum, map.get(curSum) - 1);
        return result;
    }

    void inOrderWitStack(BinaryNode root){
        if(root == null)
            return;
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
        BinaryNode node = root;
        while(node != null){
            stack.push(node);
            node = node.left;
        }

        while (stack.size() > 0){
            node = stack.pop();
            System.out.print(node.value + " ");
            if(node.right != null){
                node = node.right;
                while (node!=null){
                    stack.push(node);
                    node = node.left;
                }
            }
        }
    }

    void preOrderWithStack(BinaryNode root){
        if(root == null)
            return;
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
        BinaryNode node = root;
        stack.push(node);
        while(stack.size() > 0){
            node = stack.pop();
            System.out.print(node.value + " ");
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
    }

    void postOrderWithStack(BinaryNode root){
        if(root == null)
            return;
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
        BinaryNode node = root;

    }

    void inOrderRecursive(BinaryNode root){
        if(root == null)
            return;
        inOrderRecursive(root.left);
        System.out.print(root.value + " ");
        inOrderRecursive(root.right);
    }

    void preOrderRecursive(BinaryNode root){
        if(root == null)
            return;
        System.out.print(root.value + " ");
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);
    }

    void postOrderRecursive(BinaryNode root){
        if(root == null)
            return;
        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.print(root.value + " ");
    }
}
