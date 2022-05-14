package com.example.go.Models

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface Booktrip {

    @Headers("Prefer: return=representation")
    @POST("reservation")
    fun addtodos(@Body reservation: Reservation) : Call<List<Reservation>>

    @Headers("Prefer: return=representation")
    @POST("reservation")
    fun addtodos(@Body todos: todos) : Call<List<todos>>

    @Headers("Prefer: return=representation")
    @POST("bookTrip")
    fun addtodos(@Query("isconfirm ") isconfirm  : Boolean, @Query("idtravel") idtravel:Int?, @Query("usernumber") usernumber:String?, @Query("passlist") passlist: String ) : Call<List<Reservation>>
}

