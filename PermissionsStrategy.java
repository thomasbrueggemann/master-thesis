String manifest = this.app.getManifestFile();
if (manifest != null) {
	FileScanner scanner = new FileScanner(manifest);
	LinkedList<Snippet> snippets = new LinkedList<Snippet>();
	for (String searchTerm : (LinkedList<String>) this.params.get("searchFor")) {
		try {
			snippets.addAll(scanner.scan(searchTerm));
		} catch (FileNotFoundException e) {}
	}
	if (snippets.size() > 0) {
		return new StrategyResult(StrategyResultProbability.HIGH, true, snippets);
	}
}