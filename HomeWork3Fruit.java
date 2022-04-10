
/**
 * Java Core. Homework #3
 *
 * @author Bakeshko Daria
 * @version 06.04.22
 *
 */

import java.util.Arrays;

class HomeWork3Fruit {
    public static void main(String[] args) {
        Apple[] apples = new Apple[5];
        Orange[] oranges = new Orange[2];

        Box<Apple> appleBox = new Box<>(apples);
        Box<Orange> orangeBox = new Box<>(oranges);

        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        orangeBox.add(new Orange());
        orangeBox.add(new Orange());

        System.out.println(appleBox.compare(orangeBox));

        Apple[] apples2 = new Apple[3];

        Box<Apple> appleBox2 = new Box<>(apples2);

        appleBox2.add(new Apple());
        appleBox2.add(new Apple());
        appleBox2.add(new Apple());

        System.out.println(appleBox2.compare(orangeBox));
        System.out.println(appleBox.compare(appleBox2));

        appleBox.putAnotherBox(orangeBox);

    }

}

class Fruit {
    private float weight;

    Fruit(float weight) {
        this.weight = weight;
    }

    float getWeightOfFruit() {
        return weight;
    }

}

class Apple extends Fruit {
    Apple() {
        super(1.0f);
    }

}

class Orange extends Fruit {
    Orange() {
        super(1.5f);
    }

}

class Box<T extends Fruit> {
    private T[] arr;
    private int counter = 0;

    Box(T[] arr) {
        this.arr = arr;
    }

    T[] getArr() {
        return arr;
    }

    void setArr(T[] arr) {
        this.arr = arr;
    }

    void add(T obj) {
        if (counter < arr.length) {
            arr[counter] = obj;
            counter++;
        } else {
            System.out.println("Box is full");
        }
    }

    void delet() {
        if (counter > 0) {
            arr[counter] = null;
            counter--;
        } else {
            System.out.println("Box is empty");
        }
    }

    void putAnotherBox(Box<?> anotherBox) {
        int firstSize = arr.length;

        if (counter > 0) {
            arr[counter] = null;
            counter--;
        } else {
            System.out.println("Box is empty");
        }

        System.out.println(firstSize);

        int size = anotherBox.getArr().length;
        arr = Arrays.copyOf(arr, size);

        for (int i = firstSize; i < size; i++) {
            arr[i] = anotherBox.getArr()[i - firstSize];
        }
        System.out.println(size);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    float getWeightOfBox() {
        float weight = 0;
        for (T num : arr) {
            weight = weight + num.getWeightOfFruit();
        }
        return weight;
    }

    boolean compare(Box<?> boxToCompare) {
        return getWeightOfBox() - boxToCompare.getWeightOfBox() == 0;
    }

    @Override
    public String toString() {
        return "Box with " + Arrays.toString(arr);
    }
}