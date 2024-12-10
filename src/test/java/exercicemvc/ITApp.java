package exercicemvc;

import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exercicemvc.Controller.Controller;
import exercicemvc.Model.IModel;
import exercicemvc.Model.PrimaryModel;
import exercicemvc.View.IView;
import exercicemvc.View.TestingView;

@DisplayName("Tests d'intégration: ensemble des composants")
public class ITApp {
    private TestingView testingView;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private static IModel model = new PrimaryModel();

    @AfterAll
    public static void tearAll(){
        model.close();
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        testingView = new TestingView();
        model.addPropertyChangeListener((PropertyChangeListener) testingView);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        model.removePropertyChangeListener((PropertyChangeListener) testingView);
    }

    @Test
    @DisplayName("Validation du meilleur scénario pour les sections")
    public void happyPathSection() {
        Controller controller = new Controller();
        IView testingView = new TestingView();
        controller.setView(testingView);
        controller.setModel(model);
        testingView.setController(controller);
        
        Assertions.assertAll(
            () -> {
                controller.showAllSections();
                Assertions.assertEquals("Droit-Informatique de gestion-", outputStreamCaptor.toString().trim());
            },
            () -> {
                outputStreamCaptor.reset();
                controller.showAllSections();
                Assertions.assertEquals("Droit-Informatique de gestion-", outputStreamCaptor.toString().trim());
            },
            () -> {
                outputStreamCaptor.reset();
                controller.showSections("Droit");                
                Assertions.assertEquals("1-Droit", outputStreamCaptor.toString().trim());

            },
            () -> {
                outputStreamCaptor.reset();
                controller.insertSection("Test");
                Assertions.assertTrue( outputStreamCaptor.toString().trim().matches("[0-9]+-Test"));
            },
            () -> {
                outputStreamCaptor.reset();
                controller.showSections("Test");
                String id = outputStreamCaptor.toString().trim().split("-")[0];                
                outputStreamCaptor.reset();                
                controller.updateSection(id,"Test2");
                Assertions.assertTrue( outputStreamCaptor.toString().trim().matches("[0-9]+-Test2"));
            },
            () -> {
                outputStreamCaptor.reset();
                controller.showSections("Test2");
                String id = outputStreamCaptor.toString().trim().split("-")[0];
                controller.deleteSection(id);                
                outputStreamCaptor.reset();    
                controller.showSections("Test2");            
                Assertions.assertEquals("-1-Test2", outputStreamCaptor.toString().trim());
            }
        ); 
    }

    @Test
    @DisplayName("Validation du meilleur scénario pour les status")
    public void happyPathStatus() {
        Controller controller = new Controller();
        IView testingView = new TestingView();
        controller.setView(testingView);
        controller.setModel(model);
        testingView.setController(controller);
        
        Assertions.assertAll(
            () -> {
                controller.showAllStatus();
                Assertions.assertEquals("Chargé de cours-Etudiant-Employé administratif-", outputStreamCaptor.toString().trim());
            },
            () -> {
                outputStreamCaptor.reset();
                controller.showAllStatus();
                Assertions.assertEquals("Chargé de cours-Etudiant-Employé administratif-", outputStreamCaptor.toString().trim());
            },
            () -> {
                outputStreamCaptor.reset();
                controller.showStatus("Chargé de cours");                
                Assertions.assertEquals("1-Chargé de cours", outputStreamCaptor.toString().trim());

            },
            () -> {
                outputStreamCaptor.reset();
                controller.insertStatus("Test");
                Assertions.assertTrue( outputStreamCaptor.toString().trim().matches("[0-9]+-Test"));
            },
            () -> {
                outputStreamCaptor.reset();
                controller.showStatus("Test");
                String id = outputStreamCaptor.toString().trim().split("-")[0];                
                outputStreamCaptor.reset();                
                controller.updateStatus(id,"Test2");
                Assertions.assertTrue( outputStreamCaptor.toString().trim().matches("[0-9]+-Test2"));
            },
            () -> {
                outputStreamCaptor.reset();
                controller.showStatus("Test2");
                String id = outputStreamCaptor.toString().trim().split("-")[0];
                controller.deleteStatus(id);                
                outputStreamCaptor.reset();    
                controller.showStatus("Test2");            
                Assertions.assertEquals("-1-Test2", outputStreamCaptor.toString().trim());
            }
        ); 
    }

}