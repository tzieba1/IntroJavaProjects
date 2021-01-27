/**
 * Generic Linked List class that always keeps the elements in order
 *
 * @author mark.yendt
 */
public class SortedLinkedList<T extends Comparable> {
  /**
   * The Node class stores a list element and a reference to the next node.
   */
  public final class Node<T extends Comparable> {
    T value;
    Node next;

    /**
     * Constructor.
     *
     * @param val The element to store in the node.
     * @param n   The reference to the successor node.
     */
    Node(T val, Node n) {
      value = val;
      next = n;
    }

    /**
     * Constructor.
     *
     * @param val The element to store in the node.
     */
    Node(T val) {
      // Call the other (sister) constructor.
      this(val, null);
    }
  }

  public Node first;  // list head

  /**
   * Constructor.
   */
  public SortedLinkedList() {
    first = null;
  }

  /**
   * The isEmpty method checks to see if the list is empty.
   *
   * @return true if list is empty, false otherwise.
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * The size method returns the length of the list.
   *
   * @return The number of elements in the list.
   */
  public int size() {
    int count = 0;
    Node p = first;
    while (p != null) {
      // There is an element at p
      count++;
      p = p.next;
    }
    return count;
  }

  /**
   * The add method adds an element at a position.
   *
   * @param element The element to add to the list in sorted order.
   */
  public void add(T element){
    if (this.isEmpty()) {
      //REQUIRED: Assign the first Node with null reference constructor.
      first = new Node(element);
    }

    //REQUIRED: Otherwise, check for second node before proceeding with algorithm.
    else {
      //REQUIRED: Check condition for single element list.
      if (first.next == null) {
        /* Condition for first value sorted before or equal to element. */
        if (first.value.compareTo(element) <= 0)
          //REQUIRED: Add second element to end of list.
          first.next = new Node(element);
        /* Condition for first value sorted after element. */
        if (first.value.compareTo(element) > 0) {
          //REQUIRED: Add second element to beginning of list.
          first.next =  new Node(first.value);    // Change first to have next reference to the tempNode
          first.value = element;                  // Change value of first to the element which should come first.
        }
      }
      //REQUIRED: Otherwise, do algorithm traversing list with more than 2 elements and add new element in sorted order.
      else {
        Node primaryNode = first;
        Node secondaryNode = first.next;

        //NOTE: Assume list is always ordered, but possibly empty and proceed with logic as follows.
        //REQUIRED: Iterate through pairs of nodes for SortedLinkedList with size() > 1 to add element in sorted order.
        while (secondaryNode != null) {
          //REQUIRED: Only 4 conditions need to be checked since logic is structured based on assumption of order.
          //----------------------------------------------------------------------------------------------------------//
          /* Condition #1 (case that looping continues) satisfying: secondaryNode.value < element */
          if (secondaryNode.value.compareTo(element) < 0) {
            //REQUIRED: Advance the nodes forward.
            primaryNode = primaryNode.next;
            secondaryNode = secondaryNode.next;
            /*  Condition #4 (case that list ends) satisfying: primaryNode <= element && primaryNode references null */
            if (secondaryNode == null) {
              //REQUIRED: Add element node to end of list. No need to break out because of while loop's condition.
              primaryNode.next = new Node(element);
            }
          }
          /* Condition #2 (case that element sorted) satisfying: primaryNode.value < element <= secondaryNode.value */
          else if (primaryNode.value.compareTo(element) < 0 && secondaryNode.value.compareTo(element) >= 0) {
            //REQUIRED: Add element after primaryNode and break out of loop.
            primaryNode.next = new Node(element);   // Assign primaryNode reference to element node without a reference.
            primaryNode.next.next = secondaryNode;  // Assign element node reference to secondaryNode.
            break;
          }
          /* Condition #3 (for case when adding to front of list) satisfying: element < primaryNode */
          else {
            //REQUIRED: Add element to front of list and break out of loop.
            first = new Node(element);
            first.next = primaryNode;
            break;
          }
        }
      }
    }
  }

  /**
   * The toString method computes the string representation of the list.
   *
   * @return The string form of the list.
   */
  @Override
  public String toString() {
    String listOfItems = "[";
    Node name = first;          //Start at first node.
    while (name != null) {      //Loop through each next node and add the value to the listOfItems to be returned.
      listOfItems += name.value.toString();
      name = name.next;
      if (name != null) {       //Check this condition to ensure an extra comma is not appended to last value.
        listOfItems += ", ";
      }
    }
    listOfItems += "]";

    return listOfItems;
  }

  /**
   * The remove method removes an element.
   *
   * @param element The element to remove.
   * @return true if the remove succeeded, false otherwise.
   */
  public boolean remove(T element) {
    if(this.isEmpty()) {
      return false;
    }

    /* Condition for checking to remove the first Node in this SortedLinkedList. */
    if (first.value.compareTo(element) == 0){
      //REQUIRED: Change the reference so that the list starts at the next Node and 'skips' the current first Node.
      this.first = first.next;
      return true;
    }

    //REQUIRED: Loop through pairs of nodes until secondaryNode equals element and 'skip over it' referentially.
    //          Two nodes are required in order to shuffle references if and when reaching the node to be removed.
    Node primaryNode = first;
    Node secondaryNode = first.next;
    while(secondaryNode != null) {
      /* Condition for reaching the Node to be removed. */
      if(secondaryNode.value.compareTo(element) == 0) {
        //REQUIRED: Change the references so that the secondaryNode with a value equal to element is 'discarded'.
        primaryNode.next = secondaryNode.next;
        break;
      }
      //REQUIRED: Increment pair of nodes forward to loop and compare the next Node after the current secondaryNode.
      secondaryNode = secondaryNode.next;
      primaryNode =  primaryNode.next;
    }

    /* Condition for checking to remove the final Node in this SortedLinkedList. */
    if(secondaryNode == null) {
      //REQUIRED: return boolean outcome of checking equality of final Node value with element.
      return primaryNode.value.compareTo(element) == 0;
    }

    //REQUIRED: Only reached after breaking out of while loop prior to the last Node in this SortedLinkedList.
    return true;
  }
}