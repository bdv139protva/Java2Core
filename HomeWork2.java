
/**
* Java Core. Homework #2
*
* @author Bakeshko Daria
* @version 29.03.22
*
*/
/*
1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
3. В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и MyArrayDataException и вывести результат расчета.
*/
import java.util.Arrays;

class HomeWork2 {
    public static void main(String[] args) {
        // String[][] arr = new String[4][4];
        String[][] arr1 = { { "10", "20", "30", "40" }, { "100", "200", "300", "400" },
                { "1000", "2000", "3000", "4000" }, { "10000", "20000", "30000", "40000" } };
        String[][] arr2 = { { "10", "20", "30", "40" }, { "100", "200", "300", "400" }, { "1000", "2000", "4000" },
                { "10000", "20000", "30000", "40000" } };
        String[][] arr3 = { { "10", "20", "30", "40" }, { "100", "200", "300", "400" },
                { "1000", "2000", "3000", "4000" }, { "10000", "20000", "30000", "40000" }, { "1", "2", "3", "4" } };
        String[][] arr4 = { { "10", "20", "thirty", "40" }, { "100", "200", "300", "400" },
                { "1000", "2000", "3000", "4000" }, { "10000", "20000", "30000", "40000" } };
        try {
            System.out.println("arr1:");
            System.out.println("Sum of all elements = " + IntAndSum.doIntAndSum(arr1));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.getMessage();
        }

        try {
            System.out.println("arr2:");
            System.out.println("Sum of all elements = " + IntAndSum.doIntAndSum(arr2));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("arr3:");
            System.out.println("Sum of all elements = " + IntAndSum.doIntAndSum(arr3));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("arr4:");
            System.out.println("Sum of all elements = " + IntAndSum.doIntAndSum(arr4));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
        // System.out.println("Sum of all elements = " + IntAndSum.doIntAndSum(arr1));
        // System.out.println("Sum of all elements = " + IntAndSum.doIntAndSum(arr2));
        // System.out.println("Sum of all elements = " + IntAndSum.doIntAndSum(arr3));
    }
}

class IntAndSum {
    static int doIntAndSum(String[][] strArr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        if (4 != strArr.length)
            throw new MyArraySizeException("Size of line not 4");
        for (int i = 0; i < strArr.length; i++) {
            if (4 != strArr[i].length)
                throw new MyArraySizeException("Size of column not 4");
            for (int j = 0; j < strArr[i].length; j++) {
                try {
                    // Integer.parseInt(strArr[i][j]);
                    // System.out.print(strArr[i][j] + " ");
                    sum += Integer.parseInt(strArr[i][j]);
                } catch (NumberFormatException e) {
                    int a = i + 1;
                    int b = j + 1;
                    throw new MyArrayDataException(a, b);
                }
            }
        }
        return sum;
    }
}

class MyArraySizeException extends RuntimeException {
    // construktor
    MyArraySizeException(String s) {
        super(s);
        System.out.println("Array Size Exception");
    }
}

class MyArrayDataException extends RuntimeException {
    // construktor
    MyArrayDataException(int row, int col) {
        super(String.format("Wrong Data at " + "[ line" + row + ": column" + col + "]"));
    }
}
