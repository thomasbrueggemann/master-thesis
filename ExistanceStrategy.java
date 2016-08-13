for (String file : files) {
	FileScanner scanner = new FileScanner(file);
	try {
		LinkedList<Snippet> snippets = new LinkedList<Snippet>();
		for (String searchTerm : (LinkedList<String>) this.params.get("searchFor")) {
			snippets.addAll(scanner.scan(searchTerm));
		}
		if (snippets.size() > 0) {
			return new StrategyResult(StrategyResultProbability.HIGH, true, snippets);
		}
	} catch (FileNotFoundException e) {}
}