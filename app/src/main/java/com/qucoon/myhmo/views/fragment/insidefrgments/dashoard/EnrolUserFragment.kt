package com.qucoon.myhmo.views.fragment.insidefrgments.dashoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_enrol_user.*


class EnrolUserFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enrol_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        initOnClick()
    }


    fun initOnClick() {

        enrolmentSegment.addOnSegmentClickListener {
            when (it.column) {
                0 -> {
                    Toast.makeText(context, "0", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


    fun initView() {
        (activity as MainActivity).hideTablayout()
        (activity as MainActivity).setActoonBarTitle("Enrolment")

        val typeface = ResourcesCompat.getFont(context!!, R.font.poppinsfont)
        enrolmentSegment.setTypeFace(typeface)
        enrolmentSegment.setSelectedSegment(0)
    }
}