InputStrategy is = new InputStrategy();
is.app = this.app;
StrategyResult isResult = is.execute();
LinkedList<EditTextMeta> metaTexts = InputStrategy.searchMetaFor(isResult, identifiers);
LinkedList<String> metaIdTargets = new LinkedList<String>();
for (EditTextMeta metaText : metaTexts) {
	metaIdTargets.add(metaText.Id);
}
TraceBackStrategy tbsMeta = new TraceBackStrategy();
tbsMeta.app = this.app;
tbsMeta.params.put("startSink", TraceBackStrategy.INFORMATION_COLLECTION_SINKS);
tbsMeta.params.put("searchFor", metaIdTargets);
return tbsMeta.execute();