package wowArchaelogy;/**
 * Created by Лиззард on 07.07.2016.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class archaelogy extends Application {

    Scene mainScene;
    TableView<Item> table;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        TableColumn<Item, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(400);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Item, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table = new TableView<>();
        table.setItems(getItems());
        table.getColumns().addAll(idColumn, nameColumn, priceColumn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table);

        Scene mainScene = new Scene(vbox, 300, 300);

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Archaelogy");
        primaryStage.show();

    }

    public ObservableList<Item> getItems() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/wow-archaelogy", "helios", "71f46fec");
            Statement myStatement = conn.createStatement();
            ResultSet rs = myStatement.executeQuery("SELECT * FROM `archaeology` ");
            rs.beforeFirst();
            int rowCount = rs.last() ? rs.getRow() : 0;
            for (int i = 0; i < rowCount; i++) {
                items.add(new Item(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
            }
            rs.close();
            conn.close();

        } catch (Exception e) {
            System.out.print("Something gone wrong - Error:" + e);
        }
        return items;
    }
}
