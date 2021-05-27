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
import other.utlis.getApplicationPackageFile
import other.utlis.getResourcePrefix
import other.viewmodel.VLibraryBean
import other.viewmodel.VLibraryViewModel
import java.text.SimpleDateFormat
import java.util.*


fun RecipeExecutor.VLibraryFragmentRecipe(
        moduleData: ModuleTemplateData,
        className: String,
        layoutName: String,
        packageName: String,
        isViewMode: Boolean,
        isResourcePrefix: Boolean,
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

    //是否约束资源文件命名
    var resourcePrefixClass = ""
    if (isResourcePrefix)
    {
        resourcePrefixClass = getResourcePrefix(applicationPackage).toUpperCase()
    }

    var resourcePrefixXml = ""
    if (isResourcePrefix)
    {
        resourcePrefixXml = getResourcePrefix(applicationPackage).toLowerCase() + "_"
    }

    //获取包名根目录
    val pkFile = getApplicationPackageFile(srcOut, applicationPackage)

    val activity = VLibraryFragmentKt(applicationPackage, className, layoutName, packageName, isViewMode, resourcePrefixClass, headerString)

    // 保存Fragment
    save(activity, srcOut.resolve("${className}Fragment.${ktOrJavaExt}"))

    // 保存xml
    save(VLibraryFragmentXml(applicationPackage, packageName, className, isViewMode, resourcePrefixXml, layoutName), resOut.resolve("layout/${resourcePrefixXml}${layoutName}.xml"))


    if (isViewMode)
    {
        // 保存viewModel
        save(VLibraryViewModel(applicationPackage, className, headerString), pkFile.resolve("model/${className}ViewModel.${ktOrJavaExt}"))
        // 保存bean
        save(VLibraryBean(applicationPackage, className, headerString), pkFile.resolve("bean/${className}Bean.${ktOrJavaExt}"))
        // 保存adapter
        save(VLibraryAdapter(applicationPackage, className, "Fragment", "${resourcePrefixXml}item_${layoutName}", resourcePrefixClass, resourcePrefixXml, headerString), pkFile.resolve("adapter/${className}FragmentAdapter.${ktOrJavaExt}"))
        // 保存adapterItemXml
        save(VLibraryAdapterItemXml(applicationPackage, packageName, className, "Fragment"), resOut.resolve("layout/${resourcePrefixXml}item_${layoutName}.xml"))

    }

    open(srcOut.resolve("${className}Fragment.${ktOrJavaExt}"))

}