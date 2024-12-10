package exercicemvc.Model.DAL;

import exercicemvc.Model.DAL.Sections.ISectionDAO;
import exercicemvc.Model.DAL.Status.IStatusDAO;

public interface IDAOFactory {
    
    public boolean close();

    public ISectionDAO createSectionDAO();

    public IStatusDAO createStatusDAO();
}
