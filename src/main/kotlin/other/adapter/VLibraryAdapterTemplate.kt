package other.adapter

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:09
 */
import android.databinding.tool.ext.toCamelCase
import android.databinding.tool.ext.toCamelCaseAsVar
import com.android.tools.idea.res.getResourceName
import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.intellij.openapi.util.IconLoader
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toUpperCaseAsciiOnly
import other.activity.VLibraryActivityRecipe
import java.io.File
import com.android.tools.idea.wizard.template.AssetNameConverter.Type



val VLibraryAdapterTemplate
    get() = template {
        revision = 1
        name = "VLibrary Adapter"
        description = "适用于VLibrary框架的Adapter"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageName = defaultPackageNameParameter

        val className = stringParameter {
            name = "Adapter Name"
            default = "Main"
            help = "只输入名字，不要包含Adapter"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val author = stringParameter {
            name = "Author"
            default = " "
            help = "开发者"
            constraints = listOf(Constraint.NONEMPTY)

        }

        val classDesc = stringParameter {
            name = "Description"
            default = " "
            help = "描述一下方法的作用"
            constraints = listOf(Constraint.NONEMPTY)

        }


        widgets(
                TextFieldWidget(className),
                PackageNameWidget(packageName),
                TextFieldWidget(classDesc),
                TextFieldWidget(author)
        )

        thumb { File("template_login_activity.png") }

        recipe = { data: TemplateData ->
            VLibraryAdapterRecipe(
                    data as ModuleTemplateData,
                    className.value,
                    packageName.value,
                    author.value,
                    classDesc.value)
        }
    }

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.v.app"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }


