package com.task.assignment;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.task.taskone_listofapps.MainClass;

import java.util.ArrayList;
import java.util.List;

public class TaskOnActivity extends AppCompatActivity {

    private TasONeAdapter adapter;
    private RecyclerView recyclerview;
    private LinearLayoutManager linearLayoutManager_vertical;
    private List<PackageInfo> listOfapps;

    private ArrayList<AppListModel> arrayListApps = new ArrayList<>();
    private ArrayList<AppListModel> arrayListApps_2 = new ArrayList<>();
    private SearchView serachView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_on);


        initControlls();

    }

    private void initControlls() {

        getAppListFromModule();


        adapter = new TasONeAdapter(arrayListApps, TaskOnActivity.this);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        serachView = (SearchView) findViewById(R.id.serach);
        linearLayoutManager_vertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter.setHasStableIds(true);
        recyclerview.setLayoutManager(linearLayoutManager_vertical);
        recyclerview.setAdapter(adapter);

        serachView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterResult(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        serachView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.removefilter(arrayListApps_2);
                return false;
            }
        });
    }

    private void filterResult(String query) {
        List<AppListModel> temp = new ArrayList();

        for (AppListModel d : arrayListApps) {

            if (d.appname.contains(query)) {
                temp.add(d);
            }

        }
        adapter.setFilter(temp);
    }

    //get list of installed apps from Module
    private void getAppListFromModule() {
        MainClass mainObj = com.task.taskone_listofapps.MainClass.getInstance();
        listOfapps = mainObj.getInstalledApps(TaskOnActivity.this);

        for (int i = 0; i < listOfapps.size(); i++) {
            AppListModel Listobj = new AppListModel();

            PackageInfo obj = listOfapps.get(i);
            Listobj.setAppIcon(obj.applicationInfo.loadIcon(this.getPackageManager()));
            Listobj.setAppname(obj.applicationInfo.loadLabel(this.getPackageManager()).toString());
            Listobj.setPackageName(obj.packageName);
            Listobj.setVersionname(obj.versionName);

            arrayListApps.add(Listobj);
        }
        arrayListApps_2.addAll(arrayListApps);
    }
}
