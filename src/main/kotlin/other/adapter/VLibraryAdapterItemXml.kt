package other.adapter

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */

fun VLibraryAdapterItemXml(
        applicationPackage: String,
        packageName: String,
        className: String,
        classType:String
) = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="${applicationPackage}.adapter.${className}${classType}Adapter">
    
    <data>
          <import type="${applicationPackage}.R" />
          <variable
            name="bean"
            type="${applicationPackage}.bean.${className}Data" />
    </data>
    
      <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <ImageView
            android:id="@+id/ivIcon"
            android:layout_width="match_parent"
            android:scaleType="centerCrop"
            app:imgUrl="@{bean.url}"
            android:layout_height="match_parent" />

    </LinearLayout>
   
</layout>"""