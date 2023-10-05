package com.example.appapitest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder
{
    private static final String URL = "http://10.0.2.2:8080/Leos-Api-Project/api/";
    private static Retrofit retrofit =
            new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

    //<S> = ServiceType
    public static <F> F buildService(Class<F> serviceType)
    {
        return retrofit.create(serviceType);
    }
}
