package com.example.modules

import geb.Module


class SearchResult extends Module{

    static content = {
        title { $("h3 > a") }
    }


}