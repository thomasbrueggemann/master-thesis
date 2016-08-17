Iterator<Edge> edges = this.app.callgraph.iterator();
while (edges.hasNext()) {
	Edge edge = (Edge) edges.next();
	boolean isStartEdge = false;
	for (String startSink : startSinks) {
		if (edge.toString().contains(startSink)) {
			isStartEdge = true; break;
		}
	}
	for (String startSinkInverted : startSinksInverted) {
		if (!edge.toString().contains(startSinkInverted)) {
			isStartEdge = true; break;
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
					if (foundInCallstack == false) foundInCallstack = true;
					Snippet snippet = new Snippet(method.getDeclaringClass().getName() + ".java", method.toString(), method.getJavaSourceStartLineNumber());
					snippets.add(snippet);
				}
			}
		}
	}
}