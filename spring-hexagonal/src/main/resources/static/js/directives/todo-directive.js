Application.Directives.directive('colorGreen', [function() {
	return {
		restrict: 'A',
		link : function(scope, element, attr, ngModel) {
			element.css('color', 'green');
		}
	};
}]);