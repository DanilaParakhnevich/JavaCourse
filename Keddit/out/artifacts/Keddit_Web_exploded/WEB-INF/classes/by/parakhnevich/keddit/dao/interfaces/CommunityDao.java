package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.exception.DaoException;

import java.util.List;

public interface CommunityDao extends BaseDao<Long, Community> {
    public List<Community> getCommunitiesByUserId(long id) throws DaoException;

    public List<Community> getFollowingCommunitiesByUserId(long id) throws DaoException;

    public boolean addFollower(long communityId, long followerId) throws DaoException;

    public boolean deleteFollower(long communityId, long followerId) throws DaoException;

    public boolean deleteByUserId(long id) throws DaoException;
}
