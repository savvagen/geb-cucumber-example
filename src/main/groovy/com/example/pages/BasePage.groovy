package com.example.pages

import com.example.modules.Header
import geb.Page

class BasePage extends Page {

    static content = {
        header { $("#gb").module(Header) }
    }


}
