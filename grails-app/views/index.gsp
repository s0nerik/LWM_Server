<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>

    <asset:link rel="import" href="polymer/polymer.html" />
    <asset:link rel="import" href="paper-shadow/paper-shadow.html" />
    <asset:link rel="import" href="colored-circle.html" />
    <asset:link rel="import" href="core-toolbar/core-toolbar.html" />
    <asset:link rel="import" href="core-pages/core-pages.html" />
    <asset:link rel="import" href="core-header-panel/core-header-panel.html" />

    <asset:link rel="import" href="core-animated-pages/core-animated-pages.html" />
    <asset:link rel="import" href="core-animated-pages/transitions/hero-transition.html"/>
    <asset:link rel="import" href="core-animated-pages/transitions/cross-fade.html"/>
    <asset:link rel="import" href="core-animated-pages/transitions/slide-down.html"/>
    <asset:link rel="import" href="core-animated-pages/transitions/slide-up.html"/>
    <asset:link rel="import" href="core-animated-pages/transitions/tile-cascade.html"/>

    <asset:javascript src="webcomponentsjs/webcomponents.min.js" />

    <asset:javascript src="d3/d3.min.js" />
    <asset:javascript src="trianglify/trianglify.min.js" />

    <asset:stylesheet src="home.css"/>

    <link href='http://fonts.googleapis.com/css?family=Leckerli+One' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Noto+Sans' rel='stylesheet' type='text/css' />

    <script src="https://apis.google.com/js/client:platform.js" async defer></script>

</head>

<body fullbleed="" >

    <span id="signinButton" style="float: right; padding: 24px;">
        <span
                class="g-signin"
                data-callback="signinCallback"
                data-clientid="CLIENT_ID"
                data-cookiepolicy="single_host_origin"
                data-requestvisibleactions="http://schema.org/AddAction"
                data-scope="https://www.googleapis.com/auth/plus.login">
        </span>
    </span>

    <polymer-element name="start-page">

        <template>
            <style>
            :host {
                position: absolute;
                width: 100%;
                height: 100%;
                box-sizing: border-box;
            }
            #section {
                left: 0px;
                top: 0px;
                position: absolute;
                width: 100%;
                height: 100%;
            }
            #core_animated_pages {
                overflow: hidden;
                height: 100vh;
                width: 400px;
                /*background-color: rgb(238, 238, 238);*/
            }
            #p {
                font-family: 'Leckerli One', cursive;
                font-size: 50pt;
                color: rgb(103, 58, 183);
            }
            #p1 {
                font-family: 'Noto Sans', sans-serif;
                font-size: 18pt;
                color: rgb(0, 0, 0);
            }
            #section5 {
                padding: 24px;
            }
            #section1 {
                padding: 60px;
            }
            #a {
                padding: 40px;
            }
            </style>
            <section id="section" vertical layout center center-justified fullbleed>
                <section id="section1" horizontal layout center-justified center>
                    <core-animated-pages id="core_animated_pages" transitions="cross-fade-all" notap >
                        <section id="screen0" active vertical layout center-justified>
                            <asset:image src="device-0-framed.png" width="400px" />
                        </section>
                        <section id="screen1" vertical layout center-justified>
                            <asset:image src="device-1-framed.png" width="400px" />
                        </section>
                        <section id="screen2" vertical layout center-justified>
                            <asset:image src="device-2-framed.png" width="400px" />
                        </section>
                    </core-animated-pages>
                    <section id="section5" vertical layout center center-justified>
                        <asset:image id="img3" src="web_hi_res_512.png" width="256px" height="256px" />
                        <section id="section6" vertical layout center center-justified>
                            <section id="p">Listen With Me!</section>
                            <section id="p1">Your local Wi-Fi radio</section>
                        </section>
                        <a id="a" href="https://play.google.com/store/apps/details?id=com.lwm.app">
                            <img id="img4" alt="Get it on Google Play" src="https://developer.android.com/images/brand/en_generic_rgb_wo_60.png" />
                        </a>
                    </section>
                </section>
            </section>
        </template>

        <script>
            Polymer({
                switchPhoneScreen: function () {
                    var p = this.shadowRoot.querySelector('#core_animated_pages');
                    p.selected += 1;
                    p.selected = p.selected % 3;
                    this.async(this.switchPhoneScreen, null, 1000);
                },
                ready: function() {
                    this.switchPhoneScreen();
                }
            });
        </script>

    </polymer-element>

    <start-page />

</body>
</html>
