package com.ilham.myapplication.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilham.myapplication.R
import com.ilham.myapplication.model.Constant
import com.ilham.myapplication.model.DataVideo
import com.ilham.myapplication.ui.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_video.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class VideoAdapter(
    val context: Context, var dataVideo: ArrayList<DataVideo>,
    val clickListener: (DataVideo, Int, String) -> Unit
) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_video, parent, false)
    )

    override fun getItemCount() = dataVideo.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataVideo[position])

        GlideHelper.setImage(
            context,
            Constant.IP_IMAGE + dataVideo[position].video!!,
            holder.view.imvImage
        )

        holder.view.txvDetail.setOnClickListener {
            Constant.Video_id = dataVideo[position].id!!
            clickListener(dataVideo[position], position, "detail")
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataVideo: DataVideo) {
            view.txvjudul.text = dataVideo.judul
            view.txvKategori.text = dataVideo.kategori
            view.txvHarga.text = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
                .format(Integer.valueOf(dataVideo.harga))
        }
    }

    fun setData(newDataVideo: List<DataVideo>) {
        dataVideo.clear()
        dataVideo.addAll(newDataVideo)
        notifyDataSetChanged()
    }
}