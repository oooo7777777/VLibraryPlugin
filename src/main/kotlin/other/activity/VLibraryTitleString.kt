package other.activity

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */

fun VLibraryTitleString(
        layoutName: String,
        title: String,
        resourcePrefixXml:String
) = """<resources>
   <string name="${resourcePrefixXml}string_${layoutName}_title">${title}</string>
</resources>
"""