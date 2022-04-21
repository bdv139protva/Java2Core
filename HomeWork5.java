
/**
 * Java Core. Homework #5
 *
 * @author Bakeshko Daria
 * @version 15.04.22
 *
 */
import java.util.Arrays;

class HomeWork5 {
    public static void main(String[] args) {
        AppData appData = new AppData();
        appData.load("AppDataFile.csv");

        System.out.println(Arrays.toString(appData.getHeader()));
        System.out.println(Arrays.deepToString(appData.getData()));

        appData.save("AppDataFileSave.csv");

    }

}