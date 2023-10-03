package com.udacity.jwdnd.course1.cloudstorage.repositories;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CredRepository {
    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId};")
    Credential[] getCredentials(int userId);
}
