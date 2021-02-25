package other.activity

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:12
 */
fun VLibraryActivityXml(
        applicationPackage: String,
        packageName: String,
        activityClass: String,
        isModeView: Boolean
) = if (isModeView)
        {
            """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="${packageName}.${activityClass}Activity">
    
    <data>
          <import type="${applicationPackage}.R" />
          <variable
            name="v"
            type="${packageName}.${activityClass}Activity" />
    </data>
    
      <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.scwang.smart.refresh.layout.SmartRefreshLayout
            android:id="@+id/refreshLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/recyclerView"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:overScrollMode="never"
                android:scrollbars="none" />

        </com.scwang.smart.refresh.layout.SmartRefreshLayout>

    </LinearLayout>
   
</layout>"""
        } else
        {
            """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="${packageName}.${activityClass}Activity">

    <data>
          <import type="${applicationPackage}.R" />
          <variable
            name="v"
            type="${packageName}.${activityClass}Activity" />
    </data>
    
     <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
     </LinearLayout>
   
</layout>"""
        }
