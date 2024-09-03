package other.bean

/**
 * author  : ww
 * desc    :
 * time    : 2023/4/21 16:00
 */
enum class VLibraryActivityOrientation {
    unspecified,//不指定屏幕方向，系统会根据设备当前的方向来决定Activity的方向。
    portrait,//：指定屏幕方向为竖屏。如果设备旋转到横屏，系统可能会重新创建Activity。
    landscape,//指定屏幕方向为横屏。如果设备旋转到竖屏，系统可能会重新创建Activity。
    sensorPortrait,//根据设备的传感器来决定屏幕方向，但只允许竖屏方向。
    sensorLandscape//根据设备的传感器来决定屏幕方向，但只允许横屏方向。
}


enum class VLibraryCreateStyle {
    Activity,//Activity
    Activity_ViewModule,//Activity+ViewModule
    Activity_ViewModule_RecyclerView,//Activity+ViewModule+RecyclerView
}

enum class VLibraryFragmentCreateStyle {
    Fragment,//Activity
    Fragment_ViewModule,//Activity+ViewModule
    Fragment_ViewModule_RecyclerView,//Activity+ViewModule+RecyclerView
}

