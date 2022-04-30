package LIAO.entity;

import java.util.ArrayList;
import java.util.Collection;

public class CircleList<E> extends ArrayList<E> {
    public E get(int index) {

        return super.get((index+size())%size());
    }

    public E set(int index, E element) {
        return super.set((index+size())%size(), element);
    }

    public void add(int index, E element) {
        super.set((index+size())%size(), element);
    }

    public E remove(int index) {
        return super.remove((index+size())%size());
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return super.addAll((index+size())%size(), c);
    }


}
