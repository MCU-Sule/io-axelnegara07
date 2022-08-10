package com.example.pt07_2072029;

import com.example.pt07_2072029.model.Komen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.google.gson.Gson;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloController {
    @FXML
    private ListView<Komen> listKomen;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextArea txtKomen;
    private ObservableList<Komen> kList;

    public void initialize() {
        kList = FXCollections.observableArrayList();
        listKomen.setItems(kList);
    }

    public void OnAdd(ActionEvent actionEvent) {
        kList.add(new Komen(txtUsername.getText(),txtKomen.getText()));
        txtUsername.clear();
        txtKomen.clear();
    }

    public void OnSaveIO(ActionEvent actionEvent) {
        BufferedWriter writer;
        String fileName = "data/komen.txt";
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            Gson gson = new Gson();
            String jsonGson = gson.toJson(listKomen.getItems());
            writer.write(jsonGson);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void OnLoadIO(ActionEvent actionEvent) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/komen.txt"));

            String jsonGson = "";
            String string;
            while ((string = reader.readLine()) != null) {
                jsonGson += string;
            }
            Gson gson = new Gson();
            Komen[] komen = gson.fromJson(jsonGson, Komen[].class);
            kList = FXCollections.observableArrayList(komen);
            listKomen.setItems(kList);
            reader.close();

        } catch (FileNotFoundException e) {
            kList = FXCollections.observableArrayList();
            listKomen.setItems(kList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void OnSaveNIO(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Documents", "*.txt");
        chooser.getExtensionFilters().add(extensionFilter);
        chooser.setSelectedExtensionFilter(extensionFilter);
        File file = chooser.showSaveDialog(txtUsername.getScene().getWindow());
        if(file != null) {
            Path path = Paths.get(file.toURI());
            Gson gson = new Gson();
            String jsonGson = gson.toJson(listKomen.getItems());
            try {
                Files.write(path,jsonGson.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void OnLoadNIO(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Documents", "*.txt");
        chooser.getExtensionFilters().add(extensionFilter);
        chooser.setSelectedExtensionFilter(extensionFilter);
        File file = chooser.showOpenDialog(txtUsername.getScene().getWindow());
        if (file != null) {
            Path path = Paths.get(file.toURI());
            try {
                Gson gson = new Gson();
                String jsonString = Files.readString(path);
                Komen[] komen = gson.fromJson(jsonString, Komen[].class);
                kList = FXCollections.observableArrayList(komen);
                listKomen.setItems(kList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}