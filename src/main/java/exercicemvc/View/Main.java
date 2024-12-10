package exercicemvc.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;

import exercicemvc.Controller.Controller;
import exercicemvc.Utilities.Utilities;

public class Main extends Application implements PropertyChangeListener, IView {
    private static Scene scene;
    private static Stage stage;
    private Pane actualParent; 
    private Controller control;
    private Section sectionWindow;
    private Status statusWindows;
    private Utilities utilities;

    public void setController(Controller control) {
        this.control = control;
        this.sectionWindow = new Section(this);
        this.statusWindows = new Status(this);
    }

    public Controller getController(){
        return this.control;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.utilities = new Utilities();
        switch (evt.getPropertyName()) {
            case "listeSection":
                if (evt.getNewValue() instanceof ArrayList<?>) {
                    ArrayList<?> newValue = (ArrayList<?>) evt.getNewValue();
                    ArrayList<String> stringList = utilities.convertToStringList(newValue);
                    if (stringList != null) {
                        this.showAllSections(stringList);
                    }else{
                        System.err.println("Erreur: listeSection doit être de type ArrayList<String>.");
                    }
                }
                break;
            case "sectionSelected":
                if (evt.getNewValue() instanceof ArrayList<?>) {
                    ArrayList<?> newValue = (ArrayList<?>) evt.getNewValue();
                    ArrayList<String> stringList = utilities.convertToStringList(newValue);
                    if (stringList != null) {
                        this.showSection(stringList);
                    }else{
                        System.err.println("Erreur: sectionSelected doit être de type ArrayList<String>.");
                    }
                }
                break;
            case "listeStatus":
                if (evt.getNewValue() instanceof ArrayList<?>) {
                    ArrayList<?> newValue = (ArrayList<?>) evt.getNewValue();
                    ArrayList<String> stringList = utilities.convertToStringList(newValue);
                    if (stringList != null) {
                        this.showAllStatus(stringList);
                    }else{
                        System.err.println("Erreur: listeStatus doit être de type ArrayList<String>.");
                    }
                }
                break;
            case "statusSelected":
                if (evt.getNewValue() instanceof ArrayList<?>) {
                    ArrayList<?> newValue = (ArrayList<?>) evt.getNewValue();
                    ArrayList<String> stringList = utilities.convertToStringList(newValue);
                    if (stringList != null) {
                        this.showStatus(stringList);
                    }else{
                        System.err.println("Erreur: statusSelected doit être de type ArrayList<String>.");
                    }
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        // Préparation du stage pour gérer la fermeture du programme.
        Main.stage.setOnCloseRequest(this.control.generateCloseEvent());

        // Préparation de la première fenêtre
        showPrincipalWindow();
        stage.show();
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
    public void launchApp() {
        Platform.startup(() -> {
            Stage stage = new Stage();
            try {
                this.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void stopApp() {        
        Platform.exit();
    }

    //Sections
    public void showAllSections(ArrayList<String> listeSection){
         ListView<String> listView = this.sectionWindow.showAllSections(listeSection);
         showPrincipalWindow();
         actualParent.getChildren().add(listView);
    }

    public void showSection(ArrayList<String> infoSection){
        scene.setRoot(this.sectionWindow.showSection(infoSection));
    }

    public void addNewSection(){
        scene.setRoot(this.sectionWindow.addNewSection());
    }

    //Status
    @Override
    public void addNewStatus() {
        scene.setRoot(this.statusWindows.addNewStatus());
    }

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
}