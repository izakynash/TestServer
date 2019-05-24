package info.goodline.servertestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://")
            .addConverterFactory(GsonConverterFactory.create())
            // чтобы обрабатывать тип данных из обсервэбла?
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service = retrofit.create(TestEntityService::class.java)

        val repos = service.getAllBooks()

        repos.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<TestEntityJ>> {

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(value: List<TestEntityJ>) {
                    Log.d("seven", "countNext" + value.size)
                }

                override fun onError(e: Throwable) {
                    // TODO: 18.05.2019 Hide progressbar
                    Log.e("seven", "My errors:$e")
                    e.printStackTrace()
                }

                override fun onComplete() {
                    // TODO: 18.05.2019 Hide progressbar
                }
            })
    }
}
