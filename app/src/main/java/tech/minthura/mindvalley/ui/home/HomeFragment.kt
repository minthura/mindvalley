package tech.minthura.mindvalley.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import tech.minthura.mindvalley.R
import tech.minthura.mindvalley.adapters.ParentRecyclerViewAdapter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private val parentRecyclerViewAdapter = ParentRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
        recyclerView.adapter = parentRecyclerViewAdapter
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.episodes.observe(viewLifecycleOwner, {
            parentRecyclerViewAdapter.setEpisodes(it)
        })
        viewModel.channels.observe(viewLifecycleOwner, {
            parentRecyclerViewAdapter.setChannels(it)
        })
        viewModel.categories.observe(viewLifecycleOwner, {
            parentRecyclerViewAdapter.setCategories(it)
        })
        viewModel.isFetching.observe(viewLifecycleOwner, {
            swipeRefreshLayout.isRefreshing = it
        })
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getData()
        }
        viewModel.getData()
    }

}
