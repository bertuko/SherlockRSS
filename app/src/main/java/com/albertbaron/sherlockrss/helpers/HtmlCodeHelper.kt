package com.albertbaron.sherlockrss.helpers

class HtmlCodeHelper {
    companion object {
        fun AdaptCodeToWebView(inputCode: String): String {
            var head: String = "<head>"
            head += "<style>img,iframe{max-width: 100%; width:auto; height: auto;}</style>"
            head += "<meta name=\"viewport\" content=\"initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no\">"
            head += "<meta name=\"HandheldFriendly\" content=\"true\">"
            head += "<meta name=\"mobile-web-app-capable\" content=\"yes\">"
            head += "</head>"
            val outputCode: String = "<html>$head<body>$inputCode</body></html>"
            return outputCode
        }
    }
}
