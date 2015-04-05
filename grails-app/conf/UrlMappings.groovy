class UrlMappings {

	static mappings = {
        "/favorites"(resource: "favorites")

        "/$controller/$id?"{
            action = [GET:"show", POST:"save", PUT:"update", DELETE:"remove"]
        }

        "/history"(resource: "history")
        "/me"(resource: "me")
	}
}
