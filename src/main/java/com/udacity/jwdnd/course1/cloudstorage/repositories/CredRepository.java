package com.udacity.jwdnd.course1.cloudstorage.repositories;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredRepository {
    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId};")
    Credential[] getCredentials(int userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId};")
    Credential getCredential(int credentialId);

    @Insert(
            "INSERT INTO CREDENTIALS " +
            "(" +
                "userId, " +
                "url, " +
                "userName, " +
                "key, " +
                "password " +
            ") " +
            "VALUES" +
            "(" +
                "#{userId}, " +
                "#{url}, " +
                "#{userName}, " +
                "#{key}, " +
                "#{password} " +
            ");")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET " +
                "key = #{key}, " +
                "password = #{password} " +
            "WHERE credentialId = #{credentialId};")
    int updateCredential(int credentialId, String key, String password);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId} AND userId = #{userId};")
    int deleteCredential(int credentialId, int userId);
}
