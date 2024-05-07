package nau.coursework4.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CourseworkApplication {
    private final FlowersStore flowersStore = FlowersStoreSingleton.getInstance();
    private final BunchesStore bunchesStore = BunchesStoreSingleton.getInstance();
    private final OrdersStore ordersStore = OrdersStoreSingleton.getInstance();

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }


    @GetMapping(path = "/bunches", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Object> getBunches() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return new ResponseEntity<Object>(gson.toJson(bunchesStore.data), HttpStatus.OK);
    }

    @PostMapping(path = "/addBunch", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Object> addBunch(@RequestBody Map<String, Object> datamap) {
        BunchBuilder bunchBuilder = new BunchBuilder();

        int highestId = 0;
        for (Bunch bunch : bunchesStore.data) {
            if (bunch.getId() > highestId) {
                highestId = bunch.getId();
            }
        }

        bunchBuilder.setId(highestId+1);
        bunchBuilder.setProducts((List<BunchProduct>) datamap.get("products"));

        Bunch product = bunchBuilder.build();

        bunchesStore.addBunch(product);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping(path = "/flowers", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Object> getCatalog() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return new ResponseEntity<Object>(gson.toJson(flowersStore.data), HttpStatus.OK);
    }

    @PostMapping(path = "/addFlower", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Object> addProduct(@RequestBody Map<String, Object> datamap) {
        FlowerBuilder flowerBuilder = new FlowerBuilder();

        int highestId = 0;
        for (Flower flower : flowersStore.data) {
            if (flower.getId() > highestId) {
                highestId = flower.getId();
            }
        }
        flowerBuilder.setId(highestId+1);
        flowerBuilder.setName(datamap.get("name").toString());
        flowerBuilder.setPrice(Double.parseDouble(datamap.get("price").toString()));
        flowerBuilder.setColor(datamap.get("color").toString());
        flowerBuilder.setDescription(datamap.get("description").toString());
        flowerBuilder.setSeasoning(datamap.get("seasoning").toString());

        Flower product = flowerBuilder.build();

        flowersStore.addProduct(product);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Object> getOrders() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return new ResponseEntity<Object>(gson.toJson(ordersStore.data), HttpStatus.OK);
    }

    @PostMapping(path = "/addOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Object> addOrder(@RequestBody Map<String, Object> datamap) {
        OrderBuilder orderBuilder = new OrderBuilder();

        int highestId = 0;
        for (Order order : ordersStore.data) {
            if (order.getId() > highestId) {
                highestId = order.getId();
            }
        }
        orderBuilder.setId(highestId+1);
        orderBuilder.setProducts((List<OrderProduct>) datamap.get("products"));
        orderBuilder.setBunches((List<Bunch>) datamap.get("bunches"));
        orderBuilder.setStatus(datamap.get("status").toString());
        orderBuilder.setCreatedAt(Long.parseLong(datamap.get("createdAt").toString()));
        orderBuilder.setLastStatusChange(Long.parseLong(datamap.get("lastStatusChange").toString()));
        orderBuilder.setClient_firstname(datamap.get("client_firstname").toString());
        orderBuilder.setClient_lastname(datamap.get("client_lastname").toString());
        orderBuilder.setClient_address(datamap.get("client_address").toString());
        orderBuilder.setClient_phone(datamap.get("client_phone").toString());
        orderBuilder.setClient_email(datamap.get("client_email").toString());
        orderBuilder.setClient_comments(datamap.get("client_comments").toString());

        Order order = orderBuilder.build();

        ordersStore.addOrder(order);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
