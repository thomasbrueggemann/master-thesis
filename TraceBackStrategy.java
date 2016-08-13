ExistanceStrategy es = new ExistanceStrategy();
es.app = this.app;
es.params.put("searchFor", startSinks);
StrategyResult esResult = es.execute();
boolean sinkFeaturesAvailable = esResult.found == true;
if (sinkFeaturesAvailable) {
	boolean callGraphAvailable = this.app.callgraph != null && this.app.callgraph.size() > 0;
	if (callGraphAvailable) {
		int sinksFound = 0;
		int featureFound = 0;
		LinkedList<Snippet> snippets = new LinkedList<Snippet>();
		Iterator<Edge> edges = this.app.callgraph.iterator();
		while (edges.hasNext()) {
			Edge edge = (Edge) edges.next();
			boolean isStartEdge = false;
			for (String startSink : startSinks) {
				if (edge.toString().contains(startSink)) {
					isStartEdge = true;
					break;
				}
			}
			for (String startSinkInverted : startSinksInverted) {
				if (!edge.toString().contains(startSinkInverted)) {
					isStartEdge = true;
					break;
				}
			}
			if (isStartEdge) {
				sinksFound++;
				boolean foundInCallstack = false;
				SootMethod tgt = edge.tgt();
				LinkedList<SootMethod> callers = this.traceBack(tgt);

				for (SootMethod method : callers) {
					String mth = method.toString().toLowerCase();
					for (String test : (LinkedList<String>) this.params.get("searchFor")) {

						if (mth == null || test == null) continue;
						if (mth.contains(test.toLowerCase())) {
							if (foundInCallstack == false) {
								featureFound++;
								foundInCallstack = true;
							}
							Snippet snippet = new Snippet(method.getDeclaringClass().getName() + ".java",
									method.toString(), method.getJavaSourceStartLineNumber());
							snippets.add(snippet);
						}
					}
				}
			}
		}
		StrategyResult result = new StrategyResult();
		result.found = (featureFound > 0);
		result.probability = (featureFound > 0) ? StrategyResultProbability.HIGH : StrategyResultProbability.MEDIUM;
		result.snippets = snippets;
		if (sinksFound > 0) {
			result.extra.put("featureRatio", featureFound / sinksFound);
		}
		return result;
	}
}