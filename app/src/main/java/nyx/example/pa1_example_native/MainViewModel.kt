package nyx.example.pa1_example_native

import android.util.Log
import androidx.lifecycle.ViewModel
import nyx.example.pa1_example_native.data.Repository

class MainViewModel:ViewModel() {

    private val TAG = "Main-VM"

    suspend fun getPreviews() = try{
        Repository.fetchArticlePreviews()
    }catch (e:Exception){
        Log.e(TAG, e.message!!)
        e
    }

    suspend fun getArticle(id:Int)  = try{
        Repository.fetchArticleDetail(id)
    }catch (e:Exception){
        Log.e(TAG, e.message!!)
        e
    }
}