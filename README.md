### 基于([VLibrary](https://github.com/oooo7777777/VLibrary))框架,编写的代码模板插件
### 开发效率提升80%

``` 
如遇此插件在其他版本不起作用或者展示不了需要自己手动把代码下载下来更新wizard-template.jar
更新方法为：使用AndroidStudio安装目录里的wizard-template.jar，
位于 /Applications/AndroidStudio.app/Contents/plugins/android/lib/目录下。
替换掉本项目跟目录里lib文件夹里面的wizard-template.jar，重新编译即可。

修改gradle.properties
pluginSinceBuild表示插件适配的最低版本，pluginUntilBuild表示插件适配的最高版本。
踩坑注意：pluginSinceBuild不是说想适配到哪个版本就可以适配的，跟对应Android Studio的JDK版本需保持一直，太低不支持。

这个pluginSinceBuild跟你的AS About Build后面对应版本，否则会出现plugin不能导入lib的问题

```
![pluginSinceBuild](https://images.gitee.com/uploads/images/2022/0617/110040_36adc398_699199.jpeg "781655431713.jpg")

#### 痛点解决
- 每次写代码都要做很多得重复动作
> 以一个MVVM架构为例,新建Activity-&gt;继承Base类-&gt;写ViewModel-&gt;写bean--&gt;修改xml--&gt;然后各种关系得绑定,引用等等..
- 这些无用且繁琐得操作,完全可以避免,此插件就是为了解决这些,让你快速高效得撸代码...
- 你也可以下载下来,依葫芦画瓢改成自己框架所需要得代码


#### 功能
- 1.可一键约束资源命名(组件化开发下使用)
> 以你包名得最后一段得第一个字母大写为约束,比如包名为com.v.home,资源约束就是h
> 开启以后res文件里面生成得资源都会以h_为开头,比如h_activity,h_string
- 2.可一键约束是否生成ViewModel代码
- 3.一键生成VLibraryActivity(bean,viewModel,activity,xml,注册AndroidManifest)
- 4.一键生成VLibraryFragment(bean,viewModel,activity,xml)
- 5.一键生成VLibraryDialog(dialog,xml)
- 6.一键生成VLibraryViewModel(bean,viewModel)
- 7.一键生成VLibraryAdapter(bean,xml,adapter)
- 8.一键生成VLibraryNet自定义网络架构


#### 1.安装方法
- [下载](https://github.com/oooo7777777/VLibraryPlugin/blob/master/VLibraryPlugin-3.0.6.jar) 
- Android Studio File-&gt; Settings -&gt; Plugins -&gt; install plugin from disk..导入下载的 VLibraryPlugin.jar
- 重启 Android Studio .

#### 2.安装方法
- 源代码下载下来,编译
- 运行 Run Plugin
- 打开\intellij-platform-plugin-template-main\build\libs\找到里面的VLibraryPlugin.jar
- Android Studio File-&gt; Settings -&gt; Plugins -&gt; install plugin from disk..导入生成的 VLibraryPlugin.jar
- 重启 Android Studio .



#### 使用
- 创建模板
![创建模板](https://images.gitee.com/uploads/images/2022/0617/110102_0686e614_699199.jpeg "791655432197.jpg")


- 输入框解释(鼠标移动到输入框上面有相对应的中文提示)
![鼠标移动到输入框上面有相对于得中文提示](https://images.gitee.com/uploads/images/2022/0617/110053_b5e85fb8_699199.jpeg "801655432238.jpg")

