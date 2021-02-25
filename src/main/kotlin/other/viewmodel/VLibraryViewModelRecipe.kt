package other.viewmodel

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
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
            " * @Author : ${author}\n" +
            " * desc    : ${classDesc} \n" +
            " * time    : ${format.format(date)} \n" +
            " */"


    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

    // 保存viewModel
    save(VLibraryViewModel(packageName, className, headerString), srcOut.resolve("model/${className}ViewModel.${ktOrJavaExt}"))
    // 保存bean
    save(VLibraryBean(packageName, className, headerString), srcOut.resolve("bean/${className}Bean.${ktOrJavaExt}"))


    open(srcOut.resolve("model/${className}ViewModel.${ktOrJavaExt}"))

}