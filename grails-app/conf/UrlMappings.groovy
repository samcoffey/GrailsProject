class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        "/hackapi/notifypayment" (controller: "hack", parseRequest: true) { action = [POST: "processPayment"] }

    }
}
