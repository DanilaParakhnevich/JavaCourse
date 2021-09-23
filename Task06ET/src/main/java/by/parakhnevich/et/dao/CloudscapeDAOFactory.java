package by.parakhnevich.et.dao;

public class CloudscapeDAOFactory implements DAOFactory {
    CloudscapeSphereDAO cloudscapeCircleDAO = new CloudscapeSphereDAO();

    @Override
    public CloudscapeSphereDAO getCircleDAO() {
        return cloudscapeCircleDAO;
    }
}
