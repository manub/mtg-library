package net.manub.mtglibrary

class MtgLibrary extends MtgLibraryStack {

  get("/") {
    contentType = "text/html"
    ssp("index", "title" -> "Welcome!", "version" -> BuildInfo.version)
  }

}