package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.Queue;

public class Main extends Application {

    // Ουρά για τις πρόσφατες αναζητήσεις
    private Queue<Integer> recentSearches = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Country App");
        
        // Δημιουργία κουμπιών
        Button button1 = new Button("Όλες οι χώρες");
        Button button2 = new Button("Αναζήτηση Ελλάδας με χρήση του ονόματός της");
        Button button3 = new Button("Χώρες που μιλούν Ισπανικά");
        Button button4 = new Button("Χώρες που χρησιμοποιούν το ευρώ");
        Button button5 = new Button("5 τελευταίες αναζητήσεις");

        // Προσθήκη λειτουργιών για τα κουμπιά
        button1.setOnAction(e -> showAllCountries(primaryStage, 1));
        button2.setOnAction(e -> showAllCountries(primaryStage, 2));
        button3.setOnAction(e -> showAllCountries(primaryStage, 3));
        button4.setOnAction(e -> showAllCountries(primaryStage, 4));
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
        // Δημιουργία νέου layout για τις πρόσφατες αναζητήσεις
        VBox recentSearchesLayout = new VBox(10);
        // Αν δεν υπάρχει καμία αναζήτηση
        if (recentSearches.isEmpty()) {
            Label label = new Label();
            label.setText("Δεν υπάρχει καμία αναζήτηση");
            // Προσθήκη του κουμπιού στο layout
            recentSearchesLayout.getChildren().add(label);
        } else {
            // Εμφάνιση κάθε πρόσφατης αναζήτησης ως ένα κουμπί
        	for (Integer search : recentSearches) {
                System.out.println(search);

                Button button = new Button();
                // Ορισμός κειμένου και λειτουργιών για κάθε κουμπί
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

                // Ορισμός της λειτουργίας κάθε κουμπιού
                button.setOnAction(e -> showAllCountries(new Stage(), search));

                // Προσθήκη του κουμπιού στο layout
                recentSearchesLayout.getChildren().add(button);
            }
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

    // Μέθοδος για την εμφάνιση όλων των χωρών
    private void showAllCountries(Stage primaryStage, int option) {
        try {
            // Δημιουργία νέας σκηνής που περιέχει τις χώρες
            CountriesResult countriesResult = new CountriesResult(option);

            // Προσθήκη της επιλογής στις πρόσφατες αναζητήσεις
            recentSearches.offer(option);

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