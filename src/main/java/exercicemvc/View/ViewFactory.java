package exercicemvc.View;

public class ViewFactory {

    public static IView createView(String selectedView){

        switch(selectedView){
            case "console":
                return new ViewConsole();
            case "application":
                return new Main();   
            default:
                throw new IllegalArgumentException("Erreur : choix de vue incorrect: " + selectedView);
        }
    }
    
}
