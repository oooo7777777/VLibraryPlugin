package other.activity


import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.*
import other.bean.VLibraryActivityOrientation
import other.bean.VLibraryCreateStyle
import other.utlis.defaultPackageNameParameter
import other.utlis.getHeaderString
import java.io.File

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
val VLibraryActivityTemplate
    get() = template {
        name = "VLibrary Activity"
        description = "适用于VLibrary框架的Activity"

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val packageName = defaultPackageNameParameter

        val className = stringParameter {
            name = "Activity Name"
            default = ""
            help = "只输入名字，不要包含Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${activityToLayout(className.value.toCamelCase())}" }
        }

        val createOption = enumParameter<VLibraryCreateStyle> {
            name = "Activity Create Style"
            default = VLibraryCreateStyle.Activity
            help = "Activity创建方式"
        }

        val isResourcePrefix = booleanParameter {
            name = "Is ResourcePrefix"
            default = true
            help = "是否约束资源文件命名(自动识别,组件化开发的时候勾选)"
        }

        val resourcePrefixName = stringParameter {
            name = "ResourcePrefix Name"
            default = ""
            help = "约束资源文件命名(手动输入的优先级高)"
        }

        val title = stringParameter {
            name = "Activity Title"
            default = ""
            help = "Activity标题(有值则显示标题栏否则隐藏)"
        }

        val author = stringParameter {
            name = "Author"
            help = "开发者"
            default = ""
            constraints = listOf(Constraint.NONEMPTY)
        }

        val classDesc = stringParameter {
            name = "Description"
            default = title.value
            help = "描述一下方法的作用"
            suggest = { title.value }
            constraints = listOf(Constraint.NONEMPTY)
        }

        val actOrientationOption = enumParameter<VLibraryActivityOrientation> {
            name = "Activity Orientation"
            default = VLibraryActivityOrientation.unspecified
            help = "Activity显示方向"
        }

        widgets(
            TextFieldWidget(className),
            EnumWidget(actOrientationOption),
            EnumWidget(createOption),
            CheckBoxWidget(isResourcePrefix),
            TextFieldWidget(resourcePrefixName),
            TextFieldWidget(title),
            TextFieldWidget(classDesc),
            TextFieldWidget(author),
            PackageNameWidget(packageName),
            LanguageWidget()
        )

        thumb { File("template_blank_activity.png") }


        recipe = { data: TemplateData ->
            VLibraryActivityRecipe(
                data as ModuleTemplateData,
                className.value,
                "${activityToLayout(className.value.toCamelCase())}",
                title.value,
                packageName.value,
                createOption.value,
                isResourcePrefix.value,
                getHeaderString(author.value, classDesc.value),
                resourcePrefixName.value,
                actOrientationOption.value
            )
        }
    }


