import java.util.Arrays;
import java.util.Objects;

// 1. Класс товара
class Product {
    private int id;
    private String name;
    private int price;
    private String category;

    public Product(int id, String name, int price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Товар[артикул=" + id +
                ", название=" + name +
                ", цена=" + price +
                ", категория=" + category + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product other = (Product) obj;
        return id == other.id &&
                Objects.equals(category, other.category);
    }
}
// 2. Класс заказа
class Order {
    private String customer;
    private Product[] basket;

    public Order(String customer, Product[] basket) {
        this.customer = customer;
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "Заказ[клиент=" + customer +
                ", корзина=" + Arrays.toString(basket) + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Order other = (Order) obj;

        // Проверяем имя клиента
        if (!Objects.equals(customer, other.customer)) {
            return false;
        }

        // Проверяем корзину
        if (basket == null && other.basket == null) return true;
        if (basket == null || other.basket == null) return false;
        if (basket.length != other.basket.length) return false;

        // Сравниваем каждый товар
        for (int i = 0; i < basket.length; i++) {
            Product p1 = basket[i];
            Product p2 = other.basket[i];

            if (!Objects.equals(p1, p2)) {
                return false;
            }
        }

        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тест товаров ===");

        // Создаем товары
        Product phone1 = new Product(1, "iPhone", 70000, "Электроника");
        Product phone2 = new Product(1, "iPhone Pro", 90000, "Электроника");
        Product laptop = new Product(2, "Ноутбук", 50000, "Электроника");
        Product book = new Product(1, "Книга", 500, "Книги");

        // Показываем товары
        System.out.println(phone1);
        System.out.println(phone2);
        System.out.println(laptop);
        System.out.println(book);

        // Сравниваем
        System.out.println("\nphone1 == phone2? " + phone1.equals(phone2)); // true (одинаковый id и категория)
        System.out.println("phone1 == laptop? " + phone1.equals(laptop)); // false (разный id)
        System.out.println("phone1 == book? " + phone1.equals(book)); // false (разная категория)

        System.out.println("\n=== Тест заказов ===");

        // Создаем заказы
        Product[] basket1 = {phone1, laptop};
        Product[] basket2 = {phone2, laptop}; // phone2 равен phone1 по id и категории
        Product[] basket3 = {phone1};

        Order order1 = new Order("Иван", basket1);
        Order order2 = new Order("Иван", basket2);
        Order order3 = new Order("Петр", basket1);
        Order order4 = new Order("Иван", basket3);

        // Показываем заказы
        System.out.println(order1);
        System.out.println(order2);
        System.out.println(order3);
        System.out.println(order4);

        // Сравниваем
        System.out.println("\norder1 == order2? " + order1.equals(order2)); // true
        System.out.println("order1 == order3? " + order1.equals(order3)); // false (разные клиенты)
        System.out.println("order1 == order4? " + order1.equals(order4)); // false (разная корзина)
    }
}
