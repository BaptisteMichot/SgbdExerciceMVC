package exercicemvc.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.function.Supplier;

import exercicemvc.Controller.Controller;
import exercicemvc.Utilities.Utilities;

public class Main implements PropertyChangeListener, IView {
    private static Scene scene;
    private static Stage stage;
    private Pane actualParent; 
    private Controller control;
    private Section sectionWindow;
    private Status statusWindows;
    private Utilities utilities;


    @Override
    public void launchApp() {
        
        Platform.startup(() -> {
            Stage primaryStage = new Stage();
            try {
                stage = primaryStage;
                stage.setOnCloseRequest(control.generateCloseEvent());
                showPrincipalWindow();
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    
    public void setController(Controller control) {
        this.control = control;
        this.utilities = new Utilities();
        this.sectionWindow = new Section(this);
        this.statusWindows = new Status(this);
    }

    public Controller getController(){
        return this.control;
    }



    @Override
    @SuppressWarnings("unchecked")
    public void propertyChange(PropertyChangeEvent evt) {

        if(utilities.isArrayListString(evt.getNewValue())){
            
            switch(evt.getPropertyName()) {
                case "listeSection":
                    this.showAllSections((ArrayList<String>) evt.getNewValue());
                    break;
                case "sectionSelected":
                    this.showSection((ArrayList<String>) evt.getNewValue());
                    break;
                case "listeStatus":
                    this.showAllStatus((ArrayList<String>) evt.getNewValue());
                    break;
                case "statusSelected":
                    this.showStatus((ArrayList<String>) evt.getNewValue());
                    break;
                default:
                    System.err.println("Erreur : " + evt.getPropertyName() + " n'existe pas");
                    break;
            }
        }else{
            System.err.println("Erreur de type pour " + evt.getPropertyName());
        }
    }


    public void showPrincipalWindow(){
        actualParent = new VBox();
        Supplier<String[]> supplier = () -> new String[] {""};

        // Sections
        HBox boutonSection = new HBox();
        Button afficherSection = new Button("Afficher les sections");
        Button ajouterSection = new Button("Ajouter" );        
        afficherSection.setOnAction(control.generateEventHandlerAction("show-sections", supplier ));
        ajouterSection.setOnAction(control.generateEventHandlerAction("add-section", supplier ));
        boutonSection.getChildren().addAll(afficherSection, ajouterSection);
        actualParent.getChildren().add(boutonSection);

        //Status        
        HBox boutonStatus = new HBox();
        Button afficherStatus = new Button("Afficher les status");
        Button ajouterStatus = new Button("Ajouter" );
        afficherStatus.setOnAction(control.generateEventHandlerAction("show-status", supplier ));
        ajouterStatus.setOnAction(control.generateEventHandlerAction("add-status", supplier ));
        boutonStatus.getChildren().addAll(afficherStatus, ajouterStatus);
        actualParent.getChildren().add(boutonStatus);


        scene = new Scene(actualParent, 640, 480);
        stage.setScene(scene);
    }


    @Override
    public void stopApp() {        
        Platform.exit();
    }

    //Sections
    @Override
    public void showAllSections(ArrayList<String> listeSection){
         ListView<String> listView = this.sectionWindow.showAllSections(listeSection);
         showPrincipalWindow();
         actualParent.getChildren().add(listView);
    }

    @Override
    public void showSection(ArrayList<String> infoSection){
        scene.setRoot(this.sectionWindow.showSection(infoSection));
    }

    @Override
    public void addNewSection(){
        scene.setRoot(this.sectionWindow.addNewSection());
    }

    //Status
    @Override
    public void showAllStatus(ArrayList<String> listeStatus) {
        ListView<String> listView = this.statusWindows.showAllStatus(listeStatus);
        showPrincipalWindow();
        actualParent.getChildren().add(listView);
    }

    @Override
    public void showStatus(ArrayList<String> infoStatus) {
        scene.setRoot(this.statusWindows.showStatus(infoStatus));
    }

    @Override
    public void addNewStatus() {
        scene.setRoot(this.statusWindows.addNewStatus());
    }
}