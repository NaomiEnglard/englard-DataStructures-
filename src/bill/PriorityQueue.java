package bill;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

import bill.LinkedList.LinkedListInternalIterator;
import School.NotFoundException;

public class PriorityQueue<T extends Serializable & Comparable<T>> implements Serializable {
	
	private SortedLinkedList<T> queue;
	private Comparator<T> compare;
	
	public PriorityQueue(Comparator<T> comparator){
		this.compare = comparator;
		this.queue =  new SortedLinkedList<T>();
		
	}
	
	public void enqueue(T data){
		queue.insert(data,compare);
	}
	
	public T dequeue() throws ListEmptyException, NotFoundException {
		if(queue.head == null){
			throw new ListEmptyException();
		}
		T dequeueData = queue.head.data;
		queue.remove(dequeueData);
		return dequeueData;
	}
	
	public T peek(){
		return queue.head.data;
	}
	
	public void remove(T data) throws ListEmptyException, NotFoundException {
		 queue.remove(data);
	}
	public LinkedListInternalIterator iterator(){
		queue.iter.reset();
		return  queue.iter;
	}
	

}
