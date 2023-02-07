package com.example.mvvmpattern.ui.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.mvvmpattern.R;
import com.example.mvvmpattern.adpter.BooksListAdapter;
import com.example.mvvmpattern.dao.BooksDao;
import com.example.mvvmpattern.database.BooksDataBase;
import com.example.mvvmpattern.databinding.FragmentDashboardBinding;
import com.example.mvvmpattern.model.Item;
import com.example.mvvmpattern.repository.FavRepository;
import com.example.mvvmpattern.viewModel.BooksFavViewModel;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    BooksListAdapter catAdapter;
    FavRepository repository;
    public BooksDao postCodeDao;
    List<Item> arrayList = new ArrayList<> ();
    private Paint p = new Paint ();
    BooksFavViewModel posatCodeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate (inflater, container, false);
        View root = binding.getRoot ();
        repository = new FavRepository (getActivity ().getApplication ());
        List<Item> getCats = new ArrayList<> ();
        BooksDataBase database = BooksDataBase.getInstance (getActivity ().getApplication ());
        postCodeDao = database.postCodeDao ();
        posatCodeViewModel = new ViewModelProvider (this).get (BooksFavViewModel.class);

        catAdapter = new BooksListAdapter (getActivity (), getCats, new BooksListAdapter.click () {
            @Override
            public void itemClick(int position) {
                SweepFunction (position);
            }
        });

        posatCodeViewModel.getAllpostCode ().observe (getActivity (), postCodeModels -> {
            catAdapter.getAllDatas (postCodeModels);
            arrayList.clear ();
            for (int a = 0; a < postCodeModels.size (); a++) {
                Item item = postCodeModels.get (a);
                arrayList.add (item);
            }
            binding.setDataset (catAdapter);
            Log.d ("TAG", String.valueOf (postCodeModels));
        });
        SweepFunction (1);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
        binding = null;
    }

    private void SweepFunction(int position1) {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback (0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition ();
                if (direction == ItemTouchHelper.RIGHT) {

                /*    new Handler ().postDelayed (new Runnable () {
                        @Override
                        public void run() {
                            BooksDataBase database = BooksDataBase.getInstance (getActivity ().getApplication ());
                            postCodeDao = database.postCodeDao ();
                            postCodeDao.deleteAll (position);
                        }
                    },1000);*/

                    // postCodeDao.deleteAll (position);
                   /* new Handler (new Handler.Callback () {
                        @Override
                        public boolean handleMessage(@NonNull Message message) {
                            BooksDataBase database = BooksDataBase.getInstance (getActivity ().getApplication ());
                            postCodeDao = database.postCodeDao ();
                            postCodeDao.deleteAll ();
                            return false;
                        }
                    });*/
                    AsyncTask.execute (new Runnable () {
                        @Override
                        public void run() {
                            postCodeDao.deleteAll (arrayList.get (position).getId ());
                            //Perform your Room database operations
                            //FavRepository repository = new FavRepository (getActivity ().getApplication ());
                            //  repository.delete (position);

                        }
                    });
                    posatCodeViewModel.getAllpostCode ().observe (getActivity (), postCodeModels -> {
                        catAdapter.getAllDatas (postCodeModels);
                        arrayList.clear ();
                        for (int a = 0; a < postCodeModels.size (); a++) {
                            Item item = postCodeModels.get (a);
                            arrayList.add (item);
                        }

                        binding.setDataset (catAdapter);
                        Log.d ("TAG", String.valueOf (postCodeModels));
                    });

                    //BooksDataBase database =  Room.databaseBuilder(getActivity (), BooksDataBase.class, "MyDatabase").allowMainThreadQueries().build();

                    //  final complaint_customer_model deletedModel = data.get(position);
                    // catAdapter.EditItem(data.get(position));
                } else if (direction == ItemTouchHelper.LEFT) {
                    /*final complaint_customer_model deletedModel = data.get(position);
                    pending_adapter.ViewItem(deletedModel);*/
                }

            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom () - (float) itemView.getTop ();
                    float width = height / 3;
                    if (dX > 0) {
                        try {
                            p.setColor (Color.parseColor ("#2196f3"));
                            RectF background = new RectF ((float) itemView.getLeft (), (float) itemView.getTop (), dX, (float) itemView.getBottom ());
                            c.drawRect (background, p);
                            icon = BitmapFactory.decodeResource (getResources (), R.drawable.ic_mic_dark);
                            RectF icon_dest = new RectF ((float) itemView.getLeft () + width, (float) itemView.getTop () + width, (float) itemView.getLeft () + 2 * width, (float) itemView.getBottom () - width);
                            c.drawBitmap (icon, null, icon_dest, p);
                        } catch (Exception e) {
                            e.printStackTrace ();
                        }
                    } else {
                        try {
                            p.setColor (Color.parseColor ("#D32F2F"));
                            RectF background = new RectF ((float) itemView.getRight () + dX, (float) itemView.getTop (), (float) itemView.getRight (), (float) itemView.getBottom ());
                            c.drawRect (background, p);
                            icon = BitmapFactory.decodeResource (getResources (), R.drawable.ic_mic_dark);
                            RectF icon_dest = new RectF ((float) itemView.getRight () - 2 * width, (float) itemView.getTop () + width, (float) itemView.getRight () - width, (float) itemView.getBottom () - width);
                            c.drawBitmap (icon, null, icon_dest, p);
                        } catch (Exception e) {
                            e.printStackTrace ();
                        }
                    }
                }
                super.onChildDraw (c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper (simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView (binding.recyclerView);
    }
}