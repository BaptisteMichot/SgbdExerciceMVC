package exercicemvc.Controller;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.security.InvalidParameterException;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;

import exercicemvc.Model.IModel;
import exercicemvc.Model.PrimaryModel;
import exercicemvc.View.IView;
import exercicemvc.View.ViewFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

public class Controller {
    private IModel model;
    private IView view;
    

    public void initialize() {
        this.model = new PrimaryModel();
        String selectedView = getDefaultView();
        this.view = ViewFactory.createView(selectedView);

        if (PropertyChangeListener.class.isAssignableFrom(view.getClass())){
            PropertyChangeListener pcl = (PropertyChangeListener) view;            
            model.addPropertyChangeListener(pcl);
        }
        view.setController(this);
    }


    private String getDefaultView() {
        Properties properties = new Properties();
        String pathConfigView = "src/main/resources/exercicemvc/ConfigView.properties";

        try(InputStream input = new FileInputStream(pathConfigView)) {
            properties.load(input);
            String selectedView = properties.getProperty("default.view", "application");
            return selectedView;
        }catch(IOException e){
            System.err.println("Erreur lors de la lecture du fichier de configuration : " + e.getMessage());
            return "application";
        }
    }


    public void start(){
        if(view!=null){
            this.view.launchApp();
        }
    }

    public EventHandler<ActionEvent> generateEventHandlerAction(String action, Supplier<String[]> params){    
        Consumer<String[]> function = this.generateConsumer(action);        
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                function.accept(params.get());;
            }
            
        };
    }

    public EventHandler<MouseEvent> generateEventHandlerMouse(String action, Supplier<String[]> params){    
        Consumer<String[]> function = this.generateConsumer(action);        
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (arg0.getClickCount() == 2 ){
                    function.accept(params.get());
                }
            }
            
        };
    }

    public EventHandler<WindowEvent> generateCloseEvent(){
        return new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                stop();
                System.exit(0);
            }
        };
    }

    private Consumer<String[]> generateConsumer(String action){
        Consumer<String[]> t;
        switch (action) {
            case "show-sections":
                t = (x) -> this.showAllSections();
                break;                
            case "show-section":
                t = (x) -> this.showSections(x[0]);
                break;
            case "add-section":
                t = (x) -> this.view.addNewSection();
                break;
            case "insert-section":
                t = (x) -> this.insertSection(x[0]);
                break;
            case "update-section":
                t = (x) -> this.updateSection(x[0], x[1]);
                break;
            case "delete-section":
                t = (x) -> this.deleteSection(x[0]);
                break;
            case "show-status":
                t = (x) -> this.showAllStatus();
                break;                
            case "show-single-status":
                t = (x) -> this.showStatus(x[0]);
                break;
            case "add-status":
                t = (x) -> this.view.addNewStatus();
                break;
            case "insert-status":
                t = (x) -> this.insertStatus(x[0]);
                break;
            case "update-status":
                t = (x) -> this.updateStatus(x[0], x[1]);
                break;
            case "delete-status":
                t = (x) -> this.deleteStatus(x[0]);
                break;        
            default:
                throw new InvalidParameterException(action + " n'existe pas.");
        }
        return t;
    }

    public void setModel(IModel model){
        this.model = model;
    }

    public void setView(IView view){
        this.view = view;
    }

    //Sections
    public void showAllSections(){
        this.model.getAllSection();
    }

    public void showSections(String sectionName){
        this.model.getSection(sectionName);
    }

    public void deleteSection(String id){
        this.model.deleteSection(id);
    }

    public void updateSection(String id, String nom){
        this.model.updateSection(id, nom);
    }

    public void insertSection(String nom){
        this.model.insertSection(nom);
    }

    //Status
    public void showAllStatus(){
        this.model.getAllStatus();
    }

    public void showStatus(String statusName){
        this.model.getStatus(statusName);
    }

    public void deleteStatus(String id){
        this.model.deleteStatus(id);
    }

    public void updateStatus(String id, String nom){
        this.model.updateStatus(id, nom);
    }

    public void insertStatus(String nom){
        this.model.insertStatus(nom);
    }


    public void stop(){
        this.model.close();        
        this.view.stopApp();
    }
}