package com.example.circlediagram_

import android.os.Bundle


import android.app.Activity

class MainActivity: Activity() {
    lateinit var circleDiagram: CircleDiagramView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        circleDiagram = CircleDiagramView(this)
        setContentView(circleDiagram)
    }
}