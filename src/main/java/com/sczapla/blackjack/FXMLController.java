package com.sczapla.blackjack;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class FXMLController implements Initializable {
        
    private Random rand = new Random();
    @FXML
    private Label lbWynik;
    @FXML
    private Button btDobierz;
    @FXML
    private Button btpasuj;
    @FXML
    private Button btNowaGra;
    @FXML
    private ListView<Integer> lvKrupier;
    @FXML
    private ListView<Integer> lvGracz;
    @FXML
    private Label lbSumaGracza;
    @FXML
    private Label lbSumaKrupiera;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btNowaGraAkcja(null);
    }
    @FXML
    private void btDobierzAkcja(ActionEvent event) {
        lvGracz.getItems().add(getNextCard());
        lbSumaGracza.setText(String.valueOf(lvGracz.getItems().stream().mapToInt(Integer::intValue).sum()));
    }

    @FXML
    private void btPasujAkcja(ActionEvent event) {
        croupierPlay();
        int sumaGracz = lvGracz.getItems().stream().mapToInt(Integer::intValue).sum();
        int sumaKrupiera = lvKrupier.getItems().stream().mapToInt(Integer::intValue).sum();
        if(sumaGracz <= 21 && (sumaKrupiera < sumaGracz || sumaKrupiera >21)){
            lbWynik.setText("Wygrałeś!");
        } else {
            lbWynik.setText("Przegrałeś!");
        }
    }

    @FXML
    private void btNowaGraAkcja(ActionEvent event) {
        lvKrupier.getItems().clear();
        lvGracz.getItems().clear();
        lbSumaKrupiera.setText("0");
        lbWynik.setText("Powodzenia!");
        lvGracz.getItems().add(getNextCard());
        lvGracz.getItems().add(getNextCard());
        lbSumaGracza.setText(String.valueOf(lvGracz.getItems().stream().mapToInt(Integer::intValue).sum()));
    }
    
    private Integer getNextCard(){
        int randInt = rand.nextInt(13)+2;
        if(randInt > 10) {
            return 10;
        }
        return randInt;
    }
    
    private void croupierPlay() {
        int sum = 0;
        do {
            lvKrupier.getItems().add(getNextCard());
            sum = lvKrupier.getItems().stream().mapToInt(Integer::intValue).sum();
        } while (sum < 17);
        lbSumaKrupiera.setText(String.valueOf(sum));
    }
 
}
