package com.example.log_thoughts.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.log_thoughts.R
import com.example.log_thoughts.data.Thought
import com.example.log_thoughts.data.ThoughtLocalDataSource
import com.example.log_thoughts.databinding.FragmentThoughtsBinding
import com.example.log_thoughts.di.DateFormatter
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import javax.inject.Inject

@AndroidEntryPoint
class thoughts : Fragment() {

    @Inject   lateinit var logger: ThoughtLocalDataSource
    @Inject   lateinit var dateFormatter: DateFormatter

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding : FragmentThoughtsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_thoughts, container, false)
        binding = FragmentThoughtsBinding.bind(view)
        recyclerView = binding.listThoughts.apply {
            setHasFixedSize(true)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById<RecyclerView>(R.id.list_thoughts).apply {
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()

        logger.getAllLogs { logs -> recyclerView.adapter = thoughtViewAdapter(logs,dateFormatter) }

    }
}


/*
*Recycler view adaper for the list
*/
private class thoughtViewAdapter(
    private val thoughtsDataSet : List<Thought>,
    private val dateFormatatter : DateFormatter
): RecyclerView.Adapter<thoughtViewAdapter.ThoughtViewHolder>(){

    class ThoughtViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThoughtViewHolder {
            return ThoughtViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.text_row_item, parent, false) as TextView
            )

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ThoughtViewHolder, position: Int) {
        val thought = thoughtsDataSet[position]
        Log.d("Something", "onBindViewHolder: "+position)
        holder.textView.text = "${thought.msg}\n\t${dateFormatatter.formatDate(thought.timestamp)}"

    }

    override fun getItemCount(): Int {
        return thoughtsDataSet.size
    }


}