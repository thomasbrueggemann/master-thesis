StrategyResult result = new StrategyResult();
String[] packageNameParts = this.app.getPackageName().split("\\.");
for (AppUrl url : this.app.urls) {
	for (String packageNamePart : packageNameParts) {
		if (packageNamePart.length() > 3) {
			for (String urlPart : url.url.split("/")) {
				int similarity = StringAnalyzer.isSimilar(urlPart, packageNamePart);
				double similarityScore = 1.0 - (double) similarity / (double) Math.max(url.url.length(), packageNamePart.length());
				if (similarityScore > 0.9) {
					result.extra.put("url", url.url);
					result.found = true;
					result.probability = StrategyResultProbability.fromDouble(similarityScore);
					result.snippets.add(url.snippet);
					return result;
				}
			}
		}
	}
}