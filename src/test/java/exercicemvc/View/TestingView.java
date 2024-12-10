package exercicemvc.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import exercicemvc.Controller.Controller;
import exercicemvc.Utilities.Utilities;

public class TestingView implements PropertyChangeListener, IView {
    Controller control;
    private Utilities utilities;


    public TestingView(){
        this.utilities = new Utilities();
    }

    @Override
    public void setController(Controller control) {
        this.control = control;
    }

    @Override
    public Controller getController() {
        return this.control;
    }

    @Override
    public void launchApp() {
    }

    @Override
    public void stopApp() {
    }

    @Override
    public void showPrincipalWindow() {
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        ArrayList<?> nouvelleValeur = (ArrayList<?>) evt.getNewValue();
        ArrayList<String> listeString = utilities.convertToStringList(nouvelleValeur);
        if(listeString == null){
            System.err.println("Erreur de type pour " + evt.getPropertyName());
            return;
        }

        switch (evt.getPropertyName()) {
            case "listeSection":
                this.showAllSections(listeString);
                break;
            case "sectionSelected":
                this.showSection(listeString);
                break;
            case "listeStatus":
                this.showAllStatus(listeString);
                break;
            case "statusSelected":
                this.showStatus(listeString);
                break;
            default:
                System.err.println("Erreur : " + evt.getPropertyName() + " n'a pas été implémenté");
                break;
        }
    }


    //Sections
    @Override
    public void addNewSection() {
    }

    @Override
    public void showAllSections(ArrayList<String> listeSection) {
        for (String string : listeSection) {
            System.out.print(string+"-");
        }
    }

    @Override
    public void showSection(ArrayList<String> infoSection) {
        System.out.println(infoSection.get(0) + "-" +infoSection.get(1));
    }

    //Status
    @Override
    public void addNewStatus() {
    }

    @Override
    public void showAllStatus(ArrayList<String> listeStatus) {
        for (String string : listeStatus) {
            System.out.print(string+"-");
        }
    }

    @Override
    public void showStatus(ArrayList<String> infoStatus) {
        System.out.println(infoStatus.get(0) + "-" +infoStatus.get(1));
    }
    
}