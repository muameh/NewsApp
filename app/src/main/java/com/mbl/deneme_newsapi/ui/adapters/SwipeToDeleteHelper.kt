package com.mbl.deneme_newsapi.ui.adapters

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mbl.deneme_newsapi.ui.viewmodel.NewViewModel

class SwipeToDeleteHelper(
    private val viewModel: NewViewModel,
    private val adapter: AdaptersFavours
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val currentItem = adapter.differ.currentList.getOrNull(position)

        currentItem?.let { item ->
            // Geçici olarak listeden kaldır
            adapter.differ.submitList(adapter.differ.currentList.toMutableList().apply {
                removeAt(position)
            })

            viewModel.deleteArticle(item.task_id)

            val view = viewHolder.itemView.rootView
            Snackbar.make(view, "Deleted from favorites", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    viewModel.addArticle(item)

                    // Öğeyi tekrar listeye ekle
                    adapter.differ.submitList(adapter.differ.currentList.toMutableList().apply {
                        add(position, item)
                    })
                }
                show()
            }
        }
    }
}

