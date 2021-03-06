package other.dialog

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:12
 */
fun VLibraryDialogXml(
        applicationPackage: String,
        className: String,
) = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="${applicationPackage}.dialog.${className}">

    <data>
          <import type="${applicationPackage}.R" />
          <variable
            name="v"
            type="${applicationPackage}.dialog.${className}" />
    </data>
    
      <RelativeLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <androidx.cardview.widget.CardView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="40dp"
            android:layout_marginRight="40dp"
            app:cardBackgroundColor="@android:color/white"
            app:cardCornerRadius="6dp">

            <RelativeLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content">

                <TextView
                    android:id="@+id/tvTitle"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:gravity="center"
                    android:paddingLeft="15dp"
                    android:paddingTop="15dp"
                    android:paddingRight="15dp"
                    android:singleLine="true"
                    android:text="提示"
                    android:textColor="#616161"
                    android:textSize="14.5sp" />

                <TextView
                    android:id="@+id/tvContent"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_below="@+id/tvTitle"
                    android:gravity="center"
                    android:lineSpacingExtra="3dp"
                    android:minHeight="90dp"
                    android:padding="15dp"
                    android:scrollbars="vertical"
                    android:text="温馨提示"
                    android:textColor="#000000"
                    android:textSize="14.5sp" />

                <View
                    android:id="@+id/tvLine"
                    style="@style/vb_line_horizontal"
                    android:layout_below="@+id/tvContent" />

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_below="@+id/tvLine"
                    android:orientation="horizontal">

                    <TextView
                        android:id="@+id/tvLeft"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:gravity="center"
                        android:padding="15dp"
                        android:text="取消"
                        android:textColor="#999999"
                        android:textSize="12.5sp"
                        app:vb_click="@{v}" />

                    <View style="@style/vb_line_vertical" />

                    <TextView
                        android:id="@+id/tvRight"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:gravity="center"
                        android:padding="15dp"
                        android:text="确定"
                        android:textColor="#FF5369"
                        android:textSize="12.5sp"
                        app:vb_click="@{v}" />
                </LinearLayout>
            </RelativeLayout>
        </androidx.cardview.widget.CardView>

    </RelativeLayout>
   
</layout>"""

