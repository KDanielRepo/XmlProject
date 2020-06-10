import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class UI extends Application {
    JaxBHelper jaxBHelper;
    XmlKurs xmlKurs;
    BorderPane borderPane;
    TableViewHelper tableViewHelper;
    @Override
    public void start(Stage primaryStage) throws Exception {
        borderPane = new BorderPane();
        VBox vBox = new VBox();
        jaxBHelper = new JaxBHelper();

        Label fromLabel = new Label("Z:");
        TextField fromField = new TextField();
        Label toLabel = new Label("Do:");
        TextField toField = new TextField();
        Label departureLabel = new Label("godzina odjazdu:");
        TextField departureField = new TextField();
        Label arrivalLabel = new Label("godzina przyjazdu:");
        TextField arrivalField = new TextField();
        Label autokarLabel = new Label("nr autokaru:");
        TextField autokarField = new TextField();
        Button send = new Button("Wyślij");
        xmlKurs  = jaxBHelper.unmarshall();
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //xmlKurs  = jaxBHelper.unmarshall();
                XmlTrasa xmlTrasa = new XmlTrasa();
                xmlTrasa.setAutokar(autokarField.getText());
                xmlTrasa.setFrom(fromField.getText());
                xmlTrasa.setTo(toField.getText());
                xmlTrasa.setGodzinaOdj(departureField.getText());
                xmlTrasa.setGodzinaPrzy(arrivalField.getText());

                xmlKurs.getTrasas().add(xmlTrasa);
                jaxBHelper.marshall(xmlKurs);
                updateTable();
            }
        });
        Label removeLabel = new Label("usuń wpis numer:");
        TextField removeField = new TextField();
        Button remove = new Button("Usuń");
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                xmlKurs  = jaxBHelper.unmarshall();
                xmlKurs.getTrasas().remove((int)tableViewHelper.getSelectionModel().getSelectedIndex());
                jaxBHelper.marshall(xmlKurs);
                updateTable();
            }
        });
        vBox.getChildren().addAll(fromLabel,fromField,toLabel,toField,departureLabel,departureField,arrivalLabel,arrivalField,autokarLabel,autokarField,send,removeLabel,removeField,remove);
        borderPane.setLeft(vBox);
        if(xmlKurs!=null){
            tableCreate();
        }
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(800);
        primaryStage.show();

    }
    public void tableCreate(){
        List<XmlTrasa> list = xmlKurs.getTrasas();
        ObservableList<?> data = FXCollections.observableList(list);
        try {
            tableViewHelper = new TableViewHelper(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        tableViewHelper.setItems(data);
        borderPane.setCenter(tableViewHelper);
    }
    public void updateTable(){
        List<XmlTrasa> list = xmlKurs.getTrasas();
        borderPane.getChildren().remove(tableViewHelper);
        ObservableList<?> data = FXCollections.observableList(list);
        try {
            tableViewHelper = new TableViewHelper(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        tableViewHelper.setItems(data);
        borderPane.setCenter(tableViewHelper);
    }

    public static void main(String[] args) {
        launch(UI.class,args);
    }
}
