public class BukanIni3 {
    static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    static void loading() throws InterruptedException {
        String[] loadingg = {".    ", ". .  ", ". . ."};
        for (int i=0; i<20; i++) {
            System.out.print("\r Loading " + loadingg[i % loadingg.length]);
            Thread.sleep(200);
        }
        System.out.println("\r Loading Complete");
    }
}