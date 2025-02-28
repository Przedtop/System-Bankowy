/** Klasa ta zawiera przyrządy do zarządzania kodem i testowania go jako developer.
*/

public class Settings {

    public static final String URL = "jdbc:mysql://localhost:3306/kontobankowe";
    public static final String USER = "root";
    public static final String PASSWORD = "";


    public static final String version = "2.0";

    private static final boolean devModeStatus = false;
    private static final boolean testerModeStatus = true;

    public static boolean testAllow = false;

    public static boolean getDevModeStatus(){
        return devModeStatus;
    }
    public static boolean getTesterModeStatus(){
        return testerModeStatus;
    }
}