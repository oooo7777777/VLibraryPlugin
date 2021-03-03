package other.dialog

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import other.utlis.getApplicationPackageFile
import other.utlis.getResourcePrefix
import java.text.SimpleDateFormat
import java.util.*


fun RecipeExecutor.VLibraryDialogRecipe(
        moduleData: ModuleTemplateData,
        className: String,
        isResourcePrefix :Boolean,
        layoutName: String,
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
        resourcePrefixXml = getResourcePrefix(applicationPackage).toLowerCase()+"_"
    }

    //获取包名根目录
    val  pkFile =  getApplicationPackageFile(srcOut,applicationPackage)

    val dialog = VLibraryDialogKt(applicationPackage, className, layoutName, packageName,resourcePrefixClass, headerString)


    save(dialog, pkFile.resolve("dialog/${className}Dialog.${ktOrJavaExt}"))


    save(VLibraryDialogXml(applicationPackage,packageName, className), resOut.resolve("layout/${resourcePrefixXml}${layoutName}.xml"))


    open(pkFile.resolve("dialog/${className}Dialog.${ktOrJavaExt}"))

}