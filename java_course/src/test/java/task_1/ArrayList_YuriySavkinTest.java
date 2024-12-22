package task_1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayList_YuriySavkinTest {

    static Integer[][] sorting_arrays;

    @BeforeAll
    static void setSortArrays() {
        sorting_arrays = new Integer[6][];

        sorting_arrays[0] = new Integer[]{2, 2, 2, 2, 3, 3, 3, 3};
        sorting_arrays[1] = new Integer[]{3, 3, 3, 3, 2, 2, 2, 2};
        sorting_arrays[2] = new Integer[]{1, 3, 2, 1, 3, 2, 3, 2};
        sorting_arrays[3] = new Integer[]{3, 3, 2, 2, 2, 2, 2};
        sorting_arrays[4] = new Integer[]{23};
        sorting_arrays[5] = new Integer[]{3, 2};
    }

    @Test
    public void canAddElements() {
        ArrayList_YuriySavkin<Integer> arr = new ArrayList_YuriySavkin<>();

        assertDoesNotThrow(()-> {
            arr.add(6);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    public void canGetListElements(int index) {
        ArrayList_YuriySavkin<Integer> arr = new ArrayList_YuriySavkin<>();

        int[] init_arr = new int[]{9, 8, 7, 6};
        for (int i: init_arr)
            arr.add(i);

        assertEquals(arr.get(index), init_arr[index]);
    }


    @Test
    public void canGetSize() {
        ArrayList_YuriySavkin<Integer> arr = new ArrayList_YuriySavkin<>();

        arr.add(2);

        assertEquals(arr.size(), 1);
    }

    @Test
    public void canSetValue() {
        ArrayList_YuriySavkin<Integer> arr = new ArrayList_YuriySavkin<>();

        arr.add(2);
        arr.set(0, 3);

        assertEquals(arr.get(0), 3);
    }

    @Test
    public void removeShiftsValuesProperly() {
        ArrayList_YuriySavkin<Integer> arr = new ArrayList_YuriySavkin<>();

        arr.add(4);
        arr.add(5);
        arr.add(7);
        arr.remove(1);

        assertEquals(arr.get(1), 7);
    }

    @Test
    public void removeSetsSizeProperly() {
        ArrayList_YuriySavkin<Integer> arr = new ArrayList_YuriySavkin<>();

        arr.add(4);
        arr.add(5);
        arr.add(7);
        arr.remove(1);

        assertEquals(arr.size(), 2);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    public void sortingTest(int index) {
        ArrayList_YuriySavkin<Integer> arr = new ArrayList_YuriySavkin<>();
        Integer[] compare_arr = Arrays.copyOf(sorting_arrays[index], sorting_arrays[index].length);
        for (int i: sorting_arrays[index]) {
            arr.add(i);
        }
        Comparator<Integer> comparator = Comparator.naturalOrder();

        Arrays.sort(compare_arr, comparator);
        arr.quickSort(comparator);

        assertEquals(arr.size(), compare_arr.length);
        for (int i = 0; i < arr.size(); ++i) {
            assertEquals(arr.get(i), compare_arr[i]);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    public void sortingCheckingTest(int index) {
        ArrayList_YuriySavkin<Integer> arr = new ArrayList_YuriySavkin<>();
        for (int i: sorting_arrays[index]) {
            arr.add(i);
        }
        Comparator<Integer> comparator = Comparator.naturalOrder();

        arr.quickSort(comparator);

        assertTrue(arr.isSorted());
    }
}