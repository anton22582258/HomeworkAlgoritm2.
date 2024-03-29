package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.exception.ElementNotFoundException;
import org.example.exception.OutOfBoundException;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.example.service.IntegerListImpl;

import java.util.Random;

public class IntegerListImplTest {
    private IntegerListImpl out = new IntegerListImpl(4);

    @BeforeEach
    void setUp() {
        out.add(5);
        out.add(11);
    }

    @AfterEach
    void after() {
        out.clear();
    }

    @Test
    public void quickSort() {
        out.add(27);
        out.add(4);
        out.add(113);
        out.add(1);
        IntegerListImpl.quickSort(out.toArray(), 0, out.toArray().length - 1);
        System.out.println(out.toArray());
    }

    @Test
    public void bubbleSort() {
        out.add(27);
        out.add(4);
        out.add(113);
        out.add(1);
        IntegerListImpl.bubbleSort(out);
    }

    @Test
    public void selectionSort() {
        out.add(27);
        out.add(4);
        out.add(113);
        out.add(1);
        IntegerListImpl.selectionSort(out);
    }

    @Test
    public void addInTheEnd() {
        out.add(21);
        out.add(3);
        out.add(7);
        Integer[] expected = new Integer[]{5, 11, 21, 3, 7};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
    }

    @Test
    public void addByIndex() {
        out.add(1, 21);
        Integer[] expected = new Integer[]{5, 21, 11};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
    }

    @Test
    public void addByIndexWithArrayExtending() {
        out.add(21);
        out.add(3);
        out.add(2, 7);
        Integer[] expected = new Integer[]{5, 11, 7, 21, 3};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
    }

    @Test
    public void addByIncorrectIndex() {
        Assertions.assertThatExceptionOfType(OutOfBoundException.class).isThrownBy(() ->
                out.add(3, 175));
        Assertions.assertThatExceptionOfType(OutOfBoundException.class).isThrownBy(() ->
                out.add(5, 17));
    }


    @Test
    public void set() {
        out.set(1, 27);
        Integer[] expected = new Integer[]{5, 27};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
    }

    @Test
    public void removeByIndex() {
        Integer[] expected = new Integer[]{11};
        out.remove(0);
        Assertions.assertThat(expected).
                isEqualTo(out.toArray());
    }

    @Test
    public void removeByIndexNull() {
        Assertions.assertThatExceptionOfType(NullPointerException.class).
                isThrownBy(() ->
                        out.remove(null));
    }

    @Test
    public void removeByIncorrectIndex() {
        Assertions.assertThatExceptionOfType(ElementNotFoundException.class).
                isThrownBy(() ->
                        out.remove(3));
    }

    @Test
    public void removeByItem() {
        out.add(27);
        out.add(29);
        Integer[] expected = new Integer[]{5, 11, 29};
        out.remove((Integer) 27);
        Assertions.assertThat(expected).
                isEqualTo(out.toArray());
    }

    @Test
    public void removeNonExistedItem() {
        Assertions.assertThatExceptionOfType(ElementNotFoundException.class).
                isThrownBy(() ->
                        out.remove(552));
    }

    @Test
    public void contains() {
        out.add(27);
        out.add(33);
        out.add(15);
        out.add(3);
        out.add(66);
        out.add(115);
        Assertions.assertThat(out.contains(27)).isTrue();
        Assertions.assertThat(out.contains(66)).isTrue();
        Assertions.assertThat(out.contains(15)).isTrue();
        Assertions.assertThat(out.contains(511)).isFalse();

    }

    @Test
    public void indexOf() {
        int expected = 0;
        Assertions.assertThat(expected).
                isEqualTo(out.indexOf(5));
        int expected2 = -1;
        Assertions.assertThat(expected2).
                isEqualTo(out.indexOf(554));
    }

    @Test
    public void indexOfNull() {
        Assertions.assertThatExceptionOfType(NullPointerException.class).
                isThrownBy(() -> out.indexOf(null));

    }

    @Test
    public void lastIndexOfNull() {
        Assertions.assertThatExceptionOfType(NullPointerException.class).
                isThrownBy(() -> out.lastIndexOf(null));
    }

    @Test
    public void lastIndexOf() {
        int expected = 0;
        Assertions.assertThat(expected).
                isEqualTo(out.lastIndexOf(5));
        int expected2 = -1;
        Assertions.assertThat(expected2).
                isEqualTo(out.lastIndexOf(15));
    }

    @Test
    public void get() {
        Integer expected = 5;
        Assertions.assertThat(expected).
                isEqualTo(out.get(0));
    }

    @Test
    public void sortTest() {
        Random r = new Random();
        IntegerListImpl list = new IntegerListImpl(4);
        IntegerListImpl list2 = new IntegerListImpl(4);
        IntegerListImpl list3 = new IntegerListImpl(4);
        for (int i = 0; i <= 1000; i++) {
            list.add(r.nextInt(100));
        }
        for (int i = 0; i < list.size(); i++) {
            list2.add(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            list3.add(list.get(i));
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print(list2.get(i) + " ");
        }
        System.out.println(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print(list3.get(i) + " ");
        }
        System.out.println(" ");


        long time = System.currentTimeMillis();
        IntegerListImpl.bubbleSort(list);
        long timeForBubble = System.currentTimeMillis() - time;

        long time2 = System.currentTimeMillis();
        IntegerListImpl.selectionSort(list2);
        long timeForSelection = System.currentTimeMillis() - time2;

        long time3 = System.currentTimeMillis();
        IntegerListImpl.insertionSort(list3);
        long timeForInsertion = System.currentTimeMillis() - time3;

        for (int i = 0; i < 50; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println(" ");

        for (int i = 0; i < 50; i++) {
            System.out.print(list2.get(i) + " ");
        }
        System.out.println(" ");
        for (int i = 0; i < 50; i++) {
            System.out.print(list3.get(i) + " ");
        }
        System.out.println(" ");

        System.out.println(timeForBubble + " " + timeForSelection + " " + timeForInsertion);
    }

    @Test
    public void testEquals() {
        IntegerListImpl list = new IntegerListImpl(4);
        IntegerListImpl list2 = new IntegerListImpl(6);
        IntegerListImpl list3 = new IntegerListImpl(4);
        list.add((Integer) 5);
        list.add((Integer) 11);
        list2.add((Integer) 5);
        list2.add((Integer) 11);
        list3.add((Integer) 5);
        list3.add((Integer) 7);
        Assertions.assertThat(out.equals(list))
                .isTrue();
        Assertions.assertThat(out.equals(list2))
                .isTrue();
        Assertions.assertThat(out.equals(list3))
                .isFalse();


    }

    @Test
    public void testEqualsNull() {

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> out.equals(null));

    }

    @Test
    public void size() {
        Assertions.assertThat(out.size()).
                isEqualTo(2);
        out.add(17);
        out.add(26);
        out.add(36);
        Assertions.assertThat(out.size()).
                isEqualTo(5);
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void clear() {
        out.clear();
        Integer[] expected = new Integer[]{};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
        out.add(15);
        out.add(27);
        Integer[] expected2 = new Integer[]{15, 27};
        Assertions.assertThat(expected2).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
    }

    @Test
    public void toArray() {
    }
}
