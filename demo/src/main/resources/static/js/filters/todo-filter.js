Application.Filters.filter('isDone', [function() {
	return function (items, isDone) {
		if (!isDone)
			return items;
		return items.filter(x => x.done === isDone);
	};
}]);