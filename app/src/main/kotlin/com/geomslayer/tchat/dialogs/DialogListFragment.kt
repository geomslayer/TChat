package com.geomslayer.tchat.dialogs

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geomslayer.tchat.DialogItem
import com.geomslayer.tchat.R
import com.geomslayer.tchat.storage.Dialog
import com.geomslayer.tchat.storage.Dialog_Table
import com.geomslayer.tchat.toDialogItem
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.android.synthetic.main.fragment_dialog_list.view.*

class DialogListFragment : Fragment() {

    companion object {
        fun newInstanse(): DialogListFragment {
            return DialogListFragment()
        }
    }

    var listener: Listener? = null
    lateinit var fragmentView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? Listener
                ?: throw RuntimeException("$context must implement ${Listener::class.java.name}")
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentView = inflater.inflate(R.layout.fragment_dialog_list, container, false)
        initRecyclerView()
        return fragmentView
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, layoutManager.orientation)
        val adapter = DialogAdapter().apply {
            dataset = fetchDialogs()
            clickListener = { pos -> listener?.onDialogClick(dataset[pos]) }
        }
        fragmentView.dialogRecyclerView.apply {
            setHasFixedSize(true)
            setLayoutManager(layoutManager)
            setAdapter(adapter)
            addItemDecoration(decoration)
        }
    }

    private fun fetchDialogs(): MutableList<DialogItem> {
        return SQLite.select()
                .from(Dialog::class.java)
                .orderBy(Dialog_Table.creation_time, true)
                .queryList()
                .mapTo(ArrayList<DialogItem>()) { it.toDialogItem() }
    }

    interface Listener {
        fun onDialogClick(dialog: DialogItem)
    }

}