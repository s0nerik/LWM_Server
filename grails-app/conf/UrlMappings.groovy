class UrlMappings {

	static mappings = {
        "/$controller/$id?"{
            action = [GET:"show", POST:"save", PUT:"update", DELETE:"remove"]
        }

        "/me"(resource: "me")
	}
}
