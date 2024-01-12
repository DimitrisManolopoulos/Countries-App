package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main extends Application {

    private Queue<Integer> recentSearches = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Country App");

        Button button1 = new Button("Όλες οι χώρες");
        Button button2 = new Button("Αναζήτηση χώρας με χρήση του ονόματός της");
        Button button3 = new Button("Χώρες που μιλούν μια συγκεκριμένη γλώσσα");
        Button button4 = new Button("Χώρες με συγκεκριμένο νόμισμα");
        Button button5 = new Button("5 τελευταίες αναζητήσεις");

        // Προσθήκη λειτουργιών για τα κουμπιά
        button1.setOnAction(e -> showAllCountries(primaryStage, 1));
        button2.setOnAction(e -> showAllCountries(primaryStage, 2));
        button3.setOnAction(e -> showAllCountries(primaryStage, 3));
        button4.setOnAction(e -> showAllCountries(primaryStage, 4));
        button5.setOnAction(e -> showRecentSearches());

        VBox vbox = new VBox(10); // Το 10 είναι το κενό μεταξύ των κουμπιών
        vbox.setPadding(new Insets(20, 50, 50, 50)); // Ρύθμιση των εξωτερικών περιθωρίων

        vbox.getChildren().addAll(button1, button2, button3, button4, button5);

        // Δημιουργία της σκηνής
        Scene scene = new Scene(vbox, 400, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showRecentSearches() {
        System.out.println("5 Πρόσφατες Αναζητήσεις:");

        VBox recentSearchesLayout = new VBox(10);

        for (Integer search : recentSearches) {
            System.out.println(search);

            Button button = new Button();
            if (search == 1) {
                button.setText("Όλες οι χώρες");
            } else if (search == 2) {
                button.setText("Αναζήτηση Ελλάδας με χρήση του ονόματός της");
            } else if (search == 3) {
                button.setText("Χώρες που μιλούν Ισπανικά");
            } else if (search == 4) {
                button.setText("Χώρες που χρησιμοποιούν το νόμισμα EUR");
            } else {
                button.setText("5 τελευταίες αναζητήσεις");
            }

            button.setOnAction(e -> showAllCountries(new Stage(), search));
            recentSearchesLayout.getChildren().add(button);
        }

        // Δημιουργία νέας σκηνής
        Scene recentSearchesScene = new Scene(recentSearchesLayout, 300, 200);

        // Δημιουργία νέου παραθύρου
        Stage recentSearchesStage = new Stage();
        recentSearchesStage.setTitle("5 Πρόσφατες Αναζητήσεις");
        recentSearchesStage.setScene(recentSearchesScene);

        // Εμφάνιση του παραθύρου
        recentSearchesStage.show();
    }

    private void showAllCountries(Stage primaryStage, int option) {
        try {
            // Δημιουργία νέας σκηνής που περιέχει τις χώρες
            CountriesResult countriesResult = new CountriesResult(option);
            recentSearches.offer(option); 
            if (recentSearches.size() > 5) {
                recentSearches.poll();
            }
            Stage resultsStage = new Stage();
            Scene resultsScene = new Scene(countriesResult, 600, 400);
            resultsStage.setScene(resultsScene);

            // Εμφάνιση του παραθύρου
            resultsStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
