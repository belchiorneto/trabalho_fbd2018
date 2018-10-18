/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spoper_ufc_nesbel;

import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *
 * @author Belchior
 */
public class PainelCadastro {
    public static void show(Stage primaryStage, String nome, Object tabela){
        StackPane secondaryLayout = new StackPane();
        Scene secondScene = new Scene(secondaryLayout, 500, 400);
           
        // grid container
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle(nome);
        
        // campos de texto editáveis
        Label id_albun_label = new Label("Número do Albun:");
        grid.add(id_albun_label, 0, 1);

        TextField id_albun_textfield = new TextField();
        grid.add(id_albun_textfield, 1, 1);
        
        Label pr_compda_label = new Label("Preço de Compra:");
        grid.add(pr_compda_label, 0, 2);

        TextField pr_compra_textfield = new TextField();
        grid.add(pr_compra_textfield, 1, 2);

        Label dt_compra_label = new Label("Data da compra:");
        grid.add(dt_compra_label, 0, 3);
        
        TextField dt_compra_textfield = new TextField();
        grid.add(dt_compra_textfield, 1, 3);
        
        Label dt_gravacao_label = new Label("Data da gravação:");
        grid.add(dt_gravacao_label, 0, 4);
        
        TextField dt_gravacao_textfield = new TextField();
        grid.add(dt_gravacao_textfield, 1, 4);
        
        // lista de tipos de compra
        ComboBox<TiposdeCompra> tiposdecompra_combo = new ComboBox<>();
        Label tipo_decompra_label = new Label("Tipo de Compra:");
        grid.add(tipo_decompra_label, 0, 5);
        
         ObservableList<TiposdeCompra> tiposdecompra =
            FXCollections.observableArrayList(
            new TiposdeCompra(),
            new TiposdeCompra());
        tiposdecompra_combo.setItems(tiposdecompra);
        
        StringConverter<TiposdeCompra> converter_tipo = new StringConverter<TiposdeCompra>() {
            @Override
            public String toString(TiposdeCompra tiposdecompra) {
                return tiposdecompra.getDescricao();
            }

            @Override
            public TiposdeCompra fromString(String id) {
                return tiposdecompra.stream()
                        .filter(item -> id.equals(item.getTipo_id()))
                        .collect(Collectors.toList()).get(0);
            }
        };
        tiposdecompra_combo.setConverter(converter_tipo);
        tiposdecompra_combo.getSelectionModel().select(0);      
        grid.add(tiposdecompra_combo, 1, 5);
        // botao para adicionar um novo tipo
        Button btadicionartipo = new Button();
        btadicionartipo.setText("Add tipo compra");
 
        btadicionartipo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // codigo para add tipo de compra
               PainelCadastro.show(primaryStage, "Cadastrar novo Tipo de compra", new TiposdeCompra());
            }
        });
        grid.add(btadicionartipo, 2, 5);
        
        // lista de gravadoras
        ComboBox<Gravadora> gravadoras_combo = new ComboBox<>();
        Label gravadoras_label = new Label("Gravadora:");
        grid.add(gravadoras_label, 0, 6);
        
         ObservableList<Gravadora> gravadoras =
            FXCollections.observableArrayList(
            new Gravadora(1, "Sony"),
            new Gravadora(2, "Music"));
        gravadoras_combo.setItems(gravadoras);
        
        StringConverter<Gravadora> converter = new StringConverter<Gravadora>() {
            @Override
            public String toString(Gravadora gravadora) {
                return gravadora.getNome();
            }

            @Override
            public Gravadora fromString(String id) {
                return gravadoras.stream()
                        .filter(item -> id.equals(item.getId()))
                        .collect(Collectors.toList()).get(0);
            }
        };
        gravadoras_combo.setConverter(converter);
        gravadoras_combo.getSelectionModel().select(0);
        grid.add(gravadoras_combo, 1, 6);
        
        // botao para adicionar uma nova gravadora
        Button btadicionargravadora = new Button();
        btadicionargravadora.setText("Add gravadora");
 
        btadicionargravadora.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // codigo para add gravadora
            }
        });
        grid.add(btadicionargravadora, 2, 6);
        
        // botões de ação
        Button botaook = new Button();
        botaook.setText("FECHAR");
 
        botaook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               newWindow.hide();
            }
        });
        grid.add(botaook, 0, 7);
        
        Button botaocadastrar = new Button();
        botaocadastrar.setText("CADASTRAR");
 
        botaocadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               String albun_id = id_albun_textfield.getText();
               String pr_compra = pr_compra_textfield.getText();
               String dt_compra = dt_compra_textfield.getText();
               String dt_gravacao = dt_gravacao_textfield.getText();
               String tipo_compra_id = String.valueOf(tiposdecompra_combo.getValue().getTipo_id());
               String gravadora_id = String.valueOf(gravadoras_combo.getValue().getId());
               String campos = ""
                       + "albun_id, "
                       + "pr_compra, "
                       + "dt_compra, "
                       + "dt_gravacao, "
                       + "tipo_compra_id, "
                       + "gravadora_id"; 
               String dados = 
                       albun_id + ", " 
                       + pr_compra + ", " 
                       + dt_compra + ", " 
                       + dt_gravacao + ", " 
                       + tipo_compra_id + ", " 
                       + gravadora_id; 
               
               DbUtils.Insert(campos, dados, "albuns");
            }
        });
        grid.add(botaocadastrar, 1, 7);
        
        secondaryLayout.getChildren().add(grid);
       
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
    }
   
}
