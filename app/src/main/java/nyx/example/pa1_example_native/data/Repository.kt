package nyx.example.pa1_example_native.data

import nyx.example.pa1_example_native.model.ArticleDetail
import nyx.example.pa1_example_native.model.ArticlePreview
import nyx.example.pa1_example_native.network.client
import io.ktor.client.request.*

object Repository {

    suspend fun fetchArticlePreviews(): Array<ArticlePreview> = client.get("previews")

    suspend fun fetchArticleDetail(id:Int): ArticleDetail = client.get("detail/${id}")
}