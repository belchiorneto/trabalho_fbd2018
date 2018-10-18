/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spoper_ufc_nesbel;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JTextField;

/**
 *
 * @author iranilda
 */
public class SpoPer_UFC_nesbel extends Application {
    Albun[] albuns;
    @Override
    public void start(Stage primaryStage) {
         // grid container
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        DbConn.OpenConnection();
        albuns = new Albun[100];
        Albun a = new Albun();
        albuns =  a.listaAlbuns();
        Text titulo = new Text(40, 10, "ALBUNS");
        titulo.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
       
        grid.add(titulo, 2, 1);
        int count = 2; // linha da tabela
        grid.add(new Text(10, 20, "Numero"), 0, count);
        grid.add(new Text(10, 20, "Descrição"), 1, count);
        grid.add(new Text(10, 20, "Data da Compra"), 2, count);
        grid.add(new Text(10, 20, "Data da Gravação"), 3, count);
        grid.add(new Text(10, 20, "Tipo de compra"), 4, count);
        count++;
        for(Albun albun : albuns){
            if(albun != null){    
                Text id = new Text(10, 20, String.valueOf(albun.albun_id));
                id.setFill(Color.BLUE);
                grid.add(id, 0, count);
                Text descr = new Text(10, 20, albun.descr);
                descr.setFill(Color.BLUE);
                grid.add(descr, 1, count);
                Text dt_compra = new Text(10, 20, String.valueOf(albun.dt_compra));
                dt_compra.setFill(Color.BLUE);
                grid.add(dt_compra, 2, count);
                Text dt_gravacao = new Text(10, 20, String.valueOf(albun.dt_gravacao));
                dt_gravacao.setFill(Color.BLUE);
                grid.add(dt_gravacao, 3, count);
                Text tipo_compra = new Text(10, 20, String.valueOf(albun.tipo_compra));
                tipo_compra.setFill(Color.BLUE);
                grid.add(tipo_compra, 4, count);
                count++;
                imprimeCds(albun.cds, grid, count);
                
            }
        }
        
        
    
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root, 300, 250);
        
        root.getChildren().add(grid);
        
        // setando tamanho da tela
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        
        primaryStage.setTitle("SpoPer !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    void imprimeCds(Cd[] cds, GridPane g, int linha){
        
        for(Cd cd: cds){
            if(cd != null) {
                Text idcd = new Text(10, 20, "cd num: " + String.valueOf(cd.cd_id) + " (faixas)");
                idcd.setFill(Color.BLACK);
                g.add(idcd, 0, linha);
                Text nome = new Text(10, 20, "Nome");
                nome.setFill(Color.BLACK);
                g.add(nome, 1, linha);
                Text duracao_header = new Text(10, 20, "Duração");
                duracao_header.setFill(Color.BLACK);
                g.add(duracao_header, 2, linha);
                Text add_faixa = new Text(10, 20, "Adicionar à playlist");
                add_faixa.setFill(Color.BLACK);
                g.add(add_faixa, 3, linha);
                linha++;
                for(Faixa faixa: cd.faixas){
                    if(faixa != null) {
                        CheckBox cb = new CheckBox();
                        cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                            public void changed(ObservableValue<? extends Boolean> ov,
                              Boolean old_val, Boolean new_val) {
                              System.out.println(cb.isSelected());
                              if(cb.isSelected()){
                                  AdicionaFaixa(faixa.faixa_id);
                              }
                           }
                         });
                       
                        Text id = new Text(10, 20, "Faixa " + String.valueOf(faixa.faixa_id));
                        id.setFill(Color.BLUE);
                        g.add(id, 0, linha);
                        Text descr = new Text(10, 20, String.valueOf(faixa.descr));
                        descr.setFill(Color.BLUE);
                        g.add(descr, 1, linha);
                        Text duracao = new Text(10, 20, String.valueOf(faixa.duracao));
                        duracao.setFill(Color.BLUE);
                        g.add(duracao, 2, linha);
                        g.add(cb, 3, linha);
                        linha++;
                    }
                }
            }
        
        }
    }
    void AdicionaFaixa(int id){
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}