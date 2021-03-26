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
    val `data`: List<${className}Data>,
    val page: Int,
    val page_count: Int,
    val status: Int,
    val total_counts: Int
)

data class ${className}Data(
    val _id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>,
    val likeCounts: Int,
    val publishedAt: String,
    val stars: Int,
    val title: String,
    val type: String,
    val url: String,
    val views: Int
)
"""