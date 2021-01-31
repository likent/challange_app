package com.mycompany.my.challenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.my.challenge.R
import com.mycompany.my.challenge.adapters.MovieAdapter
import com.mycompany.my.challenge.databinding.FragmentMovieBinding
import com.mycompany.my.challenge.factories.MovieViewModelFactory
import com.mycompany.my.challenge.viewmodels.MovieViewModel
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject


class MovieFragment @Inject constructor() : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: MovieViewModelFactory

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: FragmentMovieBinding
    private var loadTimer: Timer? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }

        binding.btnMainCancel.setOnClickListener {
            binding.flAnimation.visibility = View.GONE
            binding.btnMainCancel.visibility = View.GONE
            findNavController(this).popBackStack()
        }

        viewModel.getLoadingEvent().observe(viewLifecycleOwner,
            { t ->
                activity?.runOnUiThread{
                    binding.flAnimation.visibility = if (t!!) View.VISIBLE else View.GONE
                    if (t && loadTimer == null) {
                        loadTimer = Timer()
                        loadTimer!!.schedule(object : TimerTask() {
                            override fun run() {
                                activity?.runOnUiThread {
                                    binding.btnMainCancel.visibility = View.VISIBLE
                                }
                                loadTimer!!.cancel()
                                loadTimer = null
                            }
                        }, 3000)
                    } else if (!t) {
                        if (loadTimer != null) {
                            loadTimer!!.cancel()
                            loadTimer = null
                        }
                        binding.btnMainCancel.visibility = View.GONE
                    }
                }
            })

        viewModel.getData().observe(viewLifecycleOwner, {
            movieAdapter.submitList(it)
        })
    }
}
