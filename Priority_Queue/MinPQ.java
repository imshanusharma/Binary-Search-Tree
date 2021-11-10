package Priority_Queue;

import java.util.*;

class MinPQ {

    ArrayList<Integer> data;
    
    public MinPQ() {
    
        data = new ArrayList<>();
    }
    
    // Insertion

    public void add(int val) {
    
        data.add(val);
	swim(data.size() - 1);
    }

    // Swim
    
    private void swim(int i) {
    
        int parent = (i-1)/2;
	
	    if(data.get(parent)>data.get(i))
	    {
	        swap(parent, i);
	        swim(parent);
	    }
    }

    // Deletion

    public int poll() {
    
        if(isEmpty())
	{
	    return -1;
	}
	
	swap(0,data.size()-1);

	int val = data.remove(data.size()-1);
	
	sink(0);
	return val;
    }

    // Sink

    private void sink(int i) {
    
        int min = i;
	int left = 2*i+1;

	if(left < data.size() && data.get(left) < data.get(min))
	{
	    min = left;
	}
	
	int right = 2*i+2;

	if(right < data.size() && data.get(right) < data.get(min))
	{
	    min = right;
	}

	if(min != i)
	{
	    swap(min, i);
	    sink(min);
	}
    }

    // Peek

    public int peek() {
    
        if(isEmpty())
	{
	    return -1;
	}
	return data.get(0);
    }

    // Size

    public int size() {
    
        return data.size();
    }

    // IsEmpty
    
    public boolean isEmpty() {
    
        return (data.size() == 0);
    }

    // Swap
    
    private void swap(int i, int j) {
    
        int ith = data.get(i);
	int jth = data.get(j);

	data.set(i,jth);
	data.set(j,ith);
    }
}
