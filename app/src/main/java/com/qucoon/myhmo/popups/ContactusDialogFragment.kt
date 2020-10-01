package com.qucoon.myhmo.popups

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.neptune.utils.updateRecycler
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.qucoon.myhmo.R
import com.qucoon.myhmo.dataclasses.ContactItemDataClass
import kotlinx.android.synthetic.main.fragment_contactus_dialog.*
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactusDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactusDialogFragment : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contactus_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
        initView()
    }

     fun initView(){
         val list= listOf<ContactItemDataClass>(
             ContactItemDataClass(R.drawable.telephoneimage,"+2348088180299"),
             ContactItemDataClass(R.drawable.emailimage,"info@myhmo.ng"),
             ContactItemDataClass(R.drawable.globeimage,"www.myhmo.ng")
             )

         updateRecycler(list)
     }

     fun updateRecycler(list: List<ContactItemDataClass>){

         contactRecycler.updateRecycler(context!!, list, R.layout.contact_item, listOf(R.id.ivTinyImage, R.id.tvText),
             { innerViews, position ->
                 val image = innerViews[R.id.ivTinyImage] as ImageView
                 val text = innerViews[R.id.tvText] as TextView

                 text.text = list[position].name
                 image.setImageResource(list[position].image)

             },
             { position ->
                 when(position){
                     0 ->{dialPhoneNumer(list[position].name)}
                     1 ->{openemail(list[position].name)}
                     2 ->{openwebsite(list[position].name)}
                 }
             })

     }

     fun dialPhoneNumer(phone:String){
         startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null)))

     }

     fun openemail(email:String){

         val emailIntent = Intent(Intent.ACTION_SEND)
         emailIntent.type = "text/plain"
         emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(email))
         emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Enquiries about MYHMO")
         emailIntent.putExtra(Intent.EXTRA_TEXT, "I would like to make an enquiry about MYHMO regarding")
         emailIntent.type = "message/rfc822"

         try {
             startActivity(
                 Intent.createChooser(
                     emailIntent,
                     "Send email using..."
                 )
             )
         } catch (ex: android.content.ActivityNotFoundException) {
             Toast.makeText(
                 activity,
                 "No email clients installed.",
                 Toast.LENGTH_SHORT
             ).show()
         }




     }

     fun openwebsite(website:String){
         val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.myhmo.ng"))
         startActivity(browserIntent)
     }

     fun initOnClick(){
         submitButtonLekki.setOnClickListener {
             dismiss()
         }
     }
}