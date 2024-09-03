package other.activity

import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import other.bean.VLibraryActivityOrientation
import other.bean.VLibraryCreateStyle
import other.utlis.*


/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */
fun RecipeExecutor.VLibraryActivityRecipe(
    moduleData: ModuleTemplateData,
    className: String,//类名
    layoutName: String,//layout 名称
    title: String,//标题 名称
    packageName: String,//当前右键选择新建的路径名称
    createOption: VLibraryCreateStyle,//Activity生成风格
    isResourcePrefix: Boolean,//是否约束资源命名
    headerString: String,//注释
    resourcePrefixName: String,//资源自己约束
    actOrientationOption: VLibraryActivityOrientation//Activity显示方向
) {

    val (projectData, srcOut, resOut, manifestOut) = moduleData

    val ktOrJavaExt = projectData.language.extension
    var applicationPackage = projectData.applicationPackage//包名

    if (applicationPackage.isNullOrEmpty()) {
        applicationPackage = escapeKotlinIdentifier(packageName)
    }


    //获取约束资源命名class
    val resourcePrefixClass =
        getResPrefixClass(applicationPackage, resourcePrefixName, isResourcePrefix)


    //获取约束资源命名xml
    val resourcePrefixXml =
        getResPrefixXml(applicationPackage, resourcePrefixName, isResourcePrefix)


    //获取包名根目录 用来生成 bean adapter ViewModel 的路径
    val pkFile = getApplicationPackageFile(srcOut, applicationPackage)


    //当前生成类的类型
    val typeName = "Activity"

    // 得到最终要使用的className 比如 MMain
    val lastClassName = getFormatName(resourcePrefixClass + className)

    //拼接当前的className 比如 MMainFragment
    val lastClassNameFormat = getFormatName(lastClassName, typeName)

    // 得到最终要使用的LayoutName  比如:m_fragment_main
    val lastLayoutName = resourcePrefixXml + layoutName

    // 得到最终要使用的ItemName 比如:m_fragment_main_item
    val lastItemName = lastLayoutName + "_item"

    // 得到最终要使用的titleName 比如:m_string_main_title
    val lastTitleName = resourcePrefixXml + "string_" + className.toLowerCase() + "_title"


    // 保存Fragment
    save(
        //lastLayoutName.toCamelCase() 这里把m_fragment_main 转换成 MFragmentMain
        getStrClass(
            createOption,
            applicationPackage,
            packageName,
            lastClassName,
            typeName,
            lastLayoutName.toCamelCase(),
            getStrTitle(lastTitleName, title),
            headerString
        ),
        srcOut.resolve("$lastClassNameFormat.$ktOrJavaExt")
    )

    // 保存layout xml
    save(
        getStrXml(
            createOption,
            applicationPackage,
            packageName,
            lastClassNameFormat,
            lastItemName
        ),
        resOut.resolve("layout/${lastLayoutName}.xml")
    )

    // 保存title String
    if (title.isNotEmpty()) {
        mergeXml(getStrString(lastTitleName, title), resOut.resolve("values/strings.xml"))
    }
    

    when (createOption) {
        VLibraryCreateStyle.Activity -> {
        }

        VLibraryCreateStyle.Activity_ViewModule -> {
            val viewModelName = getFormatName(lastClassName, "ViewModel") //MainActivityViewModel
            save(
                getStrViewModule(applicationPackage, lastClassName, headerString),
                pkFile.resolve("viewmodel/$viewModelName.$ktOrJavaExt")
            )

            // 保存bean
            val beanName = getFormatName(lastClassName, "Bean") //MainActivityBean
            save(
                getStrBean(applicationPackage, lastClassName, headerString),
                pkFile.resolve("bean/$beanName.$ktOrJavaExt")
            )
        }

        VLibraryCreateStyle.Activity_ViewModule_RecyclerView -> {

            val viewModelName = getFormatName(lastClassName, "ViewModel") //MainActivityViewModel
            save(
                getStrViewModule(applicationPackage, lastClassName, headerString),
                pkFile.resolve("viewmodel/$viewModelName.$ktOrJavaExt")
            )

            // 保存bean
            val beanName = getFormatName(lastClassName, "Bean") //MainActivityBean
            save(
                getStrBean(applicationPackage, lastClassName, headerString),
                pkFile.resolve("bean/$beanName.$ktOrJavaExt")
            )

            // 保存adapter
            val adapterName = getFormatName(lastClassName, "Adapter") //MainActivityAdapter
            save(
                getStrAdapter(applicationPackage, lastClassName, lastItemName, headerString),
                pkFile.resolve("adapter/$adapterName.$ktOrJavaExt")
            )

            // 保存adapterItemXml
            save(
                getStrXmlItem(applicationPackage, lastClassName),
                resOut.resolve("layout/$lastItemName.xml")
            )
        }
    }


    val activityClass = "$packageName.$lastClassNameFormat"
    //添加activity到Manifest
    mergeXml(
        getStrAndroidManifestXml(activityClass, actOrientationOption),
        manifestOut.resolve("AndroidManifest.xml")
    )

    open(srcOut.resolve("${lastClassNameFormat}.${ktOrJavaExt}"))

}