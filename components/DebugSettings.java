package components;

public class DebugSettings {
    private static Boolean debug;

    private DebugSettings(){
        //Set a default value
        debug = false;
    }

    public static void setDebug(boolean setValue){
        debug = setValue;
    }

    public static boolean isDebug(){
        return debug != null ? debug : false;
    }
}
