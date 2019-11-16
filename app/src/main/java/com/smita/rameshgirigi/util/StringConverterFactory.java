package com.smita.rameshgirigi.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class StringConverterFactory extends Converter.Factory {
    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        if (String.class.equals(type)) {
            return new Converter<ResponseBody, String>() {

                @Override
                public String convert(ResponseBody value) throws IOException {
                    return value.string();
                }
            };
        }
        return null;
    }

    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] Annotations) {
        if (String.class.equals(type)) {
            return new Converter<String, RequestBody>() {

                @Override
                public RequestBody convert(String value) throws IOException {
                    return RequestBody.create(MEDIA_TYPE, value);
                }
            };
        }
        return null;
    }
}
