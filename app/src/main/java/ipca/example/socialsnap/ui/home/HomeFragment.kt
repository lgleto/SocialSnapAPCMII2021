package ipca.example.socialsnap.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipca.example.socialsnap.R
import ipca.example.socialsnap.models.SnapItem
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.row_photo.view.*

class HomeFragment : Fragment() {

    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: LinearLayoutManager? = null

    private var snapItems : MutableList<SnapItem> = arrayListOf()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = mLayoutManager
        mAdapter = PhotosAdapter()
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter

        fabNewPhoto.setOnClickListener {
             val action =  HomeFragmentDirections.actionNavigationHomeToPhotoDetailFragment()
             it.findNavController().navigate(action)
        }
    }

    inner class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

        inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_photo, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return snapItems.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.v.apply {
                this.isClickable = true
                this.tag = position
                //this.imageViewPhoto
                this.textViewDescription.text = snapItems[position].description
            }
        }
    }


}