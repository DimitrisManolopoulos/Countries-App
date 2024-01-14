package application;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import java.util.List;
import com.example.Country;
import com.example.CountryLibrary;



public class CountriesResult extends VBox {

	
    public CountriesResult(int option, String SearchTerm) {
        // Δημιουργία της λίστας για την εμφάνιση των χωρών
        ListView<String> countryListView = new ListView<>();        
        List<Country> countries;
        try {
            CountryLibrary countryLibrary = new CountryLibrary();
            if (option == 1) {
            	// Καλώ την μέθοδο της βιβλιοθήκης getAllCountries και αποθηκεύω τα αποτελέσματα στο countries
                countries = countryLibrary.getAllCountries();
                // Προσθέτω την κάθε χώρα στο listView
                for (Country country : countries) {
                    countryListView.getItems().add(country.toString());
                }
            }else if (option == 2) {
            	// Καλώ την μέθοδο της βιβλιοθήκης getCountryByName και αποθηκεύω τα αποτελέσματα στο country
                Country country = countryLibrary.getCountryByName(SearchTerm);
                // Προσθέτω την χώρα στο listView
                countryListView.getItems().add(country.toString());
            } else if (option == 3) {
            	// Καλώ την μέθοδο της βιβλιοθήκης getCountriesByLanguage και αποθηκεύω τα αποτελέσματα στο countries
                countries = countryLibrary.getCountriesByLanguage(SearchTerm);
                for (Country country : countries) {
                    // Προσθέτω την κάθε χώρα στο listView
                    countryListView.getItems().add(country.toString());
                }
            } else if (option == 4) {
            	// Καλώ την μέθοδο της βιβλιοθήκης getCountriesByCurrency και αποθηκεύω τα αποτελέσματα στο countries
                countries = countryLibrary.getCountriesByCurrency(SearchTerm);
                for (Country country : countries) {
                    // Προσθέτω την κάθε χώρα στο listView
                    countryListView.getItems().add(country.toString());
                }
            }else {
            	//  Εδώ δεν υλοποιώ κάτι για την 5η επιλογή καθώς η υλοποίηση της είναι στη Main
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Προσθήκη της λίστας στο layout
        getChildren().addAll(countryListView);
    }
}
