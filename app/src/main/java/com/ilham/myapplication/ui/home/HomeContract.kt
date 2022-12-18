package com.ilham.myapplication.ui.home

import android.provider.ContactsContract.Data
import com.ilham.myapplication.model.DataVideo
import com.ilham.myapplication.model.ResponseDetailVideo
import com.ilham.myapplication.model.ResponselistVideo
import java.text.FieldPosition


interface HomeContract {

    interface Presenter {
        fun getVideo()
        fun Searchvideo(keyword: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseVideo: ResponselistVideo)
        fun onShowdialogdetail(dataVideo: DataVideo, position: Int)
        fun onShowdialogvideo()
        fun showMessage(message: String)
    }
}