package com.magnus.edutech.webservices;

import com.magnus.edutech.webservices.lectureservices.responseparser.impl.LectureJsonParseImpl;
import com.magnus.edutech.webservices.lectureservices.responseparser.LectureJsonParser;
import com.magnus.edutech.webservices.userservices.responseparser.UsersJsonParser;
import com.magnus.edutech.webservices.userservices.responseparser.impl.UsersJsonParseImpl;

/**
 * Created by joshi on 6/19/2016.
 */
public class JsonParser {
    public JsonParser() {
    }

    // Return lecture parse object
    public LectureJsonParser getLectureParserObject()
    {
        return  new LectureJsonParseImpl();
    }

    // Return User service Response parse
    public UsersJsonParser getUserParserObject()
    {
        return  new UsersJsonParseImpl();
    }
}
