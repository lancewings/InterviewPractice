import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Lance on 4/27/17.
 */
public class LinkedListSolutions {
    static class Node{
        int value;
        Node next;

        public Node(int value){
            this.value = value;
            this.next = null;
        }

        public Node(){
            this.value = -1;
            this.next = null;
        }
    }

    void removeDuplicates(Node head){
        HashSet<Integer> trackDups = new HashSet<Integer>();
        Node previous = null;
        while(head!=null){
            if(!trackDups.contains(head.value)){
                trackDups.add(head.value);
                previous = head;
            }else{
                previous.next = head.next;

            }
            head = head.next;
        }
    }

    void removeDuplicatesWithoutBuffer(Node head){
        Node cur = head;
        Node runner = null;
        while(cur != null){
            runner = cur;
            while(runner.next != null){
                if(runner.next.value == cur.value){
                    runner.next = runner.next.next;
                }
                runner = runner.next;
            }
            cur = cur.next;
        }
    }

    Node kThToTheEnd(Node head, int k){
        Node runner = head;
        while(k > 0 && runner != null){
            runner = runner.next;
            k--;
        }
        if(runner == null)
            return null;

        Node result = head;
        while(runner!=null){
            runner = runner.next;
            result = result.next;
        }
        return result;

    }

    void removeMiddleNode(Node head){
        if(head == null || head.next == null)
            return;
        Node oneStepRunner = head;
        Node twoStepRunner = head;
        Node prev = null;
        while(twoStepRunner.next != null && twoStepRunner.next.next != null){
            prev = oneStepRunner;
            oneStepRunner = oneStepRunner.next;
            twoStepRunner = twoStepRunner.next.next;
        }
        prev.next = oneStepRunner.next;
    }

    Node partition1(Node head, int partitionValue){
        Node largerStart = null;
        Node largerEnd = null;
        Node lessStart = null;
        Node lessEnd = null;

        while(head != null){
            if(head.value >= partitionValue){
                if(largerStart == null){
                    largerStart = head;
                    largerEnd = head;
                }else{
                    largerEnd.next = head;
                    largerEnd = head;
                }
            }else{
                if(lessStart == null){
                    lessStart = head;
                    lessEnd = head;
                }else{
                    lessEnd.next = head;
                    lessEnd = head;
                }
            }
            Node node = head.next;
            head.next = null;
            head = node;

        }
        if(lessStart == null)
            return largerStart;
        lessEnd.next = largerStart;
        return lessStart;
    }

    Node partition2(Node node,int partitionValue){
        Node head = node;
        Node tail = node;
        while (node != null) {
            Node next = node.next;
            if(node.value < partitionValue){
                node.next = head;
                head = node;
            }else{
                tail.next = node;
                tail = node;
            }
            node = next;
        }
        tail.next = null;
        return head;
    }

    public static void main(String[] ars){
        LinkedListSolutions a = new LinkedListSolutions();
        Node head = new Node(3);
        Node node1 = new Node(5);
        Node node2 = new Node(8);
        Node node3 = new Node(5);
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        Node node6 = new Node(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        Node result = a.partition2(head, 5);
        while (result!=null){
            System.out.println(result.value);
            result = result.next;
        }
    }
}
