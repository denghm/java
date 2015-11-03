import java.io.File;
import java.io.IOException;

/**
 * Created by scdhm on 2015/11/2.
 */
public class FileChecker {

    public static void main(String args[]) {

        makeDir();
    }


    public static boolean makeDir() {
        boolean result = false;
        File directory = new File("d:/temp/abc");
        if (directory.exists() && directory.isFile()) {
            System.out.println("The dir with name could not be" +
                    " created as it is a normal file");
        } else {
            try {
                if (!directory.exists()) {
                    result = directory.mkdirs();
                }


            } catch (Exception e) {
                System.out.println("prompt for error");
            }
        }
        return result;
    }


}
