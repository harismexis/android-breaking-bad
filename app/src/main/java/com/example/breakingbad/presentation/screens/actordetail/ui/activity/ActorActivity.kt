package com.example.breakingbad.presentation.screens.actordetail.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.breakingbad.R
import com.example.breakingbad.presentation.screens.actordetail.ui.fragment.ActorDetailFragment

class ActorActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_ITEM_ID = "item_id"

        fun Context.startActorActivity(itemId: Int) {
            this.startActivity(createIntent(this, itemId))
        }

        private fun createIntent(
            context: Context,
            itemId: Int
        ): Intent {
            return Intent(context, ActorActivity::class.java)
                .apply { putExtra(EXTRA_ITEM_ID, itemId) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor)
        if (intent.hasExtra(EXTRA_ITEM_ID)) {
            val selectedItemId = intent.extras?.getInt(EXTRA_ITEM_ID)
            selectedItemId?.let {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.actor_container, ActorDetailFragment.newInstance(it))
                transaction.commit()
            }
        }
    }
}