package com.udacity.jwdnd.course1.cloudstorage.repositories;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileRepository {
    @Select("SELECT * FROM FILES WHERE userId = #{userId};")
    File[] getFiles(int userId);
}
