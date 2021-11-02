package other.adapter


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import other.utlis.*

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */
fun RecipeExecutor.VLibraryAdapterRecipe(
        moduleData: ModuleTemplateData,
        className: String,//类名
        layoutName: String,//layout 名称
        packageName: String,//当前右键选择新建的路径名称
        isResourcePrefix: Boolean,//是否约束资源命名
        headerString: String,//注释
        resourcePrefixName: String//资源自己约束
) {

    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
    var applicationPackage = projectData.applicationPackage//包名

    if (applicationPackage.isNullOrEmpty()) {
        applicationPackage = escapeKotlinIdentifier(packageName)
    }

    //获取约束资源命名class
    var resourcePrefixClass = getResPrefixClass(applicationPackage,resourcePrefixName, isResourcePrefix)

    //获取约束资源命名xml
    var resourcePrefixXml = getResPrefixXml(applicationPackage,resourcePrefixName, isResourcePrefix)

    //获取包名根目录 用来生成 bean adapter ViewModel 的路径
    val pkFile = getApplicationPackageFile(srcOut, applicationPackage)


    //当前生成类的类型
    val typeName = "Adapter"

    // 得到最终要使用的className 比如 MMain
    val lastClassName = getFormatName(resourcePrefixClass + className)

    //拼接当前的className 比如 MMainFragment
    val lastClassNameFormat = getFormatName(lastClassName, typeName)

    // 得到最终要使用的LayoutName  比如:m_fragment_main
    val lastLayoutName = resourcePrefixXml + layoutName

    // 得到最终要使用的ItemName 比如:m_fragment_main_item
    val lastItemName = lastLayoutName + "_item"


    // 保存adapter
    save(
            getStrAdapter(applicationPackage, lastClassName, lastItemName, headerString),
            pkFile.resolve("adapter/$lastClassNameFormat.$ktOrJavaExt")
    )

    // 保存adapterItemXml
    save(
            getStrXmlItem(applicationPackage, lastClassName),
            resOut.resolve("layout/$lastItemName.xml")
    )

    // 保存bean
    val beanName = getFormatName(lastClassName, "Bean") //MainActivityBean
    save(
            getStrBean(applicationPackage, lastClassName, headerString),
            pkFile.resolve("bean/$beanName.$ktOrJavaExt")
    )


    open(pkFile.resolve("adapter/${lastClassNameFormat}.${ktOrJavaExt}"))

}


