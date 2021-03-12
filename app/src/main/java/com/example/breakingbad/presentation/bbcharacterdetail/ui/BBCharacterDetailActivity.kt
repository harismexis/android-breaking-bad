package com.example.breakingbad.presentation.bbcharacterdetail.ui

import android.content.Context
import android.content.Intent
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.example.breakingbad.R
import com.example.breakingbad.databinding.ActivityBbCharacterDetailBinding
import com.example.breakingbad.databinding.BbCharacterDetailViewBinding
import com.example.breakingbad.framework.base.BaseActivity
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.framework.extensions.populateWithGlide
import com.example.breakingbad.framework.extensions.setTextOrUnknown
import com.example.breakingbad.framework.extensions.wikipediaSpanned
import com.example.breakingbad.presentation.bbcharacterdetail.viewmodel.BBCharacterDetailViewModel

class BBCharacterDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityBbCharacterDetailBinding
    private lateinit var detailBinding: BbCharacterDetailViewBinding
    private lateinit var viewModel: BBCharacterDetailViewModel

    companion object {
        private const val EXTRA_ITEM_ID = "item_id"

        fun Context.startCatDetailActivity(value: String) {
            this.startActivity(createIntent(this, value))
        }

        private fun createIntent(
            context: Context,
            value: String
        ): Intent {
            return Intent(context, BBCharacterDetailActivity::class.java)
                .apply { putExtra(EXTRA_ITEM_ID, value) }
        }
    }

    override fun initialise() {
        super.initialise()
        fetchLocalItem()
    }

    override fun observeLiveData() {
        viewModel.model.observe(this, {
            populate(it)
        })
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)[BBCharacterDetailViewModel::class.java]
    }

    override fun initialiseViewBinding() {
        binding = ActivityBbCharacterDetailBinding.inflate(layoutInflater)
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

    private fun fetchLocalItem() {
        val selectedItemId = intent.getStringExtra(EXTRA_ITEM_ID)
        selectedItemId?.let {
            viewModel.retrieveItemById(it)
        }
    }

    private fun populate(item: BBCharacter) {
        this.populateWithGlide(detailBinding.img, item.imageUrl)
        detailBinding.txtBreedName.setTextOrUnknown(item.name)
        detailBinding.txtOrigin.setTextOrUnknown(item.origin)
        detailBinding.txtDescription.setTextOrUnknown(item.description)
        detailBinding.txtTemperament.setTextOrUnknown(item.temperament)
        detailBinding.txtLifeSpan.setTextOrUnknown(item.lifeSpan)
        detailBinding.txtEnergyLevel.setTextOrUnknown(item.energyLevel.toString())
        detailBinding.txtWikipediaUrl.movementMethod = LinkMovementMethod.getInstance()
        detailBinding.txtWikipediaUrl.text = item.wikipediaSpanned(
            getString(R.string.missing_wiki_link),
            getString(R.string.wikipedia)
        )
    }

}
