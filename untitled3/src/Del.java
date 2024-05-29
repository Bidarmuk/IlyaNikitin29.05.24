import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Del {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(1);
        list.add(5);

        // тут создаем копию списка, для сохранения порядка элементов
        List<Integer> uniqueList = new CopyOnWriteArrayList<>(list);

        // создаем пул потоков
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // запускам потоки
        for (int i = 0; i < uniqueList.size(); i++) {
            final int index = i;
            executor.execute(() -> {
                Integer element = uniqueList.get(index);
                for (int j = index + 1; j < uniqueList.size(); j++) {
                    if (element.equals(uniqueList.get(j))) {
                        uniqueList.remove(j);
                        j--;
                    }
                }
            });
        }

        // останавливаем пул потокв и ждем их завершения
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("Исходный список: " + list);
        System.out.println("Список без повторений: " + uniqueList);
    }
}
