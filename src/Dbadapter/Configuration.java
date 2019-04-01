package Dbadapter;

public class Configuration {

    private static final String SERVER = "localhost";
    private static final String TYPE = "mysql";
    private static final int PORT = 3306;
    private static final String DATABASE = "swtsm";

    private  static final String USER ="root";
    /**
     * This password in stored in plaintext. We assume that only the owner can
     * access this file and that all login data is tranfered in an encrypted way
     * (e.g. SSL).
     */
    private static final String PASSWORD ="root";

    public static  String getSERVER(){
        return SERVER;
    }
    public static  String getTYPE(){
        return TYPE;
    }

    public static String getDATABASE() {
        return DATABASE;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static int getPORT() {
        return PORT;
    }

    public static String getUSER() {
        return USER;
    }
}
