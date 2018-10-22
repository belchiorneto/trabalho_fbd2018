/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpoPeer;


import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
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
    PlayList playlist = new PlayList(1,"");
    StackPane root;
    Stage principal;
    GridPane grid;
    int linhas_grid = 1;
    Button bt_salvaPlaylist;
    @Override
    public void start(Stage primaryStage){
        principal = primaryStage;
        // grid container
        grid = new GridPane();
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
       
        grid.add(titulo, 2, linhas_grid);
        linhas_grid++ ;
        grid.add(new Text(10, 20, "Numero"), 0, linhas_grid);
        grid.add(new Text(10, 20, "Descrição"), 1, linhas_grid);
        grid.add(new Text(10, 20, "Data da Compra"), 2, linhas_grid);
        grid.add(new Text(10, 20, "Data da Gravação"), 3, linhas_grid);
        grid.add(new Text(10, 20, "Tipo de compra"), 4, linhas_grid);
        linhas_grid++ ;
        for(Albun albun : albuns){
            if(albun != null){    
                Text id = new Text(10, 20, String.valueOf(albun.albun_id));
                id.setFill(Color.BLUE);
                grid.add(id, 0, linhas_grid);
                Text descr = new Text(10, 20, albun.descr);
                descr.setFill(Color.BLUE);
                grid.add(descr, 1, linhas_grid);
                Text dt_compra = new Text(10, 20, String.valueOf(albun.dt_compra));
                dt_compra.setFill(Color.BLUE);
                grid.add(dt_compra, 2, linhas_grid);
                Text dt_gravacao = new Text(10, 20, String.valueOf(albun.dt_gravacao));
                dt_gravacao.setFill(Color.BLUE);
                grid.add(dt_gravacao, 3, linhas_grid);
                Text tipo_compra = new Text(10, 20, String.valueOf(albun.tipo_compra));
                tipo_compra.setFill(Color.BLUE);
                grid.add(tipo_compra, 4, linhas_grid);
                linhas_grid++ ;
                imprimeCds(albun.cds);
                
            }
        }
        
        
    
        root = new StackPane();
        
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
    void imprimeCds(Cd[] cds){
        
        for(Cd cd: cds){
            if(cd != null) {
                Text idcd = new Text(10, 20, "cd num: " + String.valueOf(cd.cd_id) + " (faixas)");
                idcd.setFill(Color.BLACK);
                grid.add(idcd, 0, linhas_grid);
                Text nome = new Text(10, 20, "Nome");
                nome.setFill(Color.BLACK);
                grid.add(nome, 1, linhas_grid);
                Text duracao_header = new Text(10, 20, "Duração");
                duracao_header.setFill(Color.BLACK);
                grid.add(duracao_header, 2, linhas_grid);
                Text add_faixa = new Text(10, 20, "Adicionar à playlist");
                add_faixa.setFill(Color.BLACK);
                grid.add(add_faixa, 3, linhas_grid);
                linhas_grid++;
                for(Faixa faixa: cd.faixas){
                    if(faixa != null) {
                        CheckBox cb = new CheckBox();
                        cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                            public void changed(ObservableValue<? extends Boolean> ov,
                              Boolean old_val, Boolean new_val) {
                              if(cb.isSelected()){
                                playlist.faixas.put(faixa.faixa_id, faixa);
                                if (! root.getChildren().contains(bt_salvaPlaylist)) {
                                     // botões de ação
                                    bt_salvaPlaylist = new Button();
                                    bt_salvaPlaylist.setText("SALVAR PLAYLIST");

                                    bt_salvaPlaylist.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                           SalvaPlayList();
                                        }
                                    });
                                    grid.add(bt_salvaPlaylist, 1, linhas_grid);
                                }
                              }else{
                                playlist.faixas.remove(faixa.faixa_id);
                              }
                           }
                         });
                       
                        Text id = new Text(10, 20, "Faixa " + String.valueOf(faixa.faixa_id));
                        id.setFill(Color.BLUE);
                        grid.add(id, 0, linhas_grid);
                        Text descr = new Text(10, 20, String.valueOf(faixa.descr));
                        descr.setFill(Color.BLUE);
                        grid.add(descr, 1, linhas_grid);
                        Text duracao = new Text(10, 20, String.valueOf(faixa.duracao));
                        duracao.setFill(Color.BLUE);
                        grid.add(duracao, 2, linhas_grid);
                        grid.add(cb, 3, linhas_grid);
                        linhas_grid++;
                    }
                }
            }
        
        }
    }
    void SalvaPlayList(){
        int linhsGrid = 1;
        StackPane secondaryLayout = new StackPane();
        Scene secondScene = new Scene(secondaryLayout, 600, 500);
           
        // grid container
        GridPane gridplaylist = new GridPane();
        gridplaylist.setAlignment(Pos.CENTER);
        gridplaylist.setHgap(10);
        gridplaylist.setVgap(10);
        gridplaylist.setPadding(new Insets(25, 25, 25, 25));
        
        // New window (Stage)
        Stage newWindow = new Stage();
        GridPane gridhead = new GridPane();
        gridhead.setAlignment(Pos.CENTER);
        gridhead.setHgap(10);
        gridhead.setVgap(10);
        gridhead.setPadding(new Insets(25, 25, 25, 25));
        
        newWindow.setTitle("Dados da playlist");
        // campos de texto editáveis
        Label lb_numeroplaylist = new Label("Numero da playlist:");
        gridhead.add(lb_numeroplaylist, 0, 1);
        
        TextField tf_numeroplaylist = new TextField();
        tf_numeroplaylist.setMinWidth(20);
        tf_numeroplaylist.setPrefWidth(40);
        tf_numeroplaylist.setMaxWidth(60);
       
        gridhead.add(tf_numeroplaylist, 1, 1);
        
        Label lb_nomeplaylist = new Label("Nome da playlist:");
        gridhead.add(lb_nomeplaylist, 2, 1);

        TextField tf_nomeplaylist = new TextField();
        tf_nomeplaylist.setMinWidth(20);
        tf_nomeplaylist.setPrefWidth(200);
        tf_nomeplaylist.setMaxWidth(300);
        gridhead.add(tf_nomeplaylist, 3, 1);
     
        gridplaylist.add(gridhead, 1, linhsGrid);
        linhsGrid++;
        for(Faixa faixa : playlist.faixas.values()){
            System.out.println("dados da faixas: " + faixa.duracao +"/"+ faixa.faixa_id +"/"+faixa.descr);
            Text descr_faixa = new Text(10, 20, faixa.descr);
            descr_faixa.setFill(Color.BLUE);
            gridplaylist.add(descr_faixa, 1, linhsGrid);
            linhsGrid++;
        }
        Button salvarplaylist = new Button();
        salvarplaylist.setText("SALVAR");
 
        salvarplaylist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playlist.nome = tf_nomeplaylist.getText();
                playlist.playlist_id = Integer.parseInt(tf_numeroplaylist.getText());
                playlist.salvaPlaylist();
            }
        });
        gridplaylist.add(salvarplaylist, 1, linhsGrid);
        secondaryLayout.getChildren().add(gridplaylist);
        newWindow.setScene(secondScene);
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(principal);

        // Set position of second window, related to primary window.
        newWindow.setX(principal.getX() + 200);
        newWindow.setY(principal.getY() + 100);

        newWindow.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}