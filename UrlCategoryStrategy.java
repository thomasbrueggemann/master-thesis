double probs = 0.0;
int probCount = 0;
LinkedList<Snippet> snippets = new LinkedList<Snippet>();
if (!this.params.containsKey("searchFor")) {
	return new StrategyResult(StrategyResultProbability.HIGH, false);
}
for (AppUrl appUrl : this.app.categorizedUrls) {
	if (appUrl.category == (String) this.params.get("searchFor")) {
		probs += appUrl.probability;
		probCount++;
		snippets.add(appUrl.snippet);
	}
}
if (probCount > 0) {
	return new StrategyResult(StrategyResultProbability.fromDouble(probs / (double) probCount), true, snippets);
}