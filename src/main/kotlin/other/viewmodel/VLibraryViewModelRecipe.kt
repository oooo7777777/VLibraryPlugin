package other.viewmodel


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import other.utlis.*

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */
fun RecipeExecutor.VLibraryViewModelRecipe(
        moduleData: ModuleTemplateData,
        className: String,//类名
        packageName: String,//当前右键选择新建的路径名称
        isResourcePrefix: Boolean,//是否约束资源命名
        headerString: String//注释
) {


    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
    var applicationPackage = projectData.applicationPackage//包名

    if (applicationPackage.isNullOrEmpty()) {
        applicationPackage = escapeKotlinIdentifier(packageName)
    }

    //获取约束资源命名class
    var resourcePrefixClass = getResPrefixClass(applicationPackage, isResourcePrefix)

    //获取约束资源命名xml
    var resourcePrefixXml = getResPrefixXml(applicationPackage, isResourcePrefix)

    //获取包名根目录 用来生成 bean adapter ViewModel 的路径
    val pkFile = getApplicationPackageFile(srcOut, applicationPackage)


    //当前生成类的类型
    val typeName = "Bean"

    // 得到最终要使用的className 比如 MMain
    val lastClassName = getFormatName(resourcePrefixClass + className)

    //拼接当前的className 比如 MMainFragment
    val lastClassNameFormat = getFormatName(lastClassName, typeName)


    // 保存viewModel
    val viewModelName = getFormatName(lastClassName, "ViewModel") //MainActivityViewModel
    save(
            getStrViewModule(applicationPackage, lastClassName, headerString),
            pkFile.resolve("viewmodel/$viewModelName.$ktOrJavaExt")
    )

    // 保存bean
    save(
            getStrBean(applicationPackage, lastClassName, headerString),
            pkFile.resolve("bean/$lastClassNameFormat.$ktOrJavaExt")
    )

    open(srcOut.resolve("viewmodel/$viewModelName.$ktOrJavaExt"))


}