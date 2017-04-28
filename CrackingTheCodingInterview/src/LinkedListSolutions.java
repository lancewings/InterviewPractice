import java.util.HashSet;

/**
 * Created by Lance on 4/27/17.
 */
public class LinkedListSolutions {
    class Node{
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
        
    }

}
