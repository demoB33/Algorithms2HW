package org.example;

import java.util.Arrays;
import java.util.Objects;

public class IntegerArrayList implements IntegerList {
    private Integer[] data;
    private int currentSize;


    public IntegerArrayList(int size, int i, int i1, int i2) {
        data = new Integer[size];
        currentSize = 0;
    }

    public IntegerArrayList(String... args) {
        data = new Integer[args.length];
        System.arraycopy(args, 0, data, 0, args.length);
        currentSize = data.length;
    }


    @Override
    public Integer add(Integer item) {
        if (currentSize == data.length) {
            resize(currentSize + 1);
        }
        data[currentSize] = item;
        currentSize++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkBounds(index);
        if (currentSize >= data.length) {
            grow();
        }

        if (currentSize == data.length) {
            resize(currentSize + 1);
        }
        for (int i = currentSize; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = item;
        currentSize++;
        return item;
    }

    private void grow() {
        Integer[] data = new Integer[(int)(this.data.length * 1.5)];
        System.arraycopy(this.data, 0 , data, 0, this.data.length);
        this.data = data;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkBounds(index);
        data[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkBounds(index);
        Integer result = data[index];
        for (int i = index + 1; i < currentSize; i++) {
            data[i - 1] = data[i];
        }
        currentSize--;
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        checkBounds(item);
        Integer[] copy = toArray();
        search(copy, 0, copy.length - 1);

        int min = 0;
        int max = copy.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == copy[mid]) {
                return true;
            }

            if (item < copy[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < currentSize; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = currentSize - 1; i >= 0; i--) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkBounds(index);
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new InvalidArgumentException();
        }
        if (currentSize != otherList.size()) {
            return false;
        }
        for (int i = 0; i < currentSize; i++) {
            if (!Objects.equals(data[i], otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(data, 0, currentSize, null);
        currentSize = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(data, currentSize);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < currentSize; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(data[i]);
        }
        result.append("]");
        return result.toString();
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IntegerListIndexOutBoundsException();
        }
    }

    private void resize(int newSize) {
        int size = (currentSize * 3 / 2) + 1;
        size = Math.max(size, newSize);
        Integer[] newData = new Integer[size];
        System.arraycopy(data, 0, newData, 0, currentSize);
        data = newData;
    }

    private void sortInsertion() {
        for (int i = 1; i < currentSize; i++) {
            int temp = data[i];
            int j = i;
            while (j > 0 && data[j - 1] >= temp) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = temp;
        }
    }

    private void search(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            search(arr, begin, partitionIndex - 1);
            search(arr, partitionIndex + 1, end);
        }

    }
    private  int partition(Integer[] arr, int begin, int end) {
        Integer pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return i + 1;
    }
    private  void  swap(Integer[] arr, int left, int right) {
        Integer temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }



}