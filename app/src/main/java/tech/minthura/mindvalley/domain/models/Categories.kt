package tech.minthura.mindvalley.domain.models

data class Categories(
    val data: Data?
) {
    data class Data(
        val categories: List<Category>?
    )

    data class Category(
        val name: String?
    )
}


