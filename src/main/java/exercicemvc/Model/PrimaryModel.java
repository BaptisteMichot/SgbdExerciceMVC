package exercicemvc.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import exercicemvc.Model.BL.Section;
import exercicemvc.Model.DAL.DAOFactory;
import exercicemvc.Model.DAL.Sections.ISectionDAO;
import exercicemvc.Model.BL.Status;
import exercicemvc.Model.DAL.Status.IStatusDAO;

public class PrimaryModel implements IModel{

    DAOFactory factory = new DAOFactory();

    private PropertyChangeSupport support;
    private ISectionDAO iSectionDAO;
    private IStatusDAO iStatusDAO;

    public PrimaryModel(){
        this.support = new PropertyChangeSupport(this);
        this.iSectionDAO = factory.createSectionDAO();
        this.iStatusDAO = factory.createStatusDAO();
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    //Section
    @Override
    public void getAllSection(){
        ArrayList<Section> sections = this.iSectionDAO.getSections();
        ArrayList<String> sectionsName = new ArrayList<>();
        for (Section section : sections) {
            sectionsName.add(section.getNom());
        }
        support.firePropertyChange("listeSection", "", sectionsName);        
    }

    @Override
    public void getSection(String sectionName){
        int id = this.iSectionDAO.getIDSection(sectionName);
        ArrayList<String> infoSection = new ArrayList<>();
        infoSection.add(Integer.toString(id));
        infoSection.add(sectionName);
        support.firePropertyChange("sectionSelected", "", infoSection );
    }

    @Override
    public void deleteSection(String id) {
        this.iSectionDAO.deleteSection(Integer.parseInt(id));
        this.getAllSection();
    }

    @Override
    public void updateSection(String id, String nom) {
        this.iSectionDAO.updateSection(Integer.parseInt(id), nom);
        this.getSection(nom);
    }

    @Override
    public void insertSection(String nom) {
        this.iSectionDAO.addSection(nom);
        this.getSection(nom);
    }


    //Status
    @Override
    public void getAllStatus(){
        ArrayList<Status> status = this.iStatusDAO.getStatus();
        ArrayList<String> statusName = new ArrayList<>();
        for (Status sta : status) {
            statusName.add(sta.getNom());
        }
        support.firePropertyChange("listeStatus", "", statusName);        
    }

    @Override
    public void getStatus(String statusName){
        int id = this.iStatusDAO.getIDStatus(statusName);
        ArrayList<String> infoStatus = new ArrayList<>();
        infoStatus.add(Integer.toString(id));
        infoStatus.add(statusName);
        support.firePropertyChange("statusSelected", "", infoStatus );
    }

    @Override
    public void deleteStatus(String id) {
        this.iStatusDAO.deleteStatus(Integer.parseInt(id));
        this.getAllStatus();
    }

    @Override
    public void updateStatus(String id, String nom) {
        this.iStatusDAO.updateStatus(Integer.parseInt(id), nom);
        this.getStatus(nom);
    }

    @Override
    public void insertStatus(String nom) {
        this.iStatusDAO.addStatus(nom);
        this.getStatus(nom);
    }



    @Override
    public void close() {
        this.iSectionDAO.close();
        this.iStatusDAO.close();
    }

}