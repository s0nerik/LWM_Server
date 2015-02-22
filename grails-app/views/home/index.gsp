<%@ page import="groovy.json.JsonSlurper" %>
<%
    def colorPrimary = '#673ab7'
    def colorPrimaryDark = '#512da8'
    def navDrawerWidth = '256px'

    def playbackHistory = [
            [
                artist: "Asking Alexandria",
                song: "Dear Insanity",
                cover_url: "http://ecx.images-amazon.com/images/I/41yfJvNEpRL.jpg"
            ],
            [
                artist: "Asking Alexandria",
                song: "Don't Pray For Me",
                cover_url: "http://ecx.images-amazon.com/images/I/51z9%2BHwOAML.jpg"
            ],
            [
                artist: "As I Lay Dying",
                song: "Cauterize",
                cover_url: "http://upload.wikimedia.org/wikipedia/en/c/cc/Aild_awakened-cover.jpg"
            ],
            [
                artist: "As I Lay Dying",
                song: "Paralyzed",
                cover_url: "http://www.revolvermag.com/wp-content/uploads/2011/10/aild_decas.jpg"
            ],
            [
                artist: "As I Lay Dying",
                song: "Beyond Our Suffering",
                cover_url: "http://upload.wikimedia.org/wikipedia/en/0/02/As_I_Lay_Dying_-_Powerless_Rise.jpg"
            ],
    ]
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
    <asset:link rel="import" href="song-card.html" />

    <link href='http://fonts.googleapis.com/css?family=Leckerli+One' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Noto+Sans' rel='stylesheet' type='text/css' />

    <style>
        body {
            font-family: 'Noto Sans', sans-serif;
        }

        .navigation_drawer_toolbar {
            background: ${colorPrimaryDark};
            color: white;
            font-family: 'Leckerli One', cursive;
            font-size: 20pt;
        }

        core-scaffold::shadow core-toolbar {
            background: ${colorPrimary};
            font-family: 'Noto Sans', sans-serif;
            color: white;
        }

        .drawer_username {
            color: #ffffff;
            z-index: 1;
            padding-left: 16px;
            font-size: 14pt;
        }

        .drawer-dropdown-btn {
            color: #ffffff;
            margin: auto;
            margin-right: 16px;
        }

        /*core-scaffold::shadow core-header-panel {*/
            /*background: #FFF;*/
            /*color: black;*/
        /*}*/
    </style>

    <style shim-shadowdom>
        core-scaffold core-toolbar {
            background: ${colorPrimary};
            font-family: 'Noto Sans', sans-serif;
            color: white;
        }

        /*core-scaffold #main core-header-panel {*/
            /*background: #FFF;*/
            /*color: black;*/
        /*}*/
    </style>

</head>

<body>

    <template is="auto-binding">
        <core-scaffold>
            <core-header-panel navigation flex mode="seamed" style="width: ${navDrawerWidth};">
                <core-toolbar id="navheader" class="navigation_drawer_toolbar" horizontal center-justified layout >
                    <span>Listen With Me!</span>
                </core-toolbar>
                <div vertical layout fit>
                    <div style="height: ${navDrawerWidth};" vertical layout>
                        <core-image style="width: ${navDrawerWidth}; height: ${navDrawerWidth};" sizing="cover" src="${user.avatar_url}" fit></core-image>
                        <span flex></span>
                        <div horizontal layout>
                            <p class="drawer_username gradient-bottom">${user.name}</p>
                            <span flex></span>
                            <core-icon icon="arrow-drop-down-circle" class="drawer-dropdown-btn" ></core-icon>
                        </div>
                    </div>
                    <core-menu vertical layout flex selected="0" selectedItem="{{menuSelection}}">
                        <core-item icon="history" label="Playback history"></core-item>
                        <core-item icon="favorite" label="Favorite songs"></core-item>
                        <core-item icon="av:queue-music" label="Playlists"></core-item>
                        <span flex></span>
                        <core-item icon="settings" label="Settings"></core-item>
                    </core-menu>
                </div>
            </core-header-panel>

            <span tool>{{menuSelection.label}}</span>

            <div class="content">

                <div horizontal layout wrap style="padding: 8px;">
                    <g:each in="${0..200}">
                        <% def i = Math.random() * playbackHistory.size() as int; %>
                        <song-card
                            title="${playbackHistory[i].song}"
                            artist="${playbackHistory[i].artist}"
                            cover_url="${playbackHistory[i].cover_url}"
                            style="margin: 8px;"
                        ></song-card>
                    </g:each>

                </div>

            </div>
        </core-scaffold>
    </template>

</body>
</html>
