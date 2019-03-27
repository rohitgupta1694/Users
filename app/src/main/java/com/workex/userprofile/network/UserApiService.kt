package com.workex.userprofile.network

import com.workex.userprofile.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UserApiService {

    @GET("/helloWorld")
    fun getUsers() : Observable<List<User>>
}