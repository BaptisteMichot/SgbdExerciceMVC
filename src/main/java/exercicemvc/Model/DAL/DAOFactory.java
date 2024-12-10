package exercicemvc.Model.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import exercicemvc.Model.DAL.Sections.ISectionDAO;
import exercicemvc.Model.DAL.Sections.SectionDAO;
import exercicemvc.Model.DAL.Status.IStatusDAO;
import exercicemvc.Model.DAL.Status.StatusDAO;


public class DAOFactory implements IDAOFactory {

    private Connection connexion;

    final String url = "jdbc:postgresql://127.0.0.1/exercicemvc";
    final String user = "postgres";
    final String password = "Baptiste0307";

    public DAOFactory(){

        try{
            this.connexion = DriverManager.getConnection(url, user, password);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public boolean close() {

        if (this.connexion != null) {
            try {
                this.connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }


    public ISectionDAO createSectionDAO() {

        return new SectionDAO(this.connexion);
    }
    
    public IStatusDAO createStatusDAO() {

        return new StatusDAO(this.connexion);
    }
    
}
