package com.bonedone.util;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

@Log4j
public class RestUtil {
    public static <T> T getFromJson(HttpServletRequest req, Class<T> clazz) {
        Gson gson = new Gson();

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            log.error(e);
        }

        return gson.fromJson(jb.toString(), clazz);
    }
}
