package exercicemvc;

import exercicemvc.Controller.Controller;

public class App {
    
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.initialize();
        controller.start();
    }
}