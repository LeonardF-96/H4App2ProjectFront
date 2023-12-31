package com.example.appapitest;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
    Call<Void> createNewPerson(@Body Person person);

    @POST("createPersonJson")
    Call<Integer> createNewPersonJson(@Body RequestBody requestBody);

//    @PUT("Person/{Id}")
//    Call<Void> updatePersonJSON(@Body RequestBody requestBody);

    @PUT("Person/{Id}")
    Call<Void> updatePerson(@Path("Id") int id, @Body Person person);

    @DELETE("Person/{Id}")
    Call<Void> deletePerson(@Path("Id") int id);
}
