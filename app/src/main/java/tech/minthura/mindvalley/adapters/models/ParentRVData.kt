package tech.minthura.mindvalley.adapters.models


enum class ParentRVDataType {
    EPISODES,
    COURSE,
    SERIES,
    CATEGORIES,
}
class ParentRVListItem(val title: String?, val channelTitle: String?, val imageUrl: String?)

class ParentRVData(
        val type : ParentRVDataType,
        val title: String?,
        val mediaCount: String?,
        val iconAsset: String?,
        val data : List<ParentRVListItem>,
    ) {
}