package com.example.go.Interface

import com.example.go.Models.Passenger
import retrofit2.Call
import retrofit2.http.*

interface PassengerApi {
    @GET("passenger")
    //fun getCountriesData(@Query("continent=") continent: String, @Query("country_name=") country_name: String): Call<List<Country>>
    fun getAllPassengers() : Call<List<Passenger>>

    @Headers("Prefer: return=representation")
    @POST("passenger")
    fun addPassenger(@Body passenger: Passenger) : Call<List<Passenger>>

    /*@Header("Content-Type: application/json")*/
    @PATCH("todos?{id=eq.3}")
    fun updatetodosid(@Header("Content-Type: application/json")@Body passenger: Passenger) : Call<List<Passenger>>

}