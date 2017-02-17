(function() {
	'use strict';

	angular.module(
			"prensa.common.config",
			[ "ngRoute", "ngSanitize", "pascalprecht.translate",
					"LocalStorageModule", "prensa.rss.rss" ])

	.config(translateProvider_config)

	.config(localStorageServiceProvider_config)

	.run(run)

	;

	/* @ngInject */
	function translateProvider_config($translateProvider) {
		$translateProvider.useSanitizeValueStrategy('escape');
	}

	/* @ngInject */
	function localStorageServiceProvider_config(localStorageServiceProvider) {
		localStorageServiceProvider.setPrefix('argo');
	}

	/* @ngInject */
	function run($rootScope) {
		$rootScope.dateFormat = "dd/MM/yyyy";
		$rootScope.datetimeFormat = "dd/MM/yyyy HH:mm";
	}
})();