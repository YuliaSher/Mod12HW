import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzz {

    //Програма повинна бути багатопотоковою, і працювати з 4 потоками:
    //1. Потік A викликає fizz(), щоб перевірити, чи ділиться число на 3,
    //   і якщо так - додати в чергу на виведення для потоку D рядок fizz.
    //2. Потік B викликає buzz(), щоб перевірити, чи ділиться число на 5,
    //   і якщо так - додати в чергу на виведення для потоку D рядок buzz.
    //3. Потік C викликає fizzbuzz(), щоб перевірити, чи ділиться число на
    //   3 та 5 одночасно, і якщо так - додати в чергу на виведення для потоку D рядок fizzbuzz.
    //4. Потік D викликає метод number(), щоб вивести наступне число з черги,
    //   якщо є таке число для виведення.


    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Thread threadA = new Thread(() -> {
            for (int i = 1; i < 31; i++) {
                try {
                    Thread.sleep(100);
                    if (fizzBuzz.fizz(i)) {
                        queue.add("fizz");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadA.start();

        Thread threadB = new Thread(() -> {
            for (int i = 1; i < 31; i++) {
                try {
                    Thread.sleep(100);
                    if (fizzBuzz.buzz(i)) {
                        queue.add("buzz");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadB.start();

        Thread threadC = new Thread(() -> {
            for (int i = 1; i < 31; i++) {
                try {
                    Thread.sleep(100);
                    if (fizzBuzz.fizzbuzz(i)) {
                        queue.add("fizzbuzz");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadC.start();

        Thread threadD = new Thread(() -> {
            for (int i = 1; i < 31; i++) {
                try {
                    Thread.sleep(100);
                    if (i % 3 != 0 && i % 5 != 0) {
                        queue.add(fizzBuzz.number(i));
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            while (!queue.isEmpty()) {
                try {
                    String str = queue.take();
                    System.out.println(str);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadD.start();
    }


    public boolean fizz(int n) {
        return n % 3 == 0 && n % 5 != 0;
    }

    public boolean buzz(int n) {
        return n % 5 == 0 && n % 3 != 0;
    }

    public boolean fizzbuzz(int n) {
        return n % 15 == 0;
    }

    public String number(int n) {
        return Integer.toString(n);
    }
}
