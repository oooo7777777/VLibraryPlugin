package other.adapter

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.AssetNameConverter
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import other.utlis.getApplicationPackageFile
import other.utlis.getResourcePrefix
import other.viewmodel.VLibraryBean
import java.text.SimpleDateFormat
import java.util.*


fun RecipeExecutor.VLibraryAdapterRecipe(
        moduleData: ModuleTemplateData,
        className: String,
        isResourcePrefix: Boolean,
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
    val layoutName ="${classToLayout(className.toCamelCase())}"


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

    // 保存adapter
    save(VLibraryAdapter(applicationPackage, className,"", resourcePrefixXml+layoutName,resourcePrefixClass, resourcePrefixXml,headerString), pkFile.resolve("adapter/${className}Adapter.${ktOrJavaExt}"))
    // 保存adapterItemXml
    save(VLibraryAdapterItemXml(applicationPackage, packageName, className,""), resOut.resolve("layout/${resourcePrefixXml}${layoutName}.xml"))
    // 保存bean
    save(VLibraryBean(applicationPackage, className, headerString), pkFile.resolve("bean/${className}Bean.${ktOrJavaExt}"))
    
    open(pkFile.resolve("adapter/${className}Adapter.${ktOrJavaExt}"))

}

fun classToLayout(className: String, layoutName: String? = null): String =
        if (className.isNotEmpty())
            AssetNameConverter(AssetNameConverter.Type.FRAGMENT, className)
                    .overrideLayoutPrefix(layoutName ?: "item")
                    .getValue(AssetNameConverter.Type.LAYOUT)
        else
            ""

