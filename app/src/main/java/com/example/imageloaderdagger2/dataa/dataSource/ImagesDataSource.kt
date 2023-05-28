//package dev.ronnie.imageloaderdagger2.dataa.dataSource
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import dev.ronnie.imageloaderdagger2.data.model.ImagesResponse
//import dev.ronnie.imageloaderdagger2.dataa.api.ApiService
//import dev.ronnie.imageloaderdagger2.utill.STARTING_PAGE
//import java.io.IOException
//
//
//
//class ImagesDataSource(private val apiService: ApiService) :
//    PagingSource<Int, ImagesResponse>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImagesResponse> {
//        val page = params.key ?: STARTING_PAGE
//        return try {
//            val data = apiService.getImages()
//            LoadResult.Page(
//                data = data,
//                prevKey = if (page == STARTING_PAGE) null else page - 1,
//                nextKey = if (data.isEmpty()) null else page + 1
//            )
//        } catch (throwable: IOException) {
//            LoadResult.Error(IOException("Please check internet connection"))
//        } catch (throwable: Throwable) {
//            LoadResult.Error(throwable)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, ImagesResponse>): Int? {
//        return state.anchorPosition
//    }
//}
