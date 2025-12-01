import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Order {
    private String product;
    private double cost;
    private int id;

    public Order(String product, double cost, int id) {
        this.product = product;
        this.cost = cost;
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }

    public int getId(){ // так будет немного логичнее для группировки заказов
        return id;
    }

    @Override
     public String toString (){
        return "Order №" + id + " product: " + product + " cost: " + cost;
     }
}

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0, 1),
                new Order("Smartphone", 800.0, 2),
                new Order("Laptop", 1500.0, 3),
                new Order("Tablet", 500.0, 4),
                new Order("Smartphone", 900.0, 5),
                new Order("Laptop", 1500.0, 6),
                new Order("Tablet", 500.0, 7),
                new Order("Smartphone", 900.0, 8)
        );


    Map<String, Double> totalCostByProduct = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost)
                ));

    List<Map.Entry<String, Double>> top3 = totalCostByProduct.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());

    System.out.println("Три самых дорогих продукта:");
        top3.forEach(entry -> 
            System.out.println(entry.getKey() + "общая стоимость: " + entry.getValue())
        );
}
}
