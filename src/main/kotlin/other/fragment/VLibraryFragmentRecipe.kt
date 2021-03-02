package other.fragment

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import other.activity.VLibraryFragmentXml
import other.adapter.VLibraryAdapter
import other.adapter.VLibraryAdapterItemXml
import other.viewmodel.VLibraryBean
import other.viewmodel.VLibraryViewModel
import java.text.SimpleDateFormat
import java.util.*


fun RecipeExecutor.VLibraryFragmentRecipe(
        moduleData: ModuleTemplateData,
        className: String,
        layoutName: String,
        packageName: String,
        isModeView: Boolean,
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
    var applicationPackage = projectData.applicationPackage

    if (applicationPackage.isNullOrEmpty())
    {
        applicationPackage = escapeKotlinIdentifier(packageName)
    }

    val activity = VLibraryFragmentKt(applicationPackage, className, layoutName, packageName, isModeView, headerString)

    // 保存Fragment
    save(activity, srcOut.resolve("${className}Fragment.${ktOrJavaExt}"))

    // 保存xml
    save(VLibraryFragmentXml(applicationPackage, packageName, className, isModeView, layoutName), resOut.resolve("layout/${layoutName}.xml"))


    if (isModeView)
    {
        // 保存viewModel
        save(VLibraryViewModel(packageName, className, headerString), srcOut.resolve("model/${className}ViewModel.${ktOrJavaExt}"))
        // 保存bean
        save(VLibraryBean(packageName, className, headerString), srcOut.resolve("bean/${className}Bean.${ktOrJavaExt}"))
        // 保存adapter
        save(VLibraryAdapter(applicationPackage, packageName, className, "Fragment", "item_${layoutName}", headerString), srcOut.resolve("adapter/${className}FragmentAdapter.${ktOrJavaExt}"))
        // 保存adapterItemXml
        save(VLibraryAdapterItemXml(applicationPackage, packageName, className,"Fragment"), resOut.resolve("layout/item_${layoutName}.xml"))

    }

    open(srcOut.resolve("${className}Fragment.${ktOrJavaExt}"))

}