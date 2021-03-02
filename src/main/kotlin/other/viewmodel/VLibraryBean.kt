package other.viewmodel

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */

fun VLibraryBean(
        applicationPackage:String,
        className:String,
        desc:String
)="""
package ${applicationPackage}.bean

${desc}

data class ${className}Bean(
    val content: String
)
"""