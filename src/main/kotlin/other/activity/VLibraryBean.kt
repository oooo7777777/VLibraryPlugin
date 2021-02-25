package other.activity

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */

fun VLibraryBean(
        packageName:String,
        className:String,
        desc:String
)="""
package ${packageName}.bean

${desc}

data class ${className}Bean(
    val content: String
)
"""