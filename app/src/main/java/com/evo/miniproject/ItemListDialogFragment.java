package com.evo.miniproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.evo.miniproject.model.ResponseModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evo.miniproject.databinding.FragmentItemListDialogListDialogItemBinding;
import com.evo.miniproject.databinding.FragmentItemListDialogListDialogBinding;

import org.w3c.dom.Text;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ItemListDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";
    private final ResponseModel data;
    private FragmentItemListDialogListDialogBinding binding;

    public ItemListDialogFragment(ResponseModel data) {
        this.data = data;
    }

    // TODO: Customize parameters
    public static ItemListDialogFragment newInstance(ResponseModel data) {
        final ItemListDialogFragment fragment = new ItemListDialogFragment(data);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentItemListDialogListDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.tvId.setText(String.valueOf(data.getId()));
        binding.tvUserId.setText(String.valueOf(data.getUserId()));
        binding.tvTitle.setText(data.getTitle());
        binding.tvBody.setText(data.getBody());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}