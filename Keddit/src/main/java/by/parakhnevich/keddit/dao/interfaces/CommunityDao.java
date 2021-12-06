package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.util.List;

/**
 * @see BaseDao
 */
public interface CommunityDao extends BaseDao {
    List<Community> findAll() throws DaoException;

    Community findEntityById(Long id) throws DaoException;

    boolean delete(Community community) throws DaoException;

    boolean delete(Long id) throws DaoException;

    boolean create(Community community) throws DaoException;

    Community update(Community community) throws DaoException;

    List<Community> getCommunitiesByUserId(long id) throws DaoException;

    List<Community> getFollowingCommunitiesByUserId(long id) throws DaoException;

    void addFollower(long communityId, long followerId) throws DaoException;

    void deleteFollower(long communityId, long followerId) throws DaoException;

    List<Community> getCommunitiesByName(String name) throws DaoException;
}
