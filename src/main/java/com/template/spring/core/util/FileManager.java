package com.template.spring.core.util;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.net.URL;

public class FileManager {

    private FileManager() {

    }

    @SneakyThrows
    public static InputStream copyUrlToInputStream(String urlString) {
        return new URL(urlString).openStream();
    }

}
