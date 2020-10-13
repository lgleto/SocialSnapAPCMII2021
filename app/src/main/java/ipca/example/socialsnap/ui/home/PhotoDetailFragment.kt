package ipca.example.socialsnap.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import ipca.example.socialsnap.R
import ipca.example.socialsnap.models.SnapItem
import kotlinx.android.synthetic.main.activity_photo_detail.*

import java.util.*


class PhotoDetailFragment : Fragment() {

    private var bitmap : Bitmap? = null
    private var date : Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.activity_photo_detail,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fabTakePhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_CODE_PHOTO)
        }

        buttonPublish.setOnClickListener {
            var auth = Firebase.auth
            val currentUser = auth.currentUser

            val db = FirebaseFirestore.getInstance()
            val snap = SnapItem("",
                            editTextDescription.text.toString(),
                            date,
                            currentUser!!.uid)

            db.collection("snaps")
                .add(snap.toHashMap())
                .addOnSuccessListener {
                    requireActivity().supportFragmentManager.popBackStack()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Algo correu mal!", Toast.LENGTH_SHORT).show()
                }
        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode === Activity.RESULT_OK){
            if (requestCode == REQUEST_CODE_PHOTO){
                // new photo has arrive
                data?.extras?.let {
                    bitmap = it.get("data") as Bitmap
                    imageViewPhoto.setImageBitmap(bitmap)
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_PHOTO = 23524
     }
}
