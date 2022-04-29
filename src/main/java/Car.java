import com.fasterxml.uuid.Generators;

import java.util.ArrayList;
import java.util.UUID;

public class Car {
    public int id = 0;
    public String uuid;
    public String model;
    public String year;
    public ArrayList<Airbag> airbags;
    public String color;

    public Car(int id, String model, String year, ArrayList<Airbag> airbags, String color) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.airbags = airbags;
        this.color = color;
    }

    // ZROB GETTERY I SETTERY XDDASLDADAS
}
