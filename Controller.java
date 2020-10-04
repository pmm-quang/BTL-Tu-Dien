package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Button btSearch;

    @FXML
    public TextField tfSearchedWord;

    @FXML
    public ListView<String> lvWords;

    @FXML
    public TextArea taMeaning;

    DictionaryMangement dictionary = new DictionaryMangement();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btSearch.setOnMouseClicked(event -> {
            System.out.println("Search!!!");
            String searchedWord = tfSearchedWord.getText();
            if (searchedWord != null && searchedWord.equals("") == false) {
                System.out.println("Searched World: " + searchedWord);
                String wordMeaning = dictionary.Interpretation(searchedWord);
                taMeaning.setText(wordMeaning);
            }
        });
        this.initializeWordList();
        lvWords.setOnMouseClicked(event -> {
            String searchedWord = lvWords.getSelectionModel().getSelectedItem();
            if (searchedWord != null && searchedWord.equals("") == false) {
                System.out.println("Searched World: " + searchedWord);
                String wordMeaning = dictionary.Interpretation(searchedWord);
                taMeaning.setText(wordMeaning);
            }
        });
    }

    public void initializeWordList() {
        dictionary.insertFromFile();
        lvWords.getItems().addAll(dictionary.getDictionary().keySet());
    }
}