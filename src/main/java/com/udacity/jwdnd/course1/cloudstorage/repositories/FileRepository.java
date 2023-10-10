package com.udacity.jwdnd.course1.cloudstorage.repositories;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileRepository {
    @Select("SELECT * FROM FILES WHERE userId = #{userId};")
    File[] getFiles(int userId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId};")
    File getFile(int fileId);

    @Select("SELECT * FROM FILES WHERE fileName LIKE #{fileName} AND userId = #{userId};")
    File getFileByFileName(String fileName, int userId);

    @Insert(
            "INSERT INTO FILES" +
            "(" +
                "userId, " +
                "fileName, " +
                "contentType, " +
                "fileSize, " +
                "fileData" +
            ") " +
            "VALUES" +
            "(" +
                "#{userId}, " +
                "#{fileName}, " +
                "#{contentType}, " +
                "#{fileSize}, " +
                "#{fileData}" +
            ");")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int createFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId} AND userId = #{userId};")
    int deleteFile(int fileId, int userId);
}
