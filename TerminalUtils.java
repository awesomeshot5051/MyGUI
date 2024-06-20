public class TerminalUtils {
    public static void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Runtime.getRuntime().exec("clear");
                // System.out.print("\033[H\033[2J");
                // System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
