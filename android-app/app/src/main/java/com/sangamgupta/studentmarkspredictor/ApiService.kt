package com.sangamgupta.studentmarkspredictor

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("predict")
    suspend fun predictMarks(
        @Body studentData: StudentData
    ): Response<PredictionResponse>

}