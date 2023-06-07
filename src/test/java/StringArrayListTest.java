import org.example.IntegerArrayList;
import org.example.IntegerListIndexOutBoundsException;
import org.example.InvalidArgumentException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class StringArrayListTest {

    private IntegerArrayList integerArrayList = new IntegerArrayList(5, 3, 2, 3);


    @BeforeEach
    public void fillList() {
        integerArrayList.add(0);
        integerArrayList.add(1);
        integerArrayList.add(2);
        integerArrayList.add(3);
        integerArrayList.add(4);
    }

    @AfterEach
    public void clearList() {
        integerArrayList.clear();
    }

    @Test
    public void addPositiveTest() {
        int size = integerArrayList.size();
        Assertions.assertEquals(5, integerArrayList.add(5));
        Assertions.assertEquals(size + 1, integerArrayList.size());

    }

    @Test
    public void indexAddPositiveTest() {
        int size = integerArrayList.size();
        int index = 1;
        Assertions.assertEquals(5, integerArrayList.add(index, 5));
        Assertions.assertEquals(index, integerArrayList.indexOf(5));
        Assertions.assertEquals(size + 1, integerArrayList.size());

    }

    @Test
    public void indexAddNegativeTest() {
        Assertions.assertThrows(IntegerListIndexOutBoundsException.class, () -> integerArrayList.add(5, 5));

    }

    @Test
    public void setPositiveTest() {
        int size = integerArrayList.size();
        int index = 1;
        Assertions.assertEquals(5, integerArrayList.set(index, 5));
        Assertions.assertEquals(index, integerArrayList.indexOf(5));
        Assertions.assertEquals(size, integerArrayList.size());
    }

    @Test
    public void setNegativeTest() {
        Assertions.assertThrows(IntegerListIndexOutBoundsException.class, () -> integerArrayList.add(5, 5));

    }

    @Test
    public void removePositiveTest() {
        int size = integerArrayList.size();
        Assertions.assertEquals(1, integerArrayList.remove(1));
        Assertions.assertEquals(size - 1, integerArrayList.size());
    }

    @Test
    public void removeNegativeTest() {
        Assertions.assertThrows(IntegerListIndexOutBoundsException.class, () -> integerArrayList.remove(5));

    }

    @Test
    public void removeIndexPositiveTest() {
        int size = integerArrayList.size();
        Assertions.assertEquals(0, integerArrayList.remove(0));
        Assertions.assertEquals(size - 1, integerArrayList.size());
    }

    @Test
    public void removeIndexNegativeTest() {
        Assertions.assertThrows(IntegerListIndexOutBoundsException.class, () -> integerArrayList.remove(5));

    }

    public static Stream<Arguments> containsParamPositiveTest() {
        return Stream.of(
                Arguments.of(2),
                Arguments.of(2),
                Arguments.of(2)
        );
    }

    @ParameterizedTest
    @MethodSource("containsParamPositiveTest")
    public void containsParamPositiveTest(Integer integer) {
        Assertions.assertTrue(integerArrayList.contains(integer));
    }


    public static Stream<Arguments> indexOfParamPositiveTest() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 3),
                Arguments.of(4, 4)
        );
    }


    @ParameterizedTest
    @MethodSource("indexOfParamPositiveTest")
    public void indexOfPositiveTest(Integer integer, int index) {
        Assertions.assertEquals(index, integerArrayList.indexOf(integer));
    }

    public static Stream<Arguments> indexOfParamNegativeTest() {
        return Stream.of(
                Arguments.of(11, -1),
                Arguments.of(12, -1)
        );
    }


    @ParameterizedTest
    @MethodSource("indexOfParamNegativeTest")
    public void indexOfNegativeTest(Integer integer, int index) {
        Assertions.assertEquals(index, integerArrayList.indexOf(integer));
    }

    public static Stream<Arguments> lastIndexOfTestParamTest() {
        return Stream.of(
                Arguments.of(1, 5),
                Arguments.of(4, 6)
        );
    }


    @ParameterizedTest
    @MethodSource("lastIndexOfTestParamTest")
    public void lastIndexOfTest(Integer integer, int index) {
        integerArrayList.add(1);
        integerArrayList.add(4);
        Assertions.assertEquals(index, integerArrayList.lastIndexOf(integer));
    }

    public static Stream<Arguments> getParamPositiveTest() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 3),
                Arguments.of(4, 4)
        );
    }


    @ParameterizedTest
    @MethodSource("getParamPositiveTest")
    public void getPositiveTest(Integer integer, int index) {
        Assertions.assertEquals(integer, integerArrayList.get(index));
    }

    @Test
    public void getNegativeTest() {
        Assertions.assertThrows(IntegerListIndexOutBoundsException.class, () -> integerArrayList.get(5));
    }

    @Test
    public void equalsPositiveTest() {
        IntegerArrayList test = new IntegerArrayList(5, 3, 2, 3);
        test.add(0);
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        Assertions.assertTrue(integerArrayList.equals(test));
    }

    @Test
    public void equalsNegativeTest() {
        Assertions.assertThrows(InvalidArgumentException.class, () -> integerArrayList.equals(null));
    }

    public static Stream<Arguments> equalsParamNegativeTest() {
        return Stream.of(
                Arguments.of((new IntegerArrayList(1, 3 , 2, 3)))
        );
    }


    @ParameterizedTest
    @MethodSource("equalsParamNegativeTest")
    public void equalsNegativeTest(IntegerArrayList arg) {
        Assertions.assertFalse(integerArrayList.equals(arg));
    }

    @Test
    public void emptyPositiveTest() {
        IntegerArrayList test1 = new IntegerArrayList(5, 3, 2, 3);
        Assertions.assertTrue(test1.isEmpty());
    }


    @Test
    public void emptyNegativeTest() {
        Assertions.assertFalse(integerArrayList.isEmpty());
    }

    @Test
    public void toArrayTest() {
        Integer[] test2 = {0, 1, 2, 3, 4};
        Assertions.assertArrayEquals(test2, integerArrayList.toArray());
    }

}


