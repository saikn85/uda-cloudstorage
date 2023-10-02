package com.udacity.jwdnd.course1.cloudstorage.repositories;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteRepository {
    @Select("SELECT * FROM NOTES WHERE userId = #{userId};")
    Note[] getNotes(int userId);

    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId};")
    Note getNote(int noteId);

    @Insert(
            "INSERT INTO NOTES" +
            "(" +
                "userId, " +
                "noteTitle, " +
                "noteDescription " +
            ") " +
            "VALUES" +
            "(" +
                "#{userId}, " +
                "#{noteTitle}, " +
                "#{noteDescription}" +
            ");")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int createNote(Note note);

    @Update("UPDATE NOTES SET" +
                "noteTitle = #{noteTitle}," +
                "noteDescription = #{noteDescription}" +
            "WHERE noteId = #{noteId};")
    void updateNote(int noteId, String noteTitle, String noteDescription);
}
