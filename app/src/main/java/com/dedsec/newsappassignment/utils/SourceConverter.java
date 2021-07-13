package com.dedsec.newsappassignment.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import com.dedsec.newsappassignment.model.Source;

public class SourceConverter {

    @TypeConverter
    public String fromSource(Source source) {
        if (source == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Source>() {}.getType();
        return gson.toJson(source, type);
    }

    @TypeConverter
    public Source toSource(String sourceString) {
        if (sourceString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Source>() {}.getType();
        return gson.fromJson(sourceString, type);
    }

}
