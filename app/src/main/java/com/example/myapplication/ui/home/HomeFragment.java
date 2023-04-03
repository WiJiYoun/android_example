package com.example.myapplication.ui.home;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.databinding.FragmentSlideshowBinding;
import com.example.myapplication.ui.gallery.GalleryFragment;
import com.example.myapplication.ui.slideshow.SlideshowViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    ArrayList<String> toDoList;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ViewGroup rootView =(ViewGroup) inflater.inflate(R.layout.fragment_home,container,false);
        toDoList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_list, toDoList);
        listView = rootView.findViewById(R.id.list_view);
        editText = rootView.findViewById(R.id.edit_text);

        Button addBtn = rootView.findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToList();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;

                //취소선
                textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });

        return rootView;
    }

    public void addItemToList() {
        toDoList.add(editText.getText().toString());
        adapter.notifyDataSetChanged();
        editText.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}