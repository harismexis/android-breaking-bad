package com.harismexis.breakingbad.presentation.screens.player.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.core.domain.Video
import com.harismexis.breakingbad.core.result.VideosResult
import com.harismexis.breakingbad.databinding.DialogVideosBinding
import com.harismexis.breakingbad.framework.util.extensions.makeFullScreenHeight
import com.harismexis.breakingbad.presentation.screens.player.viewmodel.PlayerSharedViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class VideoPickerDialog : DialogFragment(), VideoViewHolder.VideoItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PlayerSharedViewModel by activityViewModels { viewModelFactory }
    private var binding: DialogVideosBinding? = null
    private lateinit var adapter: VideosAdapter
    private var videos: MutableList<Video> = mutableListOf()
    private var dialogView: View? = null

    companion object {
        private const val ARG_CURRENT_VIDEO_ID = "current_video_id"

        fun newInstance(
            selectedVideoId: String
        ): VideoPickerDialog {
            val fragment = VideoPickerDialog()
            val args = Bundle()
            args.putString(ARG_CURRENT_VIDEO_ID, selectedVideoId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
        dialog.makeFullScreenHeight()
    }

    /**
    - Remember:
    This method will be called after onCreate(Bundle) and before
    onCreateView(LayoutInflater, ViewGroup, Bundle).
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogVideosBinding.inflate(LayoutInflater.from(requireContext()))
        dialogView = binding?.root
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(binding?.root)
        setupExitIcon()
        setupRecycler()
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return builder.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return dialogView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        viewModel.updateVideos()
    }

    private fun observeLiveData() {
        viewModel.videos.observe(viewLifecycleOwner, {
            when (it) {
                is VideosResult.Success -> populate(it.items)
                is VideosResult.Error -> populateError(it.error)
            }
        })
    }

    private fun populate(items: List<Video>) {
        binding?.let {
            videos.clear()
            videos.addAll(items)
            val selectedId = arguments?.getString(ARG_CURRENT_VIDEO_ID, videos[0].id)
            val current = videos.indexOfFirst { video -> video.id == selectedId }
            videos[current].isPlaying = true
            adapter.notifyDataSetChanged()
            it.list.scrollToPosition(current)
        }
    }

    private fun populateError(error: Exception) {
        binding?.let {
        }
    }

    private fun setupRecycler() {
        adapter = VideosAdapter(videos, this)
        adapter.setHasStableIds(true)
        binding?.list?.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupExitIcon() {
        binding?.let {
            it.iconExit.setOnClickListener { dismiss() }
        }
    }

    override fun onVideoSelected(item: Video, position: Int) {
        dismiss()
        videos.forEach { it.isPlaying = false }
        videos[position].isPlaying = true
        adapter.notifyDataSetChanged()
        viewModel.loadVideo(item.id)
    }

}