package comp2402a2;

import java.util.ArrayList;
import java.util.Iterator;

public class FastSparrow implements CapnStackSparrow {
  protected ArrayList<Integer> ds;
  protected ArrayList<Integer> max;
  public ArrayList<Long> sum;
  long totalSum;
  Integer maxNum;

  public FastSparrow() {
    // TODO: Your code goes here
    ds = new ArrayList<>();
    max = new ArrayList<>();
    sum = new ArrayList<>();
    maxNum = null;
    totalSum = 0;
  }

  public void push(Integer x) {
    // TODO: Your code goes here
    ds.add(x);
    totalSum += x;
    sum.add(totalSum);
    if (maxNum == null || x > maxNum) {
      maxNum = x;
      max.add(x);
    } else {
      max.add(maxNum);
    }
  }

  public Integer pop() {
    // TODO: Your code goes here
    if(size() <= 0) {
      return null;
    } else {
      max.remove(max.size()-1);
      if (max.size() <= 0) {
        maxNum = null;
      } else {
        maxNum = max.get(max.size()-1);
      }
      totalSum -= ds.get(ds.size()-1);
      sum.remove(sum.size()-1);
      return ds.remove(ds.size()-1);
    }
  }

  public Integer max() {
    // TODO: Your code goes here
    if (ds.size() <= 0) {
      return null;
    }
    return maxNum;
  }

  public long ksum(int k) {
    // TODO: Your code goes here
    if (k <= 0 || k > ds.size()) {
      return 0;
    } else if (k == ds.size()) {
      return totalSum;
    } else {
      return sum.get(sum.size()-1) - sum.get(sum.size() - 1 - k);
    }
  }

  public int size() {
    // TODO: Your code goes here
    return ds.size();
  }

  public Iterator<Integer> iterator() {
    // TODO: Your code goes here
    return ds.iterator();
  }

  public int get(int i) {
    return ds.get(i);
  }

  public void clear() {
    ds.clear();
    max.clear();
    sum.clear();
    totalSum = 0;
    maxNum = null;
  }

  public boolean reverse() {
    int index = 0;
    int end = size()-1;
    while (index < end) {
      int temp = ds.get(index);
      ds.set(index, ds.get(end));
      ds.set(end, temp);
      index++;
      end--;
    }
    return true;
  }

  public boolean addAll(int start, int end, FastSparrow c) {
		for (int i = start; i < end; i++) {
      ds.add(c.get(i));
    }
		return true;
	}

}