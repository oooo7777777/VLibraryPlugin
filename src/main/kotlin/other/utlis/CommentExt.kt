package other.utlis

import java.io.File

/**
 * @Author : ww
 * desc    :
 * time    : 2021/3/2 15:50
 */

/**
 * srcOut 文件地址
 * applicationPackage 包名
 *
 * 通过创建的文件 拿到包名的根目录用来创建bean model adapter类
 */
fun getApplicationPackageFile(srcOut: File, applicationPackage: String): File
{
    var applicationPackageFile = srcOut.path.toString()
    var pk = applicationPackage.replace(".", "\\")

    val status: Boolean = applicationPackageFile.contains(pk)
    return if (status)
    {
        var file = applicationPackageFile.substring(0, applicationPackageFile.indexOf(pk)) + pk + "\\"
        File(file)
    } else
    {
        srcOut
    }

}


/**
 * applicationPackage 包名
 * isResourcePrefix 是否约束
 *
 * 获取包名的最后一级的第一位字母为约束字段
 */
fun getResourcePrefix(applicationPackage: String): String
{
    var pk = applicationPackage.split(".")
    var lastName = pk[pk.size - 1]
    return lastName.substring(0, 1)
}

