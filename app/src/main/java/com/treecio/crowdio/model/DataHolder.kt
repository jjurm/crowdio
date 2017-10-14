package com.treecio.crowdio.model

object DataHolder {

    val performances: HashMap<String, Performance> = HashMap<String, Performance>()

    fun updatePerformances(fetched: List<Performance>) {
        for (it in fetched) {
            updatePerformance(it)
        }
    }

    fun updatePerformance(performance: Performance) {
        val id = performance.id ?: return
        performances[id] = performance
    }

}
