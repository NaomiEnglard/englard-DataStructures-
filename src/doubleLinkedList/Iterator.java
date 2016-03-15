package doubleLinkedList;

import java.io.Serializable;

public class Iterator<T extends Serializable & Comparable<T>>  {

	private DoubleLinkNode<T> head;
	private DoubleLinkNode<T> fowardUpTo;
	

	
	public Iterator(DoubleLinkNode<T> head){
		this.head = fowardUpTo = head ;
		
	}
	
	public boolean hasNext(){
		return fowardUpTo !=null;
	}
	
	
	
	public T next(){
		T data = null;
		if(fowardUpTo != null){
			data = fowardUpTo.data;
			fowardUpTo = fowardUpTo.next;
		}
		return data;
	}
	
	
	
	public void reset(){
		this.head = this.fowardUpTo;
		//this.tail = this.backwordUpTo;
	}
	
	public void set(T data){
		this.fowardUpTo.data = data; 
	}
}
