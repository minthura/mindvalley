[![Build](https://github.com/minthura/mindvalley/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/minthura/mindvalley/actions/workflows/gradle-publish.yml)

# mindvalley
Mindvalley Android Coding Challenge

## Challenging part

The most challenging part of this project would be to make sure the local database to be synchronized with the API data and showing the results to UI consistently. It is needed to make sure that unnecessary data is not being stored in the database for cache, so that the dao queries are light-weight. For showing the UI changes, I believe it is the best way to use Kotlin Flows so that we can create a pipeline to the local room database and any changes in the API refresh the data in the database, and of course reflect the UI.

## Feature for the future

It would be great to make a separate module for the API SDK and implement different product flavors and build variants so that we can reuse the SDK for different projects. :)
