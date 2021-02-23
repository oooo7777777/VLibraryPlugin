package other

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */

fun VLibraryAdapterItemXml(
        packageName: String,
        activityClass: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="${packageName}.adapter.${activityClass}Adapter">
    
    <data>
          <variable
            name="bean"
            type="${packageName}.bean.${activityClass}Bean" />
    </data>
    
      <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:text="@{bean.content}"
            android:layout_width="match_parent"
            android:padding="10dp"
            android:gravity="center"
            android:background="@color/base_yellow"
            android:layout_height="wrap_content"/>

    </LinearLayout>
   
</layout>"""