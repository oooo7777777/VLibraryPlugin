package other.viewmodel

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import other.utlis.getApplicationPackageFile
import java.text.SimpleDateFormat
import java.util.*


fun RecipeExecutor.VLibraryViewModelRecipe(
        moduleData: ModuleTemplateData,
        className: String,
        packageName: String,
        author: String,
        classDesc: String
)
{

    var date = Date(System.currentTimeMillis())
    var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    val headerString = "" +
            "/**\n" +
            " * author  : ${author}\n" +
            " * desc    : ${classDesc} \n" +
            " * time    : ${format.format(date)} \n" +
            " */"


    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
    var applicationPackage = projectData.applicationPackage

    if (applicationPackage.isNullOrEmpty())
    {
        applicationPackage = escapeKotlinIdentifier(packageName)
    }
    //获取包名根目录
    val  pkFile =  getApplicationPackageFile(srcOut,applicationPackage)

    // 保存viewModel
    save(VLibraryViewModel(applicationPackage, className, headerString), pkFile.resolve("model/${className}ViewModel.${ktOrJavaExt}"))
    // 保存bean
    save(VLibraryBean(applicationPackage, className, headerString), pkFile.resolve("bean/${className}Bean.${ktOrJavaExt}"))


    open(pkFile.resolve("model/${className}ViewModel.${ktOrJavaExt}"))

}