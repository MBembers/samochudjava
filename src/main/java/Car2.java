import com.fasterxml.uuid.Generators;

import java.util.ArrayList;
import java.util.UUID;

public class Car2 {
    public int id = 0;
    public UUID uuid = Generators.randomBasedGenerator().generate();;
    public String model;
    public String year;
    public ArrayList<Airbag> airbags;
    public String color;

    public Car2(int id, String model, String year, ArrayList<Airbag> airbags, String color) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.airbags = airbags;
        this.color = color;
    }
}
