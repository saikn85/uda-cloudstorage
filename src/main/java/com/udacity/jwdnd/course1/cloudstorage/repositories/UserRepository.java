package com.udacity.jwdnd.course1.cloudstorage.repositories;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {
    @Select("SELECT * FROM USERS WHERE userid = #{id};")
    User getUser(int id);

    @Select("SELECT * FROM USERS WHERE username = #{userName};")
    User getUser(String userName);

    @Insert(
            "INSERT INTO USERS" +
            "(" +
                "username, " +
                "firstname, " +
                "lastname, " +
                "salt, " +
                "password" +
            ") " +
            "VALUES" +
            "(" +
                "#{username}, " +
                "#{firstName}, " +
                "#{lastName}, " +
                "#{salt}, " +
                "#{password}" +
            ");")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int createUser(User user);
}
