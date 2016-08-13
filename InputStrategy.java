for (String file : files) {
	FileScanner scanner = new FileScanner(file);
	try {
		for (String inputField : INPUT_FIELDS) {
			snippets.addAll(scanner.scan(inputField));
		}
		if (snippets.size() > 0) {
			metaInfos.addAll(this.extractInputMeta(file));
		}
	} catch (FileNotFoundException e) {}
}
if (metaInfos.size() > 0) {
	StrategyResult result = new StrategyResult(StrategyResultProbability.HIGH, true, snippets);
	result.extra.put("meta", metaInfos);
	return result;
}