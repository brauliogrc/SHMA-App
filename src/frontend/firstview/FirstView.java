/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.firstview;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author bruno
 */
public class FirstView extends Application {
    
    Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Panel de control");
        
        this.initRootLayout();
    }
    
    private void initRootLayout() {
        try {
            Parent root = FXMLLoader.load( getClass().getResource( "FirstView.fxml" ) );
            Scene scene = new Scene( root );
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        }
        catch( IOException ioex ) {
            System.out.print( ioex.getMessage() );
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
