package other

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */

fun VLibraryBean(
        packageName:String,
        activityClass:String,
        desc:String
)="""
package ${packageName}.bean

${desc}

data class ${activityClass}Bean(
    val content: String
)
"""