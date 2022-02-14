package other.utlis

import android.databinding.tool.ext.toCamelCase
import android.databinding.tool.ext.toCamelCaseAsVar
import com.android.tools.idea.wizard.template.AssetNameConverter
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toUpperCaseAsciiOnly
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author : ww
 * desc    :
 * time    : 2021/3/2 15:50
 */

/**
 * srcOut 文件地址
 * applicationPackage 包名
 *
 * 通过创建的文件 拿到包名的根目录用来创建bean model adapter类
 */
fun getApplicationPackageFile(srcOut: File, applicationPackage: String): File {
    var applicationPackageFile = srcOut.path.toString()
    var pk = applicationPackage.replace(".", "\\")

    val status: Boolean = applicationPackageFile.contains(pk)
    return if (status) {
        var file =
            applicationPackageFile.substring(0, applicationPackageFile.indexOf(pk)) + pk + "\\"
        File(file)
    } else {
        srcOut
    }

}


/**
 * applicationPackage 包名
 * isResourcePrefix 是否约束
 *
 * 获取包名的最后一级的第一位字母为约束字段
 */
fun getResourcePrefix(applicationPackage: String): String {
    var pk = applicationPackage.split(".")
    var lastName = pk[pk.size - 1]
    return lastName.substring(0, 1)
}

/**
 * 生成名称
 */
fun getFormatName(className: String, type: String = ""): String {
    return "${className}${type}"
}

/**
 * 获取注释
 */
fun getHeaderString(author: String, classDesc: String): String {
    var date = Date(System.currentTimeMillis())
    var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    return "" +
            "/**\n" +
            " * author  : ${author}\n" +
            " * desc    : ${classDesc} \n" +
            " * time    : ${format.format(date)} \n" +
            " */"
}

/**
 * 获取资源约束的命名 class使用
 * 是否约束资源文件命名
 * 如果约束了就获取当前applicationPackage的最后一段的第一个字母为约束并且转换成大写字母
 */
fun getResPrefixClass(aPk: String, resourcePrefixName: String, isResPrefix: Boolean): String {
    //判断约束资源名称用户是否输入
//    return if (!resourcePrefixName.isNullOrEmpty()) {
//        resourcePrefixName.toUpperCase()
//    } else {
//        //判断是否选中了约束按钮
//        if (isResPrefix) {
//            getResourcePrefix(aPk).toUpperCase()
//        } else {
//            ""
//        }
//    }
    return ""
}

/**
 * 获取资源约束的命名 xml使用
 * 是否约束资源文件命名
 * 如果约束了就获取当前applicationPackage的最后一段的第一个字母为约束并且转换成大写字母
 */
fun getResPrefixXml(aPk: String, resourcePrefixName: String, isResPrefix: Boolean): String {
    //判断约束资源名称用户是否输入
    return if (!resourcePrefixName.isNullOrEmpty()) {
        resourcePrefixName.toLowerCase() + "_"
    } else {
        //判断是否选中了约束按钮
        if (isResPrefix) {
            getResourcePrefix(aPk).toLowerCase() + "_"
        } else {
            ""
        }
    }
}


/**
 * 获取生成的xml
 *
 * 是否生成ViewMode
 * 包名
 * 当前生成对应类
 * 当前生成对应类的item
 */
fun getStrXml(
    isViewMode: Boolean,
    applicationPackage: String,
    packageName: String,
    className: String,
    classNameItem: String
): String {
    if (isViewMode) {
        return """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="${"$packageName.$className"}">
    
    <data>
    
       <import type="${applicationPackage}.R" />
       
       <variable
           name="v"
           type="${"$packageName.$className"}" />
           
    </data>
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <com.scwang.smart.refresh.layout.SmartRefreshLayout
            android:id="@+id/refreshLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/recyclerView"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:overScrollMode="never"
                android:scrollbars="none" 
                tools:listitem="@layout/$classNameItem" />

        </com.scwang.smart.refresh.layout.SmartRefreshLayout>

    </LinearLayout>
   
</layout>
"""
    } else {
        return """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="${"$packageName.$className"}">

    <data>
    
        <import type="${applicationPackage}.R" />
        
        <variable
            name="v"
            type="${"$packageName.$className"}" />
            
    </data>
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        
    </LinearLayout>
   
</layout>
"""
    }
}


/**
 * 获取ViewModule生成代码
 */
fun getStrViewModule(apk: String, className: String, headerString: String): String {
    return """
package ${apk}.viewmodel

import androidx.lifecycle.MutableLiveData
import com.v.base.VBApplication.Companion.apiBase
import com.v.base.VBViewModel
import ${apk}.bean.${className}Bean

${headerString}
class ${className}ViewModel : VBViewModel() {

    var bean = MutableLiveData<${className}Bean>()

    fun getData(page: Int) {
        val map = mapOf(
            "pageNum" to page.toString()
        )
        vbRequest({
            apiBase.get("https://gank.io/api/v2/data/category/Girl/type/Girl/page/"+page+"/count/20")
        },
            bean
        )
    }
}    
    """
}


/**
 * 获取Adapter生成代码
 */
fun getStrAdapter(apk: String, className: String, itemName: String, headerString: String): String {
    return """
package ${apk}.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import ${apk}.R
import ${apk}.bean.${className}Data
import ${apk}.databinding.${itemName.toCamelCase()}Binding
    
${headerString}
class ${className}Adapter :
    BaseQuickAdapter<${className}Data, BaseDataBindingHolder<${itemName.toCamelCase()}Binding>>(R.layout.${itemName}) {

    override fun convert(holder: BaseDataBindingHolder<${itemName.toCamelCase()}Binding>, item: ${className}Data) {
        holder.dataBinding?.run {
           bean = item
           executePendingBindings()
        }
    }
}
"""
}


/**
 * 获取bean生成代码
 */
fun getStrBean(apk: String, className: String, headerString: String): String {
    return """
package ${apk}.bean
    
${headerString}
data class ${className}Bean(
     val `data`: List<${className}Data>,
     val page: Int,
     val page_count: Int,
     val status: Int,
     val total_counts: Int
)
    
data class ${className}Data(
     val _id: String,
     val author: String,
     val category: String,
     val createdAt: String,
     val desc: String,
     val images: List<String>,
     val likeCounts: Int,
     val publishedAt: String,
     val stars: Int,
     val title: String,
     val type: String,
     val url: String,
     val views: Int
)
    """
}


/**
 * 获取adapter item 生成代码
 */
fun getStrXmlItem(apk: String, className: String): String {
    return """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="${apk}.adapter.${className}Adapter">
    
    <data>
          <import type="${apk}.R" />
          <variable
            name="bean"
            type="${apk}.bean.${className}Data" />
    </data>
    
      <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <ImageView
            android:id="@+id/ivIcon"
            android:layout_width="match_parent"
            android:scaleType="centerCrop"
            app:vb_img_url="@{bean.url}"
            android:layout_height="match_parent" />

    </LinearLayout>
   
</layout>
        """
}


/**
 * 获取class 生成代码
 */
fun getStrClass(
    isViewMode: Boolean,
    applicationPackage: String,
    packageName: String,
    className: String,
    typeName: String,
    xmlName: String,
    toolBar: String,
    headerString: String
) = if (isViewMode) {
    """
package ${packageName}


import ${applicationPackage}.R
import android.view.View
import androidx.lifecycle.Observer
import android.graphics.Color
import com.v.base.VB${typeName}
import com.v.base.utils.ext.vbDivider
import com.v.base.utils.ext.vbLinear
import com.v.base.utils.ext.vbConfig
import com.v.base.utils.ext.vbLoad
import ${applicationPackage}.adapter.${className}Adapter
import ${applicationPackage}.viewmodel.${className}ViewModel
import ${applicationPackage}.databinding.${xmlName}Binding

${headerString}
class ${className}${typeName} : VB${typeName}<${xmlName}Binding, ${className}ViewModel>(), View.OnClickListener {
   
  private var page =1
  
  private val mAdapter by lazy {
        mDataBinding.recyclerView.vbDivider{
                setDivider(5)
            }
            .vbLinear(${className}Adapter())
            .apply {
                vbConfig(mDataBinding.refreshLayout,
                    onRefresh = {
                        page = 1
                        mViewModel.getData(page)
                    },
                    onLoadMore={
                        mViewModel.getData(page)
                    },
                    onItemClick = { view, position ->
                        
                    })
            } as ${className}Adapter
    }

    ${toolBar}
    
    override fun initData() {
        mDataBinding.v = this
        mAdapter
        mDataBinding.refreshLayout.autoRefresh()
    }

    override fun createObserver() {
        mViewModel.bean.observe(this, Observer {
            it.data?.run {
                 mAdapter.vbLoad(this, page, mDataBinding.refreshLayout,
                    onSuccess = {
                         page = it
                    })

            }

        })
    }

    override fun onClick(v: View) {
        when (v.id) {
           
        }
    }
    
} 
"""
} else {
    """
package ${packageName}


import ${applicationPackage}.R
import com.v.base.VB${typeName}
import android.view.View
import com.v.base.VBBlankViewModel
import ${applicationPackage}.databinding.${xmlName}Binding

${headerString}
class ${className}${typeName} : VB${typeName}<${xmlName}Binding, VBBlankViewModel>(), View.OnClickListener {
   
     ${toolBar}
 
    override fun initData() {
        mDataBinding.v = this
    }

    override fun createObserver() {

    }

    override fun onClick(v: View) {
        when (v.id) {
           
        }
    }
    
} 
"""
}


/**
 * 获取string 生成代码
 */
fun getStrString(titleName: String, title: String): String {
    return """
<resources>
   <string name="${titleName}">${title}</string>
</resources>
"""
}

/**
 * 获取Activity 标题栏生成代码
 */
fun getStrTitle(titleName: String, title: String): String {
    return if (title.isNullOrEmpty()) {
        ""
    } else {
        """
    override fun toolBarTitle(
        title: String,
        titleColor: Int,
        isShowBottomLine: Boolean,
        listener: View.OnClickListener?
    ) {
        super.toolBarTitle(
            this.getString(R.string.${titleName}),
            titleColor,
            isShowBottomLine,
            listener
        )
    }
    """
    }
}


/**
 * 获取Activity注册 生成代码
 */
fun getStrAndroidManifestXml(
    activityClass: String
): String {
    return """
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application>
        <activity android:name="${activityClass}"/>
    </application>
</manifest>
"""
}


/**
 * 获取dialog 生成代码
 */
fun getStrDialog(
    applicationPackage: String,
    className: String,
    lastLayoutName: String,
    headerString: String
): String {
    return """
package ${applicationPackage}.dialog

import android.content.Context
import android.view.View
import com.v.base.dialog.VBDialog
import ${applicationPackage}.databinding.${lastLayoutName}Binding

${headerString}
class ${className}(mContext: Context) : VBDialog<${lastLayoutName}Binding>(mContext), View.OnClickListener {
   

    private var listener: ((dialog: ${className}, position: Int) -> Unit)? = null

    fun setClickListener(listener: ((dialog: ${className}, position: Int) -> Unit)): ${className} {
        this.listener = listener
        return this
    }
    
    
    override fun initData() {
        mDataBinding.v = this
        
//        ${className}(mContext)
//            .setClickListener { dialog, position ->
//
//            }.show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            mDataBinding.tvLeft.id -> {
                dismiss()
                listener?.invoke(this, 0)
            }
            mDataBinding.tvRight.id -> {
                dismiss()
                listener?.invoke(this, 1)
            }
        }
    }


}
"""
}