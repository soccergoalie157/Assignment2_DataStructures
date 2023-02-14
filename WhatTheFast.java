package comp2402a2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

public class WhatTheFast implements WhatTheDeque {
  public FastSparrow front;
  public FastSparrow back;
  Integer maxNum;

  public WhatTheFast() {
    front = new FastSparrow();
    back = new FastSparrow();
    maxNum = null;
  }

  public void addFirst(Integer x) {
    // TODO: Your code goes here
    front.push(x);
  }

  public void addLast(Integer x) {
    // TODO: Your code goes here
    back.push(x);
  }

  public Integer removeFirst() {
    // TODO: Your code goes here
    if(size() <= 0) {
      return null;
    } else if (front.size() <= 0 && back.size() == 1) {
      return back.pop();
    } else if (front.size() <= 0) {
      balance();
      return front.pop();
    } else {
      return front.pop();
    }
  }

  public Integer removeLast() {
    // TODO: Your code goes here
    if(size() <= 0) {
      return null;
    } else if (back.size() <= 0 && front.size() == 1) {
        return front.pop();
    } else if (back.size() <= 0) {
      balance();
      return back.pop();
    } else {
      return back.pop();
    }
  }

  public Integer max() {
    // TODO: Your code goes here
    if (size() <= 0) {
      return null;
    } else if (front.size() <= 0) {
      maxNum = back.max();
    } else if (back.size() <= 0) {
      maxNum = front.max();
    } else if (front.max() < back.max()) {
      maxNum = back.max();
    } else if (front.max() > back.max()) {
      maxNum = front.max();
    }
    return maxNum;
  }

  public long ksumFirst(int k) {
    // TODO: Your code goes here
    if (k <= 0 || k > size()) {
      return 0;
    } else if (front.size() == 0) {
        return back.sum.get(k-1); 
    } else if (k == 1) {
      return front.get(front.size()-1);
    } else if (k == size()) {
      return front.ksum(front.size()) + back.ksum(back.size());
    } else if (k > front.size()) {
      return front.ksum(front.size()) + back.sum.get(k - front.size() - 1);
    } else {
      return front.ksum(k);
    }
  }

  public long ksumLast(int k){
    // TODO: Your code goes here
    if (k <= 0 || k > size()) {
      return 0;
    } else if (back.size() == 0) {
      return front.sum.get(k-1);
    } else if (k == 1) {
      return back.get(back.size()-1);
    } else if (k == size()) {
      return front.ksum(front.size()) + back.ksum(back.size());
    } else if (k > back.size()) {
      return back.ksum(back.size()) + front.sum.get(k - back.size() - 1);
    } else {
      return back.ksum(k);
    }
  }

  public int size() {
    // TODO: Your code goes here
    return front.size() + back.size();
  }

  public Iterator<Integer> iterator() {
    // TODO: Your code goes here
    FastSparrow reverseS = new FastSparrow();
    reverseS.addAll(0, front.size(), front);
    reverseS.reverse();
    Iterator<Integer> a = reverseS.iterator();
    Iterator<Integer> b = back.iterator();
    Iterator<Integer> whole = new Iterator<Integer>() {
      int indexA = 0;
      int indexB = 0;
      @Override
      public boolean hasNext() {
        return size() > indexA + indexB;
      }
      @Override
      public Integer next() {
        if (a.hasNext()) {
          a.next();
          return reverseS.get(indexA++);
        } else {
          b.next();
          return back.get(indexB++);
        }
      }
    };
    return whole;
  }

  protected void balance() {
		int n = size();
		if (front.size() <= 0) {
			int s = n/2 - front.size();
			FastSparrow l1 = new FastSparrow();
			FastSparrow l2 = new FastSparrow();
			l1.addAll(0, s, back);
      l1.reverse();
			l2.addAll(s, back.size(), back);
      front.clear();
      back.clear();
			for (int i = 0; i < l1.size(); i++) {
        front.push(l1.get(i));
      }
			for (int i = 0; i < l2.size(); i++) {
        back.push(l2.get(i));
      }
		} else if (back.size() <= 0) {
			int s = front.size() - n/2;
			FastSparrow l1 = new FastSparrow();
			FastSparrow l2 = new FastSparrow();
			l1.addAll(s, front.size(), front);
			l2.addAll(0, s, front);
      l2.reverse();
      front.clear();
      back.clear();
			for (int i = 0; i < l1.size(); i++) {
        front.push(l1.get(i));
      }
			for (int i = 0; i < l2.size(); i++) {
        back.push(l2.get(i));
      }
		}
	}
}
