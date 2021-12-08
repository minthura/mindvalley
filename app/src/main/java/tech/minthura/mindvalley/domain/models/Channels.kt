package tech.minthura.mindvalley.domain.models

data class Channels(
    val data: Data?
){
    data class Data(
        val channels: List<Channel>?
    )
    data class Channel(
        val coverAsset: CoverAsset?,
        val iconAsset: IconAsset?,
        val id: String?,
        val latestMedia: List<LatestMedia>?,
        val mediaCount: Int?,
        val series: List<Serie>?,
        val slug: String?,
        val title: String?
    )

    data class IconAsset(
        val thumbnailUrl: String?,
        val url: String?
    )

    data class LatestMedia(
        val coverAsset: CoverAsset?,
        val title: String?,
        val type: String?
    )

    data class Serie(
        val coverAsset: CoverAsset?,
        val id: String?,
        val title: String?
    )

    data class CoverAsset(
        val url: String?
    )
}
