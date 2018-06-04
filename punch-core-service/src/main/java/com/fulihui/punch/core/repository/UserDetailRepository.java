/*
 * Copyright (c) 2017.  Willard Hu. All rights reserved.
 */

package com.fulihui.punch.core.repository;

import java.util.List;

import com.fulihui.punch.dal.dataobj.TUserDetail;
import com.fulihui.punch.dto.UserDto;

/**
 * @author Willard Hu on 2017/11/1.
 */
public interface UserDetailRepository {

    void insert(TUserDetail record, String operator);

    boolean update(TUserDetail record, String operator);

    UserDto queryByPK(String userId);

    List<UserDto> queryByUserIds(List<String> userIds);
}
