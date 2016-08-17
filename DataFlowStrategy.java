MultiMap<SerializedSinkInfo, SerializedSourceInfo> results = this.app.dataflow.getResults();
for (SerializedSinkInfo sink : results.keySet()) {
	if (this.params.containsKey("sinkIncludes")) {
		for (String searchTermSink : (LinkedList<String>) this.params.get("sinkIncludes")) {
			if (sink.toString().contains(searchTermSink)) {
				return new StrategyResult(StrategyResultProbability.HIGH, true)
			}
		}
	}
	for (SerializedSourceInfo source : results.get(sink)) {
		if (this.params.containsKey("sourceIncludes")) {
			for (String searchTermSource : (LinkedList<String>) this.params.get("sourceIncludes")) {
				if (source.toString().contains(searchTermSource)) {
					return new StrategyResult(StrategyResultProbability.HIGH, true);
				}
			}
		}
	}
}