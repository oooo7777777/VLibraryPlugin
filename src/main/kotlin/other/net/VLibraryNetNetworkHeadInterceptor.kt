package other.net

/**
 * @Author : ww
 * desc    :
 * time    : 2021/2/19 11:11
 */
fun VLibraryNetNetworkHeadInterceptor(
        applicationPackage:String,
        desc:String
)="""
package ${applicationPackage}.net

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

${desc}

class NetworkHeadInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("token", "token").build()
        builder.addHeader("device", "Android").build()
        return chain.proceed(builder.build())
    }

}
"""