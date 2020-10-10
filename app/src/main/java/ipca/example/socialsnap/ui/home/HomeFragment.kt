package ipca.example.socialsnap.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import ipca.example.socialsnap.PhotoDetailFragment
import ipca.example.socialsnap.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabNewPhoto.setOnClickListener {
            /*
            val fm = requireActivity().supportFragmentManager
            val fragment = PhotoDetailFragment()
            fm.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment )
                .addToBackStack(null).commit()
             */

             val action =  HomeFragmentDirections.actionNavigationHomeToPhotoDetailFragment()
            it.findNavController().navigate(action)

        }
    }
}