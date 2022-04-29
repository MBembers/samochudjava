import static spark.Spark.*;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.ArrayList;

public class App {
    public static ArrayList<Car> cars;
    public static ArrayList<Car2> car2s = new ArrayList<Car2>();
    public static int car2sCount = 0;
    public static void main(String[] args) {
        port(5000);
        cars = new ArrayList<Car>();
        staticFiles.location("/public");
        get("/test", (req, res) -> "test");
        get("/add", (req, res) -> addCar(req, res));
        get("/text", (req, res) -> getText(req, res));
        get("/json", (req, res) -> getJson(req, res));
        get("/html", (req, res) -> getHtml(req, res));
        get("/deleteall", (req, res) -> deleteAll(req, res));
        get("/delete/:id", (req, res) -> delete(req, res));
        get("/update/:id", (req, res) -> update(req, res));

        post("/add", (req, res) -> addCarPost(req, res));
        post("/json", (req, res) -> getJsonPost(req, res));
        post("/delete", (req, res) -> deleteCarPost(req, res));
        post("/update", (req, res) -> updateCarPost(req, res));

    }

    static String addCar(Request req, Response res) {
        Car newCar = new Car(Integer.parseInt(req.queryParams("doors")), req.queryParams("damaged") == "on" ? true : false, req.queryParams("model"), req.queryParams("country"));
        cars.add(newCar);
        res.type("application/json");
        return "dodano samochod, ilosc samochodow: " + cars.size();
    }

    static String getText(Request req, Response res) {
        Gson gson = new Gson();
        return gson.toJson(cars, ArrayList.class );
    }
    static String getJson(Request req, Response res) {
        res.type("application/json");
        Gson gson = new Gson();
        return gson.toJson(cars, ArrayList.class );
    }

    static String deleteAll(Request req, Response res) {
        cars = new ArrayList<Car>();
        return "Deleted all";
    }
    static String delete(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));
        cars.remove(id);
        return "Deleted " + id;
    }
    static String update(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));
        Car car = cars.get(id);
        if(req.queryParams("model") != null){
            car.model = req.queryParams("model");
        }
        if(req.queryParams("doors") != null){
            car.doors = Integer.parseInt(req.queryParams("doors"));
        }
        if(req.queryParams("country") != null){
            car.country = req.queryParams("country");
        }
        if(req.queryParams("damaged") != null){
            car.damaged = Boolean.parseBoolean(req.queryParams("damaged"));
        }
        return "updated " + id;
    }
    static String getHtml(Request req, Response res){
        res.type("text/html");
        String response = "<table style='border: 1px solid gray'>";
        int i = 0;
        for(Car car : cars){
            response += String.format("<tr><td style='border: 1px solid gray'>%d</td><td style='border: 1px solid gray'>%s</td><td style='border: 1px solid gray'>%b</td><td style='border: 1px solid gray'>%d</td><td style='border: 1px solid gray'>%s</td></tr>", i, car.model, car.damaged, car.doors, car.country);
            i++;
        }
        response+="</table>";
        return response;
    }

    static String addCarPost(Request req, Response res){
        res.type("application/json");
        Gson gson = new Gson();
        System.out.println(req.body());
        Car2 newCar2 = gson.fromJson(req.body(), Car2.class);
        car2sCount++;
        newCar2.id = car2sCount;
        car2s.add(newCar2);
        return gson.toJson(newCar2);
    }
    static String getJsonPost(Request req, Response res){
        res.type("application/json");
        Gson gson = new Gson();
        return gson.toJson(car2s);
    }

    static String deleteCarPost(Request req, Response res){
        return "aa";
    }

    static String updateCarPost(Request req, Response res){
        return "aa";
    }
}
