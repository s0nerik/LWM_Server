class UrlMappings {

	static mappings = {
        "/playlists"(resource: "playlist")
        "/favorites"(resource: "favorites")
        "/history"(resource: "history")
        "/me"(resource: "me")

        "/$controller/$id?"{
            action = [GET:"show", POST:"save", PUT:"update", DELETE:"remove"]
        }
	}
}
