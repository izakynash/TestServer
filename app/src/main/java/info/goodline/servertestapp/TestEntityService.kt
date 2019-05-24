package info.goodline.servertestapp

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface TestEntityService {

    @GET("/books")
    fun getAllBooks(): Observable<List<TestEntityJ>>

}