package pl.us.tripsbooking.security.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.us.tripsbooking.security.model.UserInfo;
import pl.us.tripsbooking.security.utils.SecurityDtoMapper;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;

@Repository
public class LoginRepository {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SecurityDtoMapper mapper;

    public UserInfo findUserLoginInfoByUsername(String login){
        String sql = "select users.id, users.login, users.password, roles.name as role " +
                     "  from users join roles on users.id_role = roles.id " +
                     " where users.login = :LOGIN";

        Tuple result;

        try{
            result = (Tuple) entityManager.createNativeQuery(sql, Tuple.class)
                                          .setParameter("LOGIN", login)
                                          .getSingleResult();

            return mapper.mapClass(result, UserInfo.class);
        } catch (NoResultException exc){
            return null;
        }
    }
}
