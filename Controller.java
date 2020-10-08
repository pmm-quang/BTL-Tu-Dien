package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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

    private String Word = "";

    private DictionaryMangement dictionary = new DictionaryMangement();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tfSearchedWord.setOnKeyReleased(this::textFieldSearch);

        btSearch.setOnMouseClicked(this::buttonSearch);

        this.initializeWordList();

        lvWords.setOnMouseClicked(this::listViewSearch);
    }

    public void initializeWordList() {
        dictionary.insertFromFile();
        lvWords.getItems().addAll(dictionary.getDictionary().keySet());
    }

    private void textFieldSearch(KeyEvent event) {
        String searchedWord = tfSearchedWord.getText();
        if (event.getCode() != null && !event.getCode().equals(KeyCode.ENTER)) {
            Word = searchedWord;
            dictionary.deleteListSame();
            lvWords.getItems().clear();
            lvWords.getItems().addAll(dictionary.Same(Word));
            System.out.println(Word);
        } else if (event.getCode().equals(KeyCode.ENTER)) {
            this.interprtationList(searchedWord);
            Word = null;
        }
    }

    private void interprtationList(String searchedWord) {
        String wordMeaning = "";
        for (int i = 0; i < dictionary.Interpretation(searchedWord).size(); i++) {
            wordMeaning += dictionary.Interpretation(searchedWord).get(i) + '\n';
        }
        taMeaning.setText(wordMeaning);
    }

    private void buttonSearch(MouseEvent event) {
        System.out.println("Search!!!");
        String searchedWord = tfSearchedWord.getText();
        if (searchedWord != null && searchedWord.equals("") == false) {
            System.out.println("Searched World: " + searchedWord);
            this.interprtationList(searchedWord);
        }
    }
    private void listViewSearch(MouseEvent event) {
        String searchedWord = lvWords.getSelectionModel().getSelectedItem();
        if (searchedWord != null && searchedWord.equals("") == false) {
            System.out.println("Searched World: " + searchedWord);
            this.interprtationList(searchedWord);
        }
    }
}
