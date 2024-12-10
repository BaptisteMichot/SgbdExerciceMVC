package exercicemvc.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import exercicemvc.Controller.Controller;
import exercicemvc.Utilities.Utilities;

public class TestingView implements PropertyChangeListener, IView {
    Controller control;
    private Utilities utilities;

    @Override
    public void setController(Controller control) {
        this.control = control;
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

    @Override
    public Controller getController() {
        return this.control;
    }
    
}