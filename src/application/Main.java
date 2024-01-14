package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.Queue;

public class Main extends Application {
	
	class SearchInfo {
	    int option;
	    String searchTerm;

	    public SearchInfo(int option, String searchTerm) {
	        this.option = option;
	        this.searchTerm = searchTerm;
	    }
	}
    private Queue<SearchInfo> recentSearches = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Country App");
        
        // Δημιουργία κουμπιών
        Button button1 = new Button("Όλες οι χώρες");
        Button button2 = new Button("Αναζήτηση χώρας με χρήση του ονόματός της");
        Button button3 = new Button("Αναζήτηση χωρών που μιλούν συγκεκριμένες γλώσσες");
        Button button4 = new Button("Αναζήτηση χωρών που χρησιμοποιούν συγκεκριμένο νόμισμα");
        Button button5 = new Button("5 τελευταίες αναζητήσεις");

        // Προσθήκη λειτουργιών για τα κουμπιά
        button1.setOnAction(e -> showAllCountries(primaryStage, 1, null));
        button2.setOnAction(e -> showSearchDialog(primaryStage, 2,"Αναζήτηση χώρας", "Εισάγετε το όνομα της χώρας στα αγγλικά (πχ. Greece ή Spain κλπ):"));
        button3.setOnAction(e -> showSearchDialog(primaryStage, 3, "Αναζήτηση γλώσσας", "Εισάγετε τη γλώσσα στα αγγλικά (πχ. Greek ή Spanish κλπ"));
        button4.setOnAction(e -> showSearchDialog(primaryStage, 4, "Αναζήτηση νομίσματος", "Εισάγετε το νόμισμα (πχ. EU ή eu ή eur ή euro ή aud κλπ:"));
        button5.setOnAction(e -> showRecentSearches());

        // Δημιουργία και ρύθμιση του layout
        VBox vbox = new VBox(20); // Το 10 είναι το κενό μεταξύ των κουμπιών
        vbox.setPadding(new Insets(10, 50, 50, 50)); // Ρύθμιση των εξωτερικών περιθωρίων
        vbox.setAlignment(Pos.CENTER); // Ορισμός της θέσης του vbox ως ΚΕΝΤΡΟ

        // Προσθήκη των κουμπιών στο layout
        vbox.getChildren().addAll(button1, button2, button3, button4, button5);

        // Δημιουργία της σκηνής
        Scene scene = new Scene(vbox, 700, 550);

        // Ορισμός της σκηνής για το παράθυρο
        primaryStage.setScene(scene);

        // Εμφάνιση του παραθύρου
        primaryStage.show();
    }

    // Μέθοδος για την εμφάνιση των πρόσφατων αναζητήσεων (5η επιλογή)
    private void showRecentSearches() {
        VBox recentSearchesLayout = new VBox(10);

        if (recentSearches.isEmpty()) {
            Label label = new Label();
            label.setText("Δεν υπάρχει καμία αναζήτηση");
            recentSearchesLayout.getChildren().add(label);
        } else {
            for (SearchInfo searchInfo : recentSearches) {
                Button button = new Button();

                switch (searchInfo.option) {
                    case 1:
                        button.setText("Όλες οι χώρες");
                        break;
                    case 2:
                        button.setText("Αναζήτηση " + searchInfo.searchTerm + " με χρήση του ονόματός της");
                        break;
                    case 3:
                        button.setText("Χώρες που μιλούν " + searchInfo.searchTerm);
                        break;
                    case 4:
                        button.setText("Χώρες που χρησιμοποιούν το " + searchInfo.searchTerm);
                        break;
                    default:
                        button.setText("5 τελευταίες αναζητήσεις: " + searchInfo.searchTerm);
                        break;
                }

                button.setOnAction(e -> showAllCountries(new Stage(), searchInfo.option, searchInfo.searchTerm));

                recentSearchesLayout.getChildren().add(button);
            }
        }

        Scene recentSearchesScene = new Scene(recentSearchesLayout, 300, 200);
        Stage recentSearchesStage = new Stage();
        recentSearchesStage.setTitle("5 Πρόσφατες Αναζητήσεις");
        recentSearchesStage.setScene(recentSearchesScene);
        recentSearchesStage.show();
    }

    
    private void showSearchDialog(Stage primaryStage, int option, String title, String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(prompt);

        dialog.showAndWait().ifPresent(result -> showAllCountries(primaryStage, option, result));
    }
    
    // Μέθοδος για την εμφάνιση όλων των χωρών
    private void showAllCountries(Stage primaryStage, int option, String SearchTerm) {
        try {
            // Δημιουργία νέας σκηνής που περιέχει τις χώρες
            CountriesResult countriesResult = new CountriesResult(option,SearchTerm);

            // Προσθήκη της επιλογής στις πρόσφατες αναζητήσεις
            recentSearches.offer(new SearchInfo(option, SearchTerm));

            // Εάν υπερβαίνουν τον αριθμό των 5, αφαίρεση της παλαιότερης
            if (recentSearches.size() > 5) {
                recentSearches.poll();
            }

            // Δημιουργία νέου παραθύρου
            Stage resultsStage = new Stage();
            Scene resultsScene = new Scene(countriesResult, 600, 400);
            resultsStage.setScene(resultsScene);

            // Εμφάνιση του παραθύρου
            resultsStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Η main μέθοδος για την εκκίνηση της εφαρμογής
    public static void main(String[] args) {
        launch(args);
    }
}