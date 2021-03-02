package other.utlis

import android.util.Log
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
 * 通过创建的文件 拿到包名的根目录用来传教bean model adapter类
 */
fun getApplicationPackageFile(srcOut: File, applicationPackage: String): File
{
    var applicationPackageFile = srcOut.path.toString()
    var pk = applicationPackage.split(".")
    var lastName = pk[pk.size - 1]

    val status: Boolean = applicationPackageFile.contains(lastName)
    return if (status)
    {
        var file = applicationPackageFile.substring(0, applicationPackageFile.indexOf(lastName)) + lastName+"\\"
        File(file)
    } else
    {
        srcOut
    }

}