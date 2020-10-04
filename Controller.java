package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

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

    String Word = "";
    DictionaryMangement dictionary = new DictionaryMangement();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tfSearchedWord.setOnKeyReleased(event -> {
            String searchedWord = tfSearchedWord.getText();
                if (event.getCode() != null && ! event.getCode().equals(KeyCode.ENTER)) {
                    Word = searchedWord;
                //    String searchedWord = tfSearchedWord.getText();
                    dictionary.deleteListSame();
                    lvWords.getItems().clear();
                    lvWords.getItems().addAll(dictionary.Same(Word));

                    System.out.println(Word);
                } else if (event.getCode().equals(KeyCode.ENTER)) {
                    String wordMeaning = dictionary.Interpretation(searchedWord);
                    taMeaning.setText(wordMeaning);
                    Word = null;
                }
        });

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
    //    lvWords.getItems().addAll(dictionary.Same("He"));
    }
}
