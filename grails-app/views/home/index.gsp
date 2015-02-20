<% def colorPrimary = '#673ab7' %>
<% def colorPrimaryDark = '#512da8' %>

<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <meta name="layout" content="main">

    <asset:link rel="import" href="core-scaffold/core-scaffold.html" />
    <asset:link rel="import" href="core-menu/core-menu.html" />
    <asset:link rel="import" href="core-toolbar/core-toolbar.html" />
    <asset:link rel="import" href="core-item/core-item.html" />
    <asset:link rel="import" href="core-image/core-image.html" />

    <link href='http://fonts.googleapis.com/css?family=Leckerli+One' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Noto+Sans' rel='stylesheet' type='text/css' />

    <style>
        .navigation_drawer_toolbar {
            background: ${colorPrimaryDark};
            color: white;
            font-family: 'Leckerli One', cursive;
            font-size: 20pt;
        }

        core-scaffold::shadow core-toolbar {
            background: ${colorPrimary};
            color: white;
        }

        /*core-scaffold::shadow core-header-panel {*/
            /*background: #FFF;*/
            /*color: black;*/
        /*}*/
    </style>

    <style shim-shadowdom>
        core-scaffold core-toolbar {
            background: ${colorPrimary};
            color: white;
        }

        /*core-scaffold #main core-header-panel {*/
            /*background: #FFF;*/
            /*color: black;*/
        /*}*/
    </style>

</head>

<body>

    <core-scaffold>
        <core-header-panel navigation flex mode="seamed">
            <core-toolbar id="navheader" class="navigation_drawer_toolbar" horizontal center-justified layout >
                <span>Listen With Me!</span>
                %{--<core-image style="width:500px; height:500px;" sizing="cover" src="${users[0].avatar_url}" />--}%
            </core-toolbar>
            <core-menu>
                <core-item label="One"></core-item>
                <core-item label="Two"></core-item>
            </core-menu>
        </core-header-panel>

        <span style="font-family: sans-serif;" tool>Songs</span>

        <div class="content">
            <ul>
                <g:each in="${users}">
                    <li>${it}</li>
                </g:each>
            </ul>
        </div>
    </core-scaffold>

</body>
</html>
