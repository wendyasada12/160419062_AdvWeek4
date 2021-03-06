package com.ubaya.a160419062_advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419062_advweek4.R
import com.ubaya.a160419062_advweek4.model.Student
import com.ubaya.a160419062_advweek4.util.loadImage
import com.ubaya.a160419062_advweek4.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentID)

            observeViewModel()
        }
    }
    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner){
            textID.setText(it.id)
            textName.setText(it.name)
            editDob.setText(it.dob)
            editPhone.setText(it.phone)
            imageStudentDetail.loadImage(it.photoUrl,progressLoadingStudentDetail)
        }
    }
}