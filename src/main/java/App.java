import static spark.Spark.*;

import com.fasterxml.uuid.Generators;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Objects;

public class App {
    public static ArrayList<CarOld> carsOld;
    public static ArrayList<Car> cars = new ArrayList<Car>();
    public static int carsCount = 0;
    public static void main(String[] args) {
        port(5000);
        carsOld = new ArrayList<CarOld>();
        staticFiles.location("/public");

        post("/add", (req, res) -> addCarPost(req, res));
        post("/json", (req, res) -> getJsonPost(req, res));
        post("/delete", (req, res) -> deleteCarPost(req, res));
        post("/update", (req, res) -> updateCarPost(req, res));

    }

    static String addCar(Request req, Response res) {
        CarOld newCar = new CarOld(Integer.parseInt(req.queryParams("doors")), req.queryParams("damaged") == "on" ? true : false, req.queryParams("model"), req.queryParams("country"));
        carsOld.add(newCar);
        res.type("application/json");
        return "dodano samochod, ilosc samochodow: " + carsOld.size();
    }

    static String getText(Request req, Response res) {
        Gson gson = new Gson();
        return gson.toJson(carsOld, ArrayList.class );
    }
    static String getJson(Request req, Response res) {
        res.type("application/json");
        Gson gson = new Gson();
        return gson.toJson(carsOld, ArrayList.class );
    }

    static String deleteAll(Request req, Response res) {
        carsOld = new ArrayList<CarOld>();
        return "Deleted all";
    }
    static String delete(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));
        carsOld.remove(id);
        return "Deleted " + id;
    }
    static String update(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));
        CarOld car = carsOld.get(id);
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
        for(CarOld car : carsOld){
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
        Car newCar = gson.fromJson(req.body(), Car.class);
        carsCount++;
        newCar.id = carsCount;
        newCar.uuid = Generators.randomBasedGenerator().generate().toString();
        cars.add(newCar);
        return gson.toJson(newCar);
    }
    static String getJsonPost(Request req, Response res){
        res.type("application/json");
        Gson gson = new Gson();
        return gson.toJson(cars);
    }

    static String deleteCarPost(Request req, Response res){
        res.type("text/html");
        Gson gson = new Gson();
        String uuid = gson.fromJson(req.body(), Car.class).uuid;
        boolean deleted = cars.removeIf(car -> Objects.equals(car.uuid, uuid));
        return deleted ? "true" : "false";
    }

    static String updateCarPost(Request req, Response res){
        return "aa";
    }
}
