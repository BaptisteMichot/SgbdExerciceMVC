package exercicemvc.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Supplier;

import exercicemvc.Controller.Controller;
import exercicemvc.Utilities.Utilities;


public class ViewConsole implements PropertyChangeListener, IView {

    private Controller control;
    private Scanner scanner = new Scanner(System.in);
    private Utilities utilities = new Utilities();

    @Override
    public Controller getController(){
        return this.control;
    }

    @Override
    public void setController(Controller control) {
        this.control = control;
    }


    public void affichageConsole() {
        String choix = "";
        Supplier<String[]> supplier = () -> new String[] {""};

        do{
            System.out.println("Afficher :\nSection → 1\nStatus → 2");
            choix = scanner.nextLine();
        }while(!choix.equals("1") && !choix.equals("2"));
    
        switch(choix){
            case "1":
                control.generateEventHandlerAction("show-sections", supplier).handle(null);
                break;
            case "2":
                control.generateEventHandlerAction("show-status", supplier).handle(null);
                break;
            default:
                System.err.println("Erreur");
                break;
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public void propertyChange(PropertyChangeEvent evt) {

        if(utilities.isArrayListString(evt.getNewValue())){
            
            switch(evt.getPropertyName()) {
                case "listeSection":
                    this.showAllSections((ArrayList<String>) evt.getNewValue());
                    break;
                case "listeStatus":
                    this.showAllStatus((ArrayList<String>) evt.getNewValue());
                    break;
                default:
                    System.err.println("Erreur : " + evt.getPropertyName() + " n'existe pas");
                    break;
            }
        }else{
            System.err.println("Erreur de type pour " + evt.getPropertyName());
        }
    }
    

    @Override
    public void showAllSections(ArrayList<String> listeSection){
        System.out.println("Liste des sections :");
        if(listeSection.isEmpty()){
            System.out.println("Aucune section existante");
        }else{
            for(String section : listeSection){
                System.out.println("- " + section);
            }
        }
    }

    @Override
    public void showAllStatus(ArrayList<String> listeStatus){
        System.out.println("Liste des status :");
        if(listeStatus.isEmpty()){
            System.out.println("Aucun status existant");
        }else{
            for(String status : listeStatus){
                System.out.println("- " + status);
            }
        }
    }


    @Override
    public void launchApp() {
        affichageConsole();
    }

    @Override
    public void stopApp() {
        System.err.println("Pas implémenté en console");
    }

    @Override
    public void showPrincipalWindow() {
        System.err.println("Pas implémenté en console");
    }

    @Override
    public void addNewSection() {
        System.err.println("Pas implémenté en console");
    }

    @Override
    public void showSection(ArrayList<String> infoSection) {
        System.err.println("Pas implémenté en console");
    }

    @Override
    public void addNewStatus() {
        System.err.println("Pas implémenté en console");
    }

    @Override
    public void showStatus(ArrayList<String> infoStatus) {
        System.err.println("Pas implémenté en console");
    }

        
}
