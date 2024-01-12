package application;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.example.Country;
import com.example.CountryLibrary;



public class CountriesResult extends VBox {

	
    public CountriesResult(int option) {
        // Δημιουργία της λίστας για την εμφάνιση των χωρών
        ListView<String> countryListView = new ListView<>();
        
        // Κλήση της μεθόδου για να πάρετε τις χώρες
        List<Country> countries;
        try {
            CountryLibrary countryLibrary = new CountryLibrary();
            if (option == 1) {
                countries = countryLibrary.getAllCountries();
                
                // Προσθήκη των ονομάτων των χωρών στη λίστα
                for (Country country : countries) {
                    countryListView.getItems().add(country.toString());
                }
            }else if (option == 2) {
                // Λογική για την επιλογή 2 (παράδειγμα)
                Country country = countryLibrary.getCountryByName("Greece");
                countryListView.getItems().add(country.toString());
            } else if (option == 3) {
                // Λογική για την επιλογή 3 (παράδειγμα)
                String language = "Spanish";
                countries = countryLibrary.getCountriesByLanguage(language);
                for (Country country : countries) {
                    countryListView.getItems().add(country.toString());
                }
            } else if (option == 4) {
                // Λογική για την επιλογή 4 (παράδειγμα)
                String currency = "EUR";
                countries = countryLibrary.getCountriesByCurrency(currency);
                for (Country country : countries) {
                    countryListView.getItems().add(country.toString());
                }
            }else {
            	
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Προσθήκη της λίστας στο layout
        getChildren().addAll(countryListView);
    }
}
