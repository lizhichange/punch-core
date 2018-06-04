package com.fulihui.punch.core.repository;

import java.util.List;

import com.fulihui.punch.dal.dataobj.UserPunchCountExample;
import com.fulihui.punch.dto.UserPunchCountDTO;

/**
 * The interface User punch count repository.
 *
 * @author lizhi
 */
public interface UserPunchCountRepository {

    List<UserPunchCountDTO> query(UserPunchCountExample example);


      long count(UserPunchCountExample example) ;

    /**
     * Save int.
     *
     * @param dto the dto
     * @return the int
     */
    int save(UserPunchCountDTO dto);

    /**
     * Update int.
     *
     * @param dto the dto
     * @return the int
     */
    int update(UserPunchCountDTO dto);

    /**
     * Query user punch count dto.
     *
     * @param userId the user id
     * @return the user punch count dto
     */
    UserPunchCountDTO query(String userId);
}
