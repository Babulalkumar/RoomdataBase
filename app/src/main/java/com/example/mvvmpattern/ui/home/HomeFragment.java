package com.example.mvvmpattern.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvmpattern.adpter.BooksListAdapter;
import com.example.mvvmpattern.dao.BooksDao;
import com.example.mvvmpattern.databinding.FragmentHomeBinding;
import com.example.mvvmpattern.model.Item;
import com.example.mvvmpattern.model.Items;
import com.example.mvvmpattern.repository.FavRepository;
import com.example.mvvmpattern.viewModel.BooksViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {
    int page = 1;
    private FragmentHomeBinding binding;
    BooksListAdapter catAdapter;
    BooksDao booksDao;
    FavRepository repository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate (inflater, container, false);
        View root = binding.getRoot ();
        repository = new FavRepository (getActivity ().getApplication ());
        binding.searchQueryET.addTextChangedListener (new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.clearBTN.setVisibility (View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length () > 0 && (!binding.searchQueryET.getText ().toString ().trim ().isEmpty ())) {
                    loadRelevantItems (page);
                }
                if (!binding.searchQueryET.getText ().toString ().trim ().isEmpty ()) {
                    loadRelevantItems (page);
                    binding.placeholderTitleTV.setVisibility (View.GONE);
                    binding.placeholderTextTV.setVisibility (View.GONE);
                } else {
                    binding.placeholderTitleTV.setVisibility (View.VISIBLE);
                    binding.placeholderTextTV.setVisibility (View.VISIBLE);
                }
            }
        });


        return root;
    }

    private void loadRelevantItems(int page) {
        String orderBy = "relevance";
        String search_keyword = binding.searchQueryET.getText ().toString ().trim ();
        String finalQuery = search_keyword.replace (" ", "+");

        try {
            BooksViewModel booksViewModel = ViewModelProviders.of (this).get (BooksViewModel.class);
            booksViewModel.getBooks ();
            booksViewModel.getAllCategoryData (finalQuery, page, orderBy, 40);
            booksViewModel.getCategoryBeanLiveData ().observe (getActivity (), volumesResponse -> {
                if (volumesResponse != null) {
                    List<Item> getCats = new ArrayList<> ();
                    getCats = volumesResponse.getItems ();
                    List<Item> finalGetCats = getCats;
                    catAdapter = new BooksListAdapter (getActivity (), getCats, new BooksListAdapter.click () {
                        @Override
                        public void itemClick(int position) {
                            List<Item> list = new ArrayList<> ();
                            list.add (finalGetCats.get (position));
                           // List<Items> booksList = new ArrayList<> ();
                           // booksList = (List<Items>) volumesResponse.getItems ().get (position);
                            repository.insert (list);
                        }
                    });
                    // repository.insert (Collections.singletonList (volumesResponse));
                    catAdapter.getAllDatas (getCats);
                    binding.setDataset (catAdapter);
                } else {
                    //categoryViewModel.getAllCategoryData (getActivity ());
                }
            });
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        binding = null;
    }
}