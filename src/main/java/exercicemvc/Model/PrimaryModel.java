package exercicemvc.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import exercicemvc.Model.BL.Section;
import exercicemvc.Model.DAL.Sections.ISectionDAO;
import exercicemvc.Model.DAL.Sections.SectionDAO;

public class PrimaryModel implements IModel{
    private PropertyChangeSupport support;
    private ISectionDAO iSectionDAO;

    public PrimaryModel(){
        this.support = new PropertyChangeSupport(this);
        this.iSectionDAO = new SectionDAO("jdbc:postgresql://localhost/exercicemvc", "postgres", "Baptiste0307");
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

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

    @Override
    public void close() {
        this.iSectionDAO.close();
    }

}