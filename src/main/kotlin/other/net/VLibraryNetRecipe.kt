package other.net

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


fun RecipeExecutor.VLibraryNetRecipe(
        moduleData: ModuleTemplateData,
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

    // 保存ApiResponse
    save(VLibraryNetApiResponse(applicationPackage, headerString), pkFile.resolve("net/ApiResponse.${ktOrJavaExt}"))
    // 保存Network
    save(VLibraryNetNetwork(applicationPackage, headerString), pkFile.resolve("net/Network.${ktOrJavaExt}"))
    // 保存NetApi
    save(VLibraryNetNetApi(applicationPackage, headerString), pkFile.resolve("net/NetApi.${ktOrJavaExt}"))
    // 保存NetworkHeadInterceptor
    save(VLibraryNetNetworkHeadInterceptor(applicationPackage, headerString), pkFile.resolve("net/NetworkHeadInterceptor.${ktOrJavaExt}"))


    open(pkFile.resolve("net/Network.${ktOrJavaExt}"))

}