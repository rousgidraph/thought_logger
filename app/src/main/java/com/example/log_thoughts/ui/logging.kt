package com.example.log_thoughts.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.log_thoughts.R
import com.example.log_thoughts.data.ThoughtLocalDataSource
import com.example.log_thoughts.databinding.FragmentLoggerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class logging : Fragment() {

    @Inject lateinit var logg: ThoughtLocalDataSource
    private lateinit var binding : FragmentLoggerBinding
    private lateinit var textView: TextView
    private lateinit var btn_save: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_logger, container, false)
        binding = FragmentLoggerBinding.bind(view)
        Toast.makeText(context, "Opened up ", Toast.LENGTH_SHORT).show()
        textView = binding.txtThought
        btn_save = binding.btnSave
        btn_save.setOnClickListener {
            if (textView.text.length > 2){
                add_Thought(textView.text.toString())
            }
        }


        return binding.root
    }

    fun add_Thought(Message : String){
        Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show()
        logg.addThought(Message)

    }

}