package lt.ku.emissioncalculator;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class EmissionCalculatorApplication {

    public static JSONArray prices = new JSONArray();
    public static JSONArray fuelTypes = new JSONArray();

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(EmissionCalculatorApplication.class, args);

        FileReader reader = new FileReader("config.json");
        JSONParser parser = new JSONParser();
        JSONObject settings = (JSONObject) parser.parse(reader);
        JSONArray priceArray = (JSONArray) settings.get("PRICES");
        JSONArray fuelTypesArray = (JSONArray) settings.get("FUEL_TYPES");
        if (!priceArray.isEmpty()) {
            prices = priceArray;
        }
        if (!fuelTypesArray.isEmpty()) {
            fuelTypes = fuelTypesArray;
        }
    }

}
