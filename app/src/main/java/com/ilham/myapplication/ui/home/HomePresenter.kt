package com.ilham.myapplication.ui.home

import com.ilham.myapplication.model.ResponseDetailVideo
import com.ilham.myapplication.model.ResponselistVideo
import com.ilham.myapplication.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomePresenter(val view: HomeContract.View) : HomeContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun getVideo() {
        view.onLoading(true)
        ApiService.endpoint.pemutarvideo().enqueue(object : Callback<ResponselistVideo> {
            override fun onResponse(
                call: Call<ResponselistVideo>,
                response: Response<ResponselistVideo>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseVideo: ResponselistVideo? = response.body()
                    view.onResult(responseVideo!!)
                }
            }

            override fun onFailure(call: Call<ResponselistVideo>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun Searchvideo(keyword: String) {
        view.onLoading(true)
        ApiService.endpoint.Searchvideo(keyword).enqueue(object : Callback<ResponselistVideo> {
            override fun onResponse(
                call: Call<ResponselistVideo>,
                response: Response<ResponselistVideo>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responselistVideo: ResponselistVideo? = response.body()
                    view.onResult(responselistVideo!!)
                }
            }

            override fun onFailure(call: Call<ResponselistVideo>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}