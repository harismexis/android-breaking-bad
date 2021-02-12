package com.example.jsonfeed.presentation.detail.ui

import android.content.Context
import android.content.Intent
import android.view.View

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders

import com.example.jsonfeed.databinding.ActivityItemDetailBinding
import com.example.jsonfeed.databinding.ItemDetailViewBinding
import com.example.jsonfeed.framework.base.BaseActivity
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.extensions.populateWithGlide
import com.example.jsonfeed.framework.extensions.setTextOrUnknown
import com.example.jsonfeed.presentation.detail.viewmodel.ItemDetailVm

class ItemDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityItemDetailBinding
    private lateinit var detailBinding: ItemDetailViewBinding
    private lateinit var viewModel: ItemDetailVm

    companion object {
        private const val EXTRA_ITEM_ID = "item_id"

        fun Context.startItemDetailActivity(value: String) {
            this.startActivity(createIntent(this, value))
        }

        private fun createIntent(
            context: Context,
            value: String
        ): Intent {
            return Intent(context, ItemDetailActivity::class.java)
                .apply { putExtra(EXTRA_ITEM_ID, value) }
        }
    }

    override fun initialise() {
        super.initialise()
        retrieveSelectedItem()
    }

    override fun observeLiveData() {
        viewModel.model.observe(this, {
            populate(it)
        })
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)[ItemDetailVm::class.java]
    }

    override fun initialiseViewBinding() {
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        detailBinding = binding.itemDetailContainer
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun getToolbar(): Toolbar {
        return binding.itemDetailToolbar
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun retrieveSelectedItem() {
        val selectedItemId = intent.getStringExtra(EXTRA_ITEM_ID)
        selectedItemId?.let {
            viewModel.retrieveItemById(it)
        }
    }

    private fun populate(model: Item) {
        model.imageUrlHiRes?.let {
            this.populateWithGlide(detailBinding.img, it)
        }
        detailBinding.txtName.setTextOrUnknown(model.name)
        detailBinding.txtSupertype.setTextOrUnknown(model.supertype)
        detailBinding.txtSubtype.setTextOrUnknown(model.subtype)
        detailBinding.txtArtist.setTextOrUnknown(model.artist)
        detailBinding.txtRarity.setTextOrUnknown(model.rarity)
        detailBinding.txtSeries.setTextOrUnknown(model.series)
        detailBinding.txtSet.setTextOrUnknown(model.set)
        detailBinding.txtSetCode.setTextOrUnknown(model.setCode)
    }

}