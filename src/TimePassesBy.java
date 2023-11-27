public class TimePassesBy {
    //Програма, яка кожну секунду відображає на екрані дані про час, що минув від моменту
    //її запуску.
    //Другий потік цієї ж програми кожні 5 секунд виводить повідомлення Минуло 5 секунд.

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; ; i++) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Минуло 5 секунд");
            }
        }).start();

        for (long i = 0; ; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
