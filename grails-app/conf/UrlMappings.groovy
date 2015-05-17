class UrlMappings {

	static mappings = {
        "/playlists"(resource: "playlist")
        "/favorites"(resource: "favorites")
        "/history"(resource: "history")
        "/me"(resource: "me")
        "/settings"(resource: "settings")

        "/$controller/$id?"{
            action = [GET:"show", POST:"save", PUT:"update", DELETE:"remove"]
        }
	}
}
