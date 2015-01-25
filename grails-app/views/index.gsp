<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome</title>
	</head>
	<body>
		<link rel="import"
			  href="bower_components/core-toolbar/core-toolbar.html">
		<link rel="import"
			  href="bower_components/core-menu/core-menu.html">
		<link rel="import"
			  href="bower_components/core-item/core-item.html">
		<link rel="import"
			  href="bower_components/core-header-panel/core-header-panel.html">
		<link rel="import"
			  href="bower_components/core-drawer-panel/core-drawer-panel.html">
		<link rel="import"
			  href="bower_components/core-scaffold/core-scaffold.html">

		<core-scaffold>
			<core-header-panel navigation flex>
				<core-toolbar>
					<span>Menu</span>
				</core-toolbar>
				<core-menu>
					<core-item label="One"></core-item>
					<core-item label="Two"></core-item>
				</core-menu>
			</core-header-panel>

			<span tool>Title</span>

			<div class="content">
				If drawer is hidden, press button to display drawer.
			</div>
		</core-scaffold>
	</body>
</html>
