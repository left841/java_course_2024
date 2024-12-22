package task_1;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayList_YuriySavkin<T> implements IntensiveList<T> {
    private T[] data;
    private int size;
    private Comparator<T> last_comparator;

    private final static double MEMORY_MULTIPLIER = 2.0;
    protected final static int DEFAULT_CAPACITY = 0;

    ArrayList_YuriySavkin() {
        size = 0;
        data = (T[])new Object[DEFAULT_CAPACITY];
        last_comparator = null;
    }

    ArrayList_YuriySavkin(int size) {
        this.size = size;
        data = (T[])new Object[size];
        last_comparator = null;
    }

    ArrayList_YuriySavkin(ArrayList_YuriySavkin<? extends T> src) {
        this.size = src.size();
        data = Arrays.copyOf(src.data, this.size);
        last_comparator = null;
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @param element
     */
    @Override
    public void add(T element) {
        add(size, element);
    }

    /**
     * @param index
     * @param element
     */
    @Override
    public void add(int index, T element) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException();
        }

        if (size + 1 > data.length) {
            int new_length = (int) (data.length * MEMORY_MULTIPLIER) + 1;
            data = Arrays.copyOf(data, new_length);
        }

        if (index < size)
            System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = element;
        size += 1;
    }

    /**
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    /**
     * @param index
     * @param element
     * @return
     */
    @Override
    public T set(int index, T element) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException();
        }

        T return_value = data[index];
        data[index] = element;
        return return_value;
    }

    /**
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException();
        }

        T return_value = data[index];
        size -= 1;

        if (index < size)
            System.arraycopy(data, index + 1, data, index, size - index);
        data[size] = null;

        return return_value;
    }

    /**
     *
     */
    @Override
    public void clear() {
        size = 0;
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * @param comparator
     */
    @Override
    public void quickSort(Comparator<T> comparator) {
        last_comparator = comparator;
        quickSortPart(0, size - 1);
    }

    private void quickSortPart(int first_index, int last_index) {
        if (last_index - first_index < 1)
            return;

        T pivot = data[first_index];
        int left_index = first_index, right_index = last_index;

        while (left_index <= right_index) {
            while (last_comparator.compare(data[left_index], pivot) < 0)
                ++left_index;
            while (last_comparator.compare(data[right_index], pivot) > 0)
                --right_index;

            if (left_index <= right_index) {
                T tmp = data[left_index];
                data[left_index] = data[right_index];
                data[right_index] = tmp;
                ++left_index;
                --right_index;
            }
        }

        quickSortPart(first_index, right_index);
        quickSortPart(right_index + 1, last_index);
    }

    /**
     * @return
     */
    @Override
    public boolean isSorted() {
        if (last_comparator == null)
            return false;

        for (int i = 1; i < size; ++i)
            if (last_comparator.compare(data[i - 1], data[i]) >= 0)
                return false;

        return true;
    }

    /**
     * @param size
     */
    @Override
    public void split(int size) {
        if ((size < 0) || (size >= this.size)) {
            throw new IllegalArgumentException();
        }

        Arrays.fill(data, size, this.size, null);
        this.size = size;
    }
}
