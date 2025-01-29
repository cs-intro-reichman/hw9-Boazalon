/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		//// Replace the following statement with your code
		ListIterator itr = new ListIterator(first); 
		for (int i = 0; i < index; i++) { 
		 itr.next();
		}
		return itr.current; 
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		//// Write your code here
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node newNode = new Node(block);
		if (index==0) {
			newNode.next = this.first;
			this.first = newNode;
		}
		if (size == 0) {
			last = newNode; 
		} else if (index == size){
			this.last.next = newNode;
			this.last = newNode;
		}
		else {
			Node prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
		}

		size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		//// Write your code here
		Node newNode = new Node(block);
        if (size == 0) { 
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;

	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		//// Write your code here
		Node newNode = new Node(block);
		newNode.next = this.first;
		this.first = newNode;
		if (size==0) {
			last = newNode; 
		}
		size++;

	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		//// Replace the following statement with your code
		if (index < 0 || index > size || size == 0) {
			throw new IllegalArgumentException(
				"index must be between 0 and size");
		}
		
		return this.getNode(index).block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		//// Replace the following statement with your code
		int index = -1;
		for (int i = 0; i < size; i++) { 
		 if(block.equals(getBlock(i))){
			return index = i;
		 }
		}

		return index;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		//// Write your code here
		 if (node == null || size == 0) {
		return;
		}
		int index =indexOf(node.block);
		if (index == -1 ) {
            throw new IllegalArgumentException("Node not found in the list");
        }
		if (index == 0) {
		this.first = this.first.next;
		}
		if (index == (size-1)) {
			getNode(index-1).next = null;
			this.last = getNode(index-1);
			
		}
		if (index>0 && index < size) {
			getNode(index-1).next = node.next;
		}
		
		size --;
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		//// Write your code here
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
				"index must be between 0 and size");
		}
		remove(getNode(index));
		
		
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		//// Write your code here
		if (indexOf(block)==-1) {
			throw new IllegalArgumentException(
				"index must be between 0 and size");
		}

		remove(getNode(indexOf(block)));

	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
		//// Replace the following statement with your code
	//// (0 , 32) (32 , 120) (152 , 40) (192 , 50) (242 , 27) (269 , 79) (348 , 50) (398 , 9) (407 , 1) (408 , 44)
	public String toString() {
		String s = "";
		Node current = first;
		while (current != null) {
		s = s + current.block + " ";
		current = current.next;
		}
		return s;
		}
}