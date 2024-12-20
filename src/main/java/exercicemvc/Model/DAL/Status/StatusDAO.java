package exercicemvc.Model.DAL.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exercicemvc.Model.BL.Status;

public class StatusDAO implements IStatusDAO {
    Connection connexion;
    PreparedStatement insertStatus;   
    PreparedStatement updateStatus;
    PreparedStatement deleteStatus;
    PreparedStatement getIDStatus;
    PreparedStatement getStatus;


    public StatusDAO(Connection connexion) {
        try {
            this.connexion = connexion;
            Statement statement = connexion.createStatement();
            try {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS status (id SERIAL PRIMARY KEY, nom VARCHAR(30))");
            } catch (SQLException e) {
                // La table existe déjà. Log pour le cas où.
                e.printStackTrace();
            }
            statement.close();
            this.insertStatus = this.connexion.prepareStatement("INSERT into Status (nom) VALUES (?)");
            this.updateStatus = this.connexion.prepareStatement("UPDATE Status SET nom=? WHERE id=?");
            this.deleteStatus = this.connexion.prepareStatement("DELETE FROM Status WHERE id=?");
            this.getIDStatus = this.connexion.prepareStatement("SELECT id FROM Status WHERE nom=?");
            this.getStatus = this.connexion.prepareStatement("SELECT id, nom FROM Status ORDER BY id ASC");

            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean close() {
        boolean ret = true;
       if (this.updateStatus != null) {
            try {
                this.updateStatus.close();
            } catch (SQLException e) {
                e.printStackTrace();
                ret = false;
            }
        }

        if (this.getIDStatus != null) {
            try {
                this.getIDStatus.close();
            } catch (SQLException e) {
                e.printStackTrace();
                ret = false;
            }
        }
        if (this.deleteStatus != null) {
            try {
                this.deleteStatus.close();
            } catch (SQLException e) {
                e.printStackTrace();
                ret = false;
            }
        }
        
        if (this.getStatus != null) {
            try {
                this.getStatus.close();
            } catch (SQLException e) {
                e.printStackTrace();
                ret = false;
            }
        }
        
        if (this.insertStatus != null) {
            try {
                this.insertStatus.close();
            } catch (SQLException e) {
                e.printStackTrace();
                ret = false;
            }
        }
        if (this.connexion != null) {
            try {
                this.connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
                ret = false;
            }
        }

        return ret;
    }

    @Override
    public ArrayList<Status> getStatus() {
        ArrayList<Status> listeStatus = new ArrayList<Status>();
        try {
            ResultSet set = this.getStatus.executeQuery();
            while (set.next()) {
                Status status = new Status(set.getInt(1), set.getString(2));
                listeStatus.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeStatus;

    }

    @Override
    public int getIDStatus(String nom) {
        int id = -1;
        try {
            this.getIDStatus.setString(1, nom);
            ResultSet set = this.getIDStatus.executeQuery();
            while (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public boolean updateStatus(int id, String nom) {
        try {        
            this.updateStatus.setString(1, nom);
            this.updateStatus.setInt(2, id);    
            this.updateStatus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteStatus(int id) {
        try {
            this.deleteStatus.setInt(1, id);
            this.deleteStatus.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addStatus(String nom) {
        try {
            this.insertStatus.setString(1, nom);
            this.insertStatus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}