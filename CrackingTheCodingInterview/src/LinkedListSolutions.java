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

    Node reverseList(Node head){
        Node prev = null;
        Node cur = head;
        Node next = null;
        while(cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;
        return head;
    }

    Node reverseList2(Node head){
        Node dummy = null;
        while(head != null){
            Node n = new Node(head.value);
            n.next = dummy;
            dummy = n;
            head = head.next;
        }
        return dummy;
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

    Node sumList(Node head1, Node head2){//3->6->5 stands for 563
        if(head1 == null)
            return head2;
        if(head2 == null)
            return head1;
        return sumNode(head1, head2, 0);
    }

    Node sumNode(Node node1, Node node2, int carry){
        if(node1 == null && node2 == null){
            return carry==0?null:new Node(carry);
        }
        int value1 = node1==null?0:node1.value;
        int value2 = node2==null?0:node2.value;
        int sum = value1 + value2 + carry;
        Node next = new Node(sum % 10);
        next.next = sumNode(node1.next, node2.next, sum / 10);
        return next;
    }

    class PartialSum{
        Node partialNode;
        int carry;
    }

    Node sumList2(Node head1, Node head2){//3->6->5 stands for 365
        if(head1 == null)
            return head2;
        if(head2 == null)
            return head1;
        int length1 = getListLength(head1);
        int length2 = getListLength(head2);
        if(length1 - length2 > 0){
            head2 = fillZero(head2, length1-length2);
        }else{
            head1 = fillZero(head1, length2 - length1);
        }
        PartialSum ps = getPartialSum(head1, head2);
        if(ps.carry==1){
            Node result = new Node(ps.carry);
            result.next = ps.partialNode;
            return result;
        }else{
            return ps.partialNode;
        }
    }

    PartialSum getPartialSum(Node node1, Node node2){
        PartialSum result = new PartialSum();
        result.partialNode = new Node(0);
        int sum;
        if(node1.next==null && node2.next==null){
            sum = node1.value + node2.value;
            result.partialNode.next = null;
        }
        else{
            PartialSum nextSum = getPartialSum(node1.next, node2.next);
            sum = nextSum.carry + node1.value + node2.value;
            result.partialNode.next = nextSum.partialNode;
        }
        result.partialNode.value = sum % 10;
        result.carry = sum / 10;
        return result;
    }

    Node fillZero(Node head, int length){
        Node start = head;
        while(length > 0){
            Node node = new Node(0);
            node.next = start;
            start = node;
            length--;
        }
        return start;
    }


    int getListLength(Node head){
        if(head == null)
            return 0;
        int result = 1;
        while(head.next != null){
            result++;head=head.next;
        }
        return result;
    }

    class Result{
        public Node tail;
        public int size;

        public Result(Node tail, int size) {
            this.tail = tail;
            this.size = size;
        }
    }

    Node detectIntersection(Node head1, Node head2){
        Result result1 = getTailAndSize(head1);
        Result result2 = getTailAndSize(head2);
        if(result1.tail != result2.tail)
            return null;

        Node shorter = result1.size > result2.size?head2:head1;
        Node longer = result1.size > result2.size?head1:head2;

        longer = getKthNode(longer, Math.abs(result1.size-result2.size));

        while(shorter != longer){
            shorter = shorter.next;
            longer = longer.next;
        }
        return longer;

    }

    Node getKthNode(Node head, int step){
        Node cur = head;
        while(step>0 && cur!= null){
            cur = cur.next;
            step--;
        }
        return cur;
    }

    Result getTailAndSize(Node node){
        if(node==null) return null;
        int size = 1;
        while(node.next != null){
            size++;
            node = node.next;
        }
        return new Result(node, size);
    }

    Node detectLoopStart(Node head){
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }

        if(fast == null || fast.next ==null){
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    public static void main(String[] ars){
        LinkedListSolutions a = new LinkedListSolutions();
        Node head = new Node(4);
        Node node1 = new Node(6);
        Node node2 = new Node(0);
//        Node node12 = new Node(4);
        head.next = node1;
        node1.next = node2;
//        node2.next = node12;

        Node head2 = new Node(5);
        Node node3 = new Node(6);
        Node node4 = new Node(0);
        node4.next = node2;
        head2.next = node3;
        node3.next = node4;

        Node result = a.detectIntersection(head, head2);
        while (result!=null){
            System.out.println(result.value);
            result = result.next;
        }
    }
}
