package com.ilham.myapplication.ui.home

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.MediaController
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ilham.myapplication.R
import com.ilham.myapplication.model.Constant
import com.ilham.myapplication.model.DataVideo
import com.ilham.myapplication.model.ResponseDetailVideo
import com.ilham.myapplication.model.ResponselistVideo
import com.ilham.myapplication.ui.utils.GlideHelper
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dialog_detail.*
import kotlinx.android.synthetic.main.dialog_detail.view.*
import kotlinx.android.synthetic.main.dialog_detailvideo.*
import kotlinx.android.synthetic.main.dialog_detailvideo.view.*

class HomeActivity : AppCompatActivity(), HomeContract.View {

    lateinit var presenter: HomePresenter
    lateinit var video: DataVideo
    lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter = HomePresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getVideo()
    }

    override fun initActivity() {
    }

    override fun initListener() {
        videoAdapter =
            VideoAdapter(this, arrayListOf()) { dataVideo: DataVideo, position: Int, type: String ->
                Constant.Video_id = dataVideo.id!!

                when (type) {
                    "detail" -> onShowdialogdetail(dataVideo, position)
                }

                video = dataVideo
            }

        rcvProduk.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }

        edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                Constant.KEYWORD = edtSearch.text.toString()
                presenter.Searchvideo(Constant.KEYWORD)
                true
            } else {
                false
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progresbar.visibility = View.VISIBLE
            }
            false -> {
                progresbar.visibility = View.GONE
            }
        }
    }

    override fun onResult(responseVideo: ResponselistVideo) {
        val dataVideo: List<DataVideo> = responseVideo.datavideo
        if (responseVideo.status == true) {
            videoAdapter.setData(dataVideo)
        } else {
            showMessage(responseVideo.message)
        }
    }

    override fun onShowdialogdetail(dataVideo: DataVideo, position: Int) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_detail, null)
        GlideHelper.setImage(
            applicationContext,
            Constant.IP_IMAGE + dataVideo.video!!,
            view.imvgambardetail
        )
        view.txvJuduldetail.text = dataVideo.judul
        view.txvkategori.text = dataVideo.kategori
        view.txvhargadetail.text = dataVideo.harga
        view.txvdetail.text = dataVideo.deskripsi

        view.play.setOnClickListener {
            Constant.Video_id
            onShowdialogvideo()

//            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()
    }

    override fun onShowdialogvideo() {
        val dialog = BottomSheetDialog(this)
        val mediaController = MediaController(this)
        val video = video
        mediaController.setAnchorView(imagevideo1)
        val view = layoutInflater.inflate(R.layout.dialog_detailvideo, null)

        val onlineUri = Uri.parse(Constant.IP_IMAGE + video.video)
        view.imagevideo1.setMediaController(mediaController)
        view.imagevideo1.setVideoURI(onlineUri)
        view.imagevideo1.requestFocus()
        view.imagevideo1.start()

        dialog.setContentView(view)
        dialog.show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}