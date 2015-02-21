<%
    def colorPrimary = '#673ab7'
    def colorPrimaryDark = '#512da8'
    def navDrawerWidth = '256px'
%>

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
    <asset:link rel="import" href="core-icons/av-icons.html" />
    <asset:link rel="import" href="core-list/core-list.html" />

    <link href='http://fonts.googleapis.com/css?family=Leckerli+One' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Noto+Sans' rel='stylesheet' type='text/css' />

    <style>
        body {
            font-family: sans-serif;
        }

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
        <core-header-panel navigation flex mode="seamed" style="width: ${navDrawerWidth};">
            <core-toolbar id="navheader" class="navigation_drawer_toolbar" horizontal center-justified layout >
                <span>Listen With Me!</span>
            </core-toolbar>
            <div vertical layout fit>
                <div style="height: ${navDrawerWidth};" vertical layout>
                    <core-image style="width: ${navDrawerWidth}; height: ${navDrawerWidth};" sizing="cover" src="${user.avatar_url}" fit></core-image>
                    <span flex></span>
                    <p style="color: #ffffff; z-index: 1; padding-left: 16px;" class="gradient-bottom">${user.name}</p>
                </div>
                <core-menu vertical layout flex>
                    <core-item icon="history" label="Playback history"></core-item>
                    <core-item icon="favorite" label="Favorite songs"></core-item>
                    <core-item icon="av:queue-music" label="Playlists"></core-item>
                    <span flex></span>
                    <core-item icon="settings" label="Settings"></core-item>
                </core-menu>
            </div>
        </core-header-panel>

        <span tool>Songs</span>

        <div class="content">
            ${user.avatar_url}
        </div>
    </core-scaffold>

</body>
</html>
