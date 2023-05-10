package lt.ku.emissioncalculator.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lt.ku.emissioncalculator.EmissionCalculatorApplication;
import lt.ku.emissioncalculator.dto.EmissionTax;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

@Controller
public class EmissionTaxController {
    @GetMapping("/emission-tax-calculator")
    public String index(Model model) {
        model.addAttribute("fuelTypes", EmissionCalculatorApplication.fuelTypes);
        model.addAttribute("emissionTax", new EmissionTax());
        return "calculators/emission_tax";
    }

    @PostMapping("/emission-tax-calculator")
    public String store(
            Model model,
            @Valid
            @ModelAttribute("emissionTax") EmissionTax emissionTax,
            BindingResult result)
    {

        DecimalFormat priceFormat = new DecimalFormat("#0.00");
        JSONArray prices = EmissionCalculatorApplication.prices;
        JSONArray fuelTypes = EmissionCalculatorApplication.fuelTypes;

        model.addAttribute("fuelTypes", fuelTypes);

        if (result.hasErrors()) {
            return "calculators/emission_tax";
        }

        for (Object obj : prices) {
            JSONObject priceObj = (JSONObject) obj;
            String co2FuelType = priceObj.get("FUEL_CODE").toString();
            double co2From = Double.parseDouble(priceObj.get("CO2_VALUE_FROM").toString());
            double co2To = Double.parseDouble(priceObj.get("CO2_VALUE_TO").toString());
            double co2Price = Double.parseDouble(priceObj.get("PRICE_VALUE").toString());

            // check if the CO2 value falls within the current price range
            if (co2FuelType.equals(emissionTax.getFuelType()) && co2From <= emissionTax.getEmission() && emissionTax.getEmission() <= co2To) {
                model.addAttribute("price", priceFormat.format(co2Price) + "€");
            }
        }

        return "calculators/emission_tax";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    String handleConstraintViolation(ConstraintViolationException e, Model model) {
        System.out.print(e.getMessage());

        model.addAttribute("general_error", "Įvyko vidinė klaida.");

        return "calculators/emission_tax";
    }
}
