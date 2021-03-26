package other.net

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */
fun VLibraryNetNetApi(
        applicationPackage:String,
        desc:String
)="""
package ${applicationPackage}.net

import retrofit2.http.GET
import retrofit2.http.Path


${desc}
interface NetworkApi {

   companion object {
        const val SERVER_URL = "https://gank.io/api/v2/"
    }

    @GET("banners")
    suspend fun getBanner(): ApiResponse<Any>

    @GET("data/category/Girl/type/Girl/page/{page}/count/20")
    suspend fun getGirlBean(@Path("page") page: Int): ApiResponse<Any>

}
"""