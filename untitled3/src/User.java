import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Client {
    private int id;
    private String name;
    private int age;
    private String gender;
    private List<Phone> phones;

    public Client(int id, String name, int age, String gender, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phones = phones;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<Phone> getPhones() {
        return phones;
    }
}

class Phone {
    private String number;
    private String type;

    public Phone(String number, String type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }
}

public class User {
    public static void main(String[] args) {
        // Создание списка клиентов
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1, "Иван", 25, "Мужской", List.of(
                new Phone("111-111", "Стационарный"),
                new Phone("222-222", "Мобильный")
        )));
        clients.add(new Client(2, "Мария", 35, "Женский", List.of(
                new Phone("333-333", "Мобильный")
        )));
        clients.add(new Client(3, "Алексей", 40, "Мужской", List.of(
                new Phone("444-444", "Стационарный"),
                new Phone("555-555", "Мобильный")
        )));
        clients.add(new Client(4, "Елена", 65, "Женский", List.of(
                new Phone("666-666", "Стационарный"),
                new Phone("777-777", "Мобильный")
        )));
        clients.add(new Client(12, "Николай", 11, "Мужской", List.of(
                new Phone("888-888", "Стационарный"),
                new Phone("242-212", "Мобильный")
        )));
        clients.add(new Client(14, "Сашка", 7, "Мужской", List.of(
                new Phone("923-131", "Стационарный"),
                new Phone("001-101", "Мобильный")
        )));

        // Найти самого возрастного клиента с стационарным телефоном
        Client oldestClientWithLandline = clients.stream()
                .filter(client -> client.getPhones().stream()
                        .anyMatch(phone -> phone.getType().equals("Стационарный")))
                .max(Comparator.comparingInt(Client::getAge))
                .orElse(null);

        System.out.println("Самый возрастной клиент с стационарным телефоном: " + oldestClientWithLandline.getName());

        // Средний возраст клиентов с стационарными телефонами
        double averageAge = clients.stream()
                .filter(client -> client.getPhones().stream()
                        .anyMatch(phone -> phone.getType().equals("Стационарный")))
                .mapToInt(Client::getAge)
                .average()
                .orElse(0);

        System.out.println("Средний возраст клиентов с стационарными телефонами: " + averageAge);

        // Список клиентов возрастом 18+ с мобильными телефонами
        List<Client> adultClientsWithMobilePhones = clients.stream()
                .filter(client -> client.getAge() >= 18 && client.getPhones().stream()
                        .anyMatch(phone -> phone.getType().equals("Мобильный"))).collect(Collectors.toList());

        System.out.println("Список клиентов возрастом 18+ с мобильными телефонами:");
        for (Client client : adultClientsWithMobilePhones) {
            System.out.println(client.getName());
        }

        // Проверка наличия женщины старше 60 лет с стационарным телефоном
        boolean hasOldWomanWithLandline = clients.stream()
                .anyMatch(client -> client.getGender().equals("Женский") && client.getAge() > 60 && client.getPhones().stream()
                        .anyMatch(phone -> phone.getType().equals("Стационарный")));

        System.out.println("Наличие женщины старше 60 лет с стационарным телефоном: " + hasOldWomanWithLandline);
        // Телефонный справочник, отсортированный по имени и возрасту клиента
               List<Client> sortedClients = clients.stream().sorted(Comparator.comparing(Client::getName).thenComparingInt(Client::getAge)).collect(Collectors.toList());
               System.out.println("Телефонный справочник:");
               for (Client client : sortedClients) {
                  System.out.println(client.getName() + ", " + client.getAge());
                }
            }
        }