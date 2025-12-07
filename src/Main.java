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

