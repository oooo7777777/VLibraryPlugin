package other.adapter

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:10
 */

import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.activityToLayout
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifestStrings
import other.dialog.VLibraryDialogKt
import other.dialog.VLibraryDialogXml
import other.viewmodel.VLibraryBean
import java.text.SimpleDateFormat
import java.util.*


fun RecipeExecutor.VLibraryAdapterRecipe(
        moduleData: ModuleTemplateData,
        className: String,
        packageName: String,
        author: String,
        classDesc: String
)
{

    var date = Date(System.currentTimeMillis())
    var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val applicationPackage = escapeKotlinIdentifier(packageName)

    val headerString = "" +
            "/**\n" +
            " * @Author : ${author}\n" +
            " * desc    : ${classDesc} \n" +
            " * time    : ${format.format(date)} \n" +
            " */"


    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension

    val layoutName ="${classToLayout(className.toCamelCase())}"

    // 保存adapter
    save(VLibraryAdapter(applicationPackage, packageName, className,"", layoutName, headerString), srcOut.resolve("adapter/${className}Adapter.${ktOrJavaExt}"))
    // 保存adapterItemXml
    save(VLibraryAdapterItemXml(applicationPackage, packageName, className,""), resOut.resolve("layout/${layoutName}_item.xml"))
    // 保存bean
    save(VLibraryBean(packageName, className, headerString), srcOut.resolve("bean/${className}Bean.${ktOrJavaExt}"))
    
    open(srcOut.resolve("dialog/${className}Dialog.${ktOrJavaExt}"))

}

fun classToLayout(className: String, layoutName: String? = null): String =
        if (className.isNotEmpty())
            AssetNameConverter(AssetNameConverter.Type.FRAGMENT, className)
                    .overrideLayoutPrefix(layoutName ?: "")
                    .getValue(AssetNameConverter.Type.LAYOUT)
        else
            ""

