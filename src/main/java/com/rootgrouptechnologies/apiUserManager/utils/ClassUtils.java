package com.rootgrouptechnologies.apiUserManager.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.Arrays;

public class ClassUtils {
    public static HttpHeaders configureResponseHeaders() {
        HttpHeaders headers = new HttpHeaders();

        // CORS headers
        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlAllowMethods(Arrays.asList(HttpMethod.GET, HttpMethod.POST, HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS));
        headers.setAccessControlAllowHeaders(Arrays.asList("Origin", "Content-Type", "X-Auth-Token"));

        //Custom-Headers
        headers.add("X-Api-Version", "1");

        return headers;
    }
}
