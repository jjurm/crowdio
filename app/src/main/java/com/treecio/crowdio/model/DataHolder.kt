package com.treecio.crowdio.model

object DataHolder {

    val performances: HashMap<String, Performance> = HashMap<String, Performance>()
    val submittedPerformanes = HashSet<String>()
    val performanceListeners = HashSet<PerformancesDataListener>()

    fun updatePerformances(fetched: List<Performance>) {
        for (it in fetched) {
            updatePerformance(it)
        }
        notifyPerformanceListeners()
    }

    fun updateSinglePerformance(performance: Performance) {
        updatePerformance(performance)
        notifyPerformanceListeners()
    }

    private fun updatePerformance(performance: Performance) {
        val id = performance.id ?: return
        performances[id] = performance
    }

    private fun notifyPerformanceListeners() {
        performanceListeners.forEach { it.performanceDataUpdated(performances) }
    }

    interface PerformancesDataListener {
        fun performanceDataUpdated(performances: HashMap<String, Performance>)
    }

}
