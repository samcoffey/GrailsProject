class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        "/hackapi/notifypayment" (controller: "hack", parseRequest: true) { action = [GET: "processPayment"] }
        "/hackapi/checkout" (controller: "hack", parseRequest: true) { action = [POST: "checkout"] }

		name simpview: "/simpview/simplify"(view:"simpview/simplify")
		name Success: "/Success/success"(view:"Success/success")
		name tagoff: "/tagoff/tagoff"(view:"tagoff/tagoff")
		name home: "/home/index"(view:"home/index")
		name Success1: "/Success/success1"(view:"Success/success1")
		name tagoff1: "/tagoff/tagoff1"(view:"tagoff/tagoff1")
		name home1: "/home/index1"(view:"home/index1")
    }
}
