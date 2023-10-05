package com.example.appapitest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IPersonService
{
    @GET("Person")
    Call<List<Person>> getAllPersons();
    @GET("Person/{Id}")
    Call<Person> getPersonById(@Path("Id")int id);

    @POST("Person")
    Call<Void> addPerson(@Body Person person);

    //@PUT("Frugt/{id}")
}
